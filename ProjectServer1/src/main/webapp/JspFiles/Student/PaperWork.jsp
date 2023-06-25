<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
  </head>
  <body>
    <div class="page">
    <jsp:include page="header.jsp" />
    <div class="content" >
    <% 
    		String id=request.getParameter("id");
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			ResultSet resultTeacher = null;
			   PreparedStatement psTeacher = connect.prepareStatement( "SELECT * FROM \"Paper_work\" inner join \"Teacher_data\" ON (teacher = id_teacher) inner join \"Person\" ON (\"Teacher_data\".person =id_person  ) where id_work=?" );
			   psTeacher.setInt( 1, Integer.parseInt(id) );

			   resultTeacher = psTeacher.executeQuery();		
    
    PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Paper_work\"  inner join \"Student_data\"  ON (student = id_student) inner join \"Work_type\" ON (type = id_work_type) inner join \"Person\" ON ( \"Student_data\".person = id_person) inner join \"Discipline\" ON (discipline = id_discipline) where id_work=?" );
   ps.setInt( 1, Integer.parseInt(id) );
   result = ps.executeQuery();
     if ( result.next() && resultTeacher.next())
     {
		   %>
		    <table border="1">
		   <caption>Данные о работе</caption>
		   <tr><td class="tableclm" >Ученик:</td><td><%=result.getString("name")+" "+result.getString("Fname")+" "+result.getString("Oname") %></tr>
		   <tr><td>Дисциплина:</td><td><%=result.getString("name_discipline")%></td></tr>
		   <tr><td>Тип работы:</td><td><%=result.getString("name_type")%></td></tr>
		   <tr><td>Проверяющий:</td><td><%=resultTeacher.getString("name")+" "+resultTeacher.getString("Fname")+" "+resultTeacher.getString("Oname") %></td></tr>
		   <tr><td>Статус:</td><td><%=TypeWork.defineTypeWork(result.getString("name_file_work"),result.getString("assessment"))  %></td></tr>
		   <% 
		   if(result.getString("correct_file_work")==null)
		   {
		   if(result.getString("name_file_work")==null )
		   { %>
		  
		   <form action = "PaperWork?id=<%=id %>" method = "post" enctype = "multipart/form-data">
		   <tr><td>Письменная работа:</td><td><input class="size bottom" accept=".pdf" type = "file" name = "file" size = "50" />
		   <input class="size bottom" type = "submit" value = "Загрузить файл" />
		   </td></tr>  
		      </form>
		    <% 
		   }
		   else
		      {
		    	  %><tr><td>Письменная работа:</td><td><a href="<%="DowloadFile?id="+result.getString("name_file_work") %>" download="" title="Загруженная работа в формате .pdf">Скачать</a></td></tr>
		    <% }
		    }%>
   
		   <% if(result.getString("correct_file_work")==null && result.getString("comment")!=null)
		   { %>
		  
		  
		   <tr>
		   
		   <td>Визированная работа:</td>
		    
		   <td>
		   <form action = "PaperWorkCorrect?id=<%=id %>" method = "post" enctype = "multipart/form-data">
		   <input class="bottom" accept=".pdf" type = "file" name = "file" size = "50" />
		   <input class="size bottom" type = "submit" value = "Загрузить файл" /></form>
		   
		   </td>
		  
		   </tr>  
		      
		      <% }
			   	else if (result.getString("comment")!=null)
			      {
			    	  %><tr><td>Визированная работа:</td><td><a href="<%="DowloadFile?id="+result.getString("name_file_work") %>" download="" title="Загруженная работа в формате .pdf">Скачать</a></td></tr>
			    	  <% 
			       }
			   %>
   
   
   <tr><td>Срок сдачи:</td><td><%=result.getString("date_end_work")%></td></tr>
   <%
   if(result.getString("link_AP")==null)
   		{ %>
  
	<tr><td>Ссылка на антиплагиат:</td><td>Работа не проверена</td></tr>  	 
<%
        }
     else
     {
     %> 
   <tr><td>Ссылка на антиплагиат:</td><td><%=result.getString("link_AP")%></td></tr>
   <%}
   if(result.getString("comment")==null)
   		{ %>
  
	<tr><td>Замечания к работе:</td><td>Работа не проверена</td></tr>  	 
<%
        }
     else
     {
     %> 
    <tr><td>Замечания к работе:</td><td><textarea readonly ><%=result.getString("comment")%></textarea></td></tr>
    <% }
    if(result.getString("assessment")==null)
   		{ %>
  
	<tr><td>Баллы за работу:</td><td>Работа не проверена</td></tr>  	 
<%
        }
     else
     {%> 
    	 <tr><td>Баллы за работу:</td><td><%=resultTeacher.getString("assessment")%></td></tr>
     <%}}%>
     
    
       </table>
       </div>
            <jsp:include page="footer.jsp" />
      </div>
</body>
</html>