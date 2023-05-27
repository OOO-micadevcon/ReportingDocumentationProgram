<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Письменные работы</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
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
    <table border="1">
   <caption>Список дисциплин</caption>
     <tr>
    <th>Дисциплина</th>
    <th>Тип работы</th>
    <th>Статус работы</th>
   </tr>
   <%
   PreparedStatement ps = connect.prepareStatement( "select * FROM \"Group_disciplines\" JOIN \"Discipline\" ON disciplines = id_discipline where \"group\"=? and semester=?" );
   ps.setInt( 1, (Integer)session.getAttribute("group") );
   ps.setInt( 2, (Integer)session.getAttribute("current_semester"));
   result = ps.executeQuery();
   
   while(result.next())
   {
	   ResultSet resultWork = null;
	   PreparedStatement pswork = connect.prepareStatement( "select * FROM \"Paper_work\" inner join \"Work_type\" ON (type = id_work_type) where \"student\"=? and semester_work=? and discipline=?" );
	   pswork.setInt( 1, (Integer)session.getAttribute("id_student") );
	   pswork.setInt( 2, (Integer)session.getAttribute("current_semester") );
	   pswork.setInt( 3, result.getInt("id_discipline")  );
	   resultWork = pswork.executeQuery();
	   if ( resultWork.next()){
		  /*  System.out.println(resultWork.getInt("student")); */
	  
   %>
   <tr><td><%=result.getString("name_discipline") %></td><td><%=resultWork.getString("name_type")%></td>
   <td><%=TypeWork.defineTypeWork(resultWork.getString("name_file_work"),resultWork.getString("assessment"))  %></td>
   <td> <button class="title-name" onclick="document.location='PaperWork?id=<%=resultWork.getInt("id_work")%>'">Подробнее</button></td></tr>
   <% }} %>
   </table>
    </div>
     <jsp:include page="JspFiles/Student/footer.jsp" />
    </div>
    </body>
    </html>