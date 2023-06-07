<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<header class="header-top">
            
      <div class="header-top">
          <a href="GroupYMR" >Архив ВКР</a>
		        <a href="EditStudent">Добавление в группу</a>
		        <a href="">Добавление преподавателя в группу</a>
		        <a href="">добавление дисциплины</a>
		        <a href="">добавление группы</a>
		        <a href="">добавление работы</a>
        <div class="header-right">
        <% if(session.getAttribute("current_name") == null)
	  	{%>
		<a href="Autorization"> Авторизация\Регистрация</a>
		
		<%} 
        else{%>
        Специалист по УМР: <%= GetCookie.GetCookie(request, "name")%>
        <form method="POST" action="KillSession">
        <input class="size button"  type="submit" value="Выйти из аккаунта" name="kill">
        </form>
        <%}%>
        </div>
        </div>
    </header>