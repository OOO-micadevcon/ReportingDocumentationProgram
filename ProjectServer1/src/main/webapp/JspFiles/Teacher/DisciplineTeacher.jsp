<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Выбор дисциплины</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
    <script src="public/script.js"></script>
  </head>
    <body>
    <% 
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
	%>
  <div class="page">
      
       <jsp:include page="JspFiles/Teacher/header.jsp" />

    <div class="content" >
      <input class="form-control" type="text" placeholder="Поиск по критерию" id="search-text" onkeyup="tableSearch()">
    <table border="1" id="info-table">
   <caption>Список дисциплин</caption>
     <tr>
    <th>Название группы</th>
    <th>Название дисциплины</th>
    <th>Семестр обучения</th>
   </tr>
   <%
   int semester= TimeWork.currentSem();
   PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Group_access\" inner join \"Group_disciplines\"  ON (group_disciplines = id_group_disciplines) inner join \"Groups\" ON ( \"Group_disciplines\".group = \"Groups\".id_group) inner join \"Discipline\" ON disciplines = id_discipline where teacher=? and half_year=?" );
   ps.setInt( 1, (Integer)session.getAttribute("id_teacher") );
   ps.setInt( 2, (Integer)semester);
   result = ps.executeQuery();
   
   while(result.next())
   {
   %>
   <tr><td><%=result.getString("name_group")%></td><td><%=result.getString("name_discipline")%></td><td><%=result.getString("semester")%></td><td> <button class="" onclick="document.location='SearchGroup?group=<%=result.getInt("group")%>&semester=<%=result.getInt("semester") %>&disciplines=<%=result.getInt("disciplines") %>'">Подробнее</button></td></tr>
   <% } %>
   </table>
    </div>

  
  <jsp:include page="JspFiles/Teacher/footer.jsp" />
  </div>
    </body>
  
    </html>