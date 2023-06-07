<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Архив работ</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
    <script src="public/script.js"></script>
  </head>
  <body>
  <div class="page">
    <jsp:include page="JspFiles/Student/header.jsp" />
    <% 
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			%>
    <div class="content" >
    <input class="form-control" type="text" placeholder="Поиск по критерию" id="search-text" onkeyup="tableSearch()">
    <table border="1" id="info-table">
   <caption>Список дисциплин</caption>
     <tr>
    <th>Дисциплина</th>
    <th>Тип работы</th>
    <th>Семестр</th>
    <th>Статус работы</th>
   </tr>
   <%
   /* PreparedStatement ps = connect.prepareStatement( "select * FROM \"Group_disciplines\" JOIN \"Discipline\" ON disciplines = id_discipline where \"group\"=? and semester=?" );
   ps.setInt( 1, (Integer)session.getAttribute("group") );
   ps.setInt( 2, (Integer)session.getAttribute("current_semester"));
   result = ps.executeQuery();
   
   while(result.next())
   { */
	   ResultSet resultWork = null;
	   PreparedStatement pswork = connect.prepareStatement( "SELECT * FROM \"Student_data\" inner join \"Groups\" ON ( \"Student_data\".group = \"Groups\".id_group)  inner join \"Person\" ON (person = id_person)   inner join \"Paper_work\" on (student=id_student) inner join \"Work_type\" ON (type = id_work_type) inner join \"Discipline\" ON (discipline = id_discipline)  where id_student=?" );
	   pswork.setInt( 1, (Integer)session.getAttribute("id_student") );
	   resultWork = pswork.executeQuery();
	   while( resultWork.next()){
	  
   %>
   <tr><td><%=resultWork.getString("name_discipline") %></td><td><%=resultWork.getString("name_type")%></td>
   <td><%=resultWork.getString("semester_work") %></td>
   <td><%=TypeWork.defineTypeWork(resultWork.getString("name_file_work"),resultWork.getString("assessment"))  %></td>
   <td> <button  onclick="document.location='PaperWork?id=<%=resultWork.getInt("id_work")%>'">Подробнее</button></td></tr>
   <% }/* } */ %>
   </table>
    </div>
     <jsp:include page="JspFiles/Student/footer.jsp" />
    </div>
    </body>
    </html>