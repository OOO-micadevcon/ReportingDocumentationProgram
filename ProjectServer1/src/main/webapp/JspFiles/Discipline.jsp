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
    <% 
			
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			%>
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
   result = statement.executeQuery("select * FROM \"Discipline\"");
   //result.getString("name")
   %>
   <tr><td>1</td><td>2</td><td>3</td><td>4</td></tr>
   </table>
    </div>
    </body>
    </html>