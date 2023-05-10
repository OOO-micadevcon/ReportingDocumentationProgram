<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , ru.classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
  </head>
  <body>
    <header>
      <div class="header-top">
          <a href="/" >главная</a>
		        <a href="">Магазин</a>
		         <a href="Support" >Поддержка</a></span>
        <div class="header-right">
        <% if(session.getAttribute("current_name") == null)
	  	{%>
		<a href="Autorization"> Авторизация\Регистрация</a>
		
		<%} 
        else{%>
        Пользователь:<%= GetCookie.GetCookie(request, "name")%>
        <form method="POST" action="Autorization">
        <input  type="submit" value="Выйти из аккаунта" name="kill">
        </form>
        <%}%>
        </div>
        </div>
    </header>
    <div>
    <table border="1">
   <caption>Список дисциплин</caption>
     <tr>
    <th>1</th>
    <th>2</th>
    <th>3</th>
    <th>4</th>
   </tr>
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
     if ( result.next() && resultTeacher.next()){
   %>
   <tr><td>Ученик:<%=result.getString("name")+" "+result.getString("Fname")+" "+result.getString("Oname") %></td><td>Дисциплина:<%=result.getString("name_discipline")%></td><td>3</td><td>4</td></tr>
   <% } %>
</body>
</html>