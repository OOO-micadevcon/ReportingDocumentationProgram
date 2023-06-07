<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<header class="header-top">
            
      <div class="header-top">
          <a href="DisciplineTeacher" >Текущие работы</a>
		        <a href="ArchiveWorksTeacher">Архив работ</a>
        <div class="header-right">
        <% if(session.getAttribute("current_name") == null)
	  	{%>
		<a href="Autorization"> Авторизация\Регистрация</a>
		
		<%} 
        else{%>
        Преподаватель:<%= GetCookie.GetCookie(request, "name")%>
        <form method="POST" action="KillSession">
        <input class="size button"  type="submit" value="Выйти из аккаунта" name="kill">
        </form>
        <%}%>
        </div>
        </div>
    </header>