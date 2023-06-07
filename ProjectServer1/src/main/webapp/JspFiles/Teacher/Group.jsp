<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Выбор группы</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
    <script src="public/script.js"></script>
  </head>
  <body>
  <div class="page">
    <jsp:include page="JspFiles/Teacher/header.jsp"/>
    <div class="content">
    <% 
    int semester=TimeWork.currentSem();
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			ResultSet resultWork = null;
			
			PreparedStatement ps1 = connect.prepareStatement( "SELECT * FROM \"Discipline\"  where   \"id_discipline\"=?" );
			   ps1.setInt( 1, Integer.parseInt(request.getParameter("disciplines")));
			   resultWork = ps1.executeQuery();
			   if(resultWork.next())
			{
			%>
    <input class="form-control" type="text" placeholder="Поиск по критерию" id="search-text" onkeyup="tableSearch()">
    <table border="1" id="info-table">
   <caption>Дисциплина:<%=resultWork.getString("name_discipline") %><%} %></caption>
    <tr><th colspan="6"><button onclick="document.location='CreateReport?group=<%=request.getParameter("group") %>&semester=<%=semester %>&disciplines=<%=request.getParameter("disciplines") %>'" download="" title="Загруженная работа в формате .pdf">Сгенерировать ведомость</button></td></tr>
     <tr>
     <th>Группа</th>
    <th>Имя</th>
    <th>Фамилия</th>
    <th>Отчество</th>
    <th>Состояние работы</th>
    
   </tr>
   <%
   
   PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Student_data\"inner join \"Groups\" ON ( \"Student_data\".group = \"Groups\".id_group) inner join \"Person\" ON (person = id_person) inner join \"Paper_work\" on (student=id_student) where  current_semester=? and \"group\"=? and discipline=?" );
   ps.setInt( 1, (Integer)semester);
   ps.setInt( 2, Integer.parseInt(request.getParameter("group")));
   ps.setInt( 3, Integer.parseInt(request.getParameter("disciplines")));
   result = ps.executeQuery();
   while(result.next())
   {
   %>
   <tr><td><%=result.getString("name_group")%></td><td><%=result.getString("name")%></td><td><%=result.getString("Fname")%></td><td><%=result.getString("Oname")%></td><td><%=TypeWork.defineTypeWork(result.getString("name_file_work"),result.getString("assessment"))  %></td><td><button class="" onclick="document.location='PaperWorkTeacher?id=<%=result.getString("id_work")%>'">Подробнее</button></td></tr>
   <% } %>
   </table>
    </div>

    <jsp:include page="JspFiles/Teacher/footer.jsp" />
        </div>
    </body>
    </html>