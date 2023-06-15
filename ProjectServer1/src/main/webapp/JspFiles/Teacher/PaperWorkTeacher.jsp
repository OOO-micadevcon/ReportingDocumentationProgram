<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Письменная работа</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
  </head>
  <body>
<div class="page">
    <jsp:include page="JspFiles/Teacher/header.jsp" />
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
    	 if(result.getString("name_file_work")!=null && result.getString("comment")==null || (result.getString("correct_file_work")!=null&&result.getString("assessment")==null))
    	 { 
    %>
    		  <form action = "DataTeacherWork?id_work=<%=id %>" method = "post"id="myform" >
    	<%}%>
  <table border="1">
   <caption>Данные о работе</caption>
   <tr><td class="tableclm" >Ученик:</td><td><%=result.getString("name")+" "+result.getString("Fname")+" "+result.getString("Oname") %></tr>
   <tr><td>Дисциплина:</td><td><%=result.getString("name_discipline")%></td></tr>
   <tr><td>Тип работы:</td><td><%=result.getString("name_type")%></td></tr>
   <tr><td>Проверяющий:</td><td><%=resultTeacher.getString("name")+" "+resultTeacher.getString("Fname")+" "+resultTeacher.getString("Oname") %></td></tr>
   <tr><td>Статус:</td><td><%=TypeWork.defineTypeWork(result.getString("name_file_work"),result.getString("assessment"))  %></td></tr><%;
     %>
    <% 	
    if(result.getString("correct_file_work")==null)
   		{ 
   	%>
         	<tr><td>Визированная работа:</td><td>Визированная работа не загружена</td></tr>
   	<%
        }
    else
     	{
   	%>  
   			<tr><td>Визированная работа:</td><td><a href="<%="DowloadFile?id="+result.getString("correct_file_work") %>" download="" title="Загруженная работа в формате .pdf">Скачать</a></td></tr><%;
    	}
    if(result.getString("name_file_work")==null)
		{ 
	%>
     	<tr><td>Письменная работа:</td><td>Письменная работа не загружена</td></tr>
	<%
    }
else
 	{
	%>  
			<tr><td>Письменная работа:</td><td><a href="<%="DowloadFile?id="+result.getString("name_file_work") %>" download="" title="Загруженная работа в формате .pdf">Скачать</a></td></tr><%;
	}
     %>
    		<tr><td>Срок сдачи:</td><td><%=result.getString("date_end_work")%></td></tr>
    <% 	
    if(result.getString("correct_file_work")!=null && result.getString("link_AP")==null)
   		{ %>
  
  	    <tr><td>Ссылка на антиплагиат:</td><td> <input class='text_input'form="myform" type="text" name="link_AP"id="link_AP" placeholder="Ссылка на АП"></td></tr>
  	 <%
        }
    
     else if(result.getString("link_AP")!=null)
     {
     %>
    	 <tr><td>Ссылка на антиплагиат:</td><td><%=result.getString("link_AP")%></td></tr>
    	 <%
     }
      if(result.getString("comment")==null)
   		{ %>
  	    <tr><td>Замечания к работе:</td><td> <textarea form="myform" maxlength="500" name="comment"id="comment" placeholder="Замечания к работе" ></textarea></td></tr>
  	    <%
        }
      
     else
     {
     %>
    	 <tr><td>Замечания к работе:</td><td><textarea readonly ><%=result.getString("comment")%></textarea></td></tr>
    	 <%
     }
      
       if(result.getString("correct_file_work")!=null &&result.getString("assessment")==null)
   		{ %>
  	    <tr><td>Оценка:</td><td> <input class='text_input' type="text" form="myform" name="assessment"id="assessment" placeholder="Оценка"></td></tr><%;
        }
      
     else if(result.getString("assessment")!=null)
     {
     %>
    	<tr><td>Оценка:</td><td><%=resultTeacher.getString("assessment")%></td></tr>
    	<%
     }
       if(result.getString("name_file_work")!=null && result.getString("comment")==null || (result.getString("correct_file_work")!=null&&result.getString("assessment")==null))
  		{ %>
  		<tr><td colspan="2" class="bottom"><input class="size button" type = "submit"  value = "Отправить" /></td></tr>
       <% }
      
      
     }
      %>
    
      </table>
     
      </form>
      </div>
      <jsp:include page="JspFiles/Teacher/footer.jsp" />
      </div>
</body>
</html>