<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Objects,java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<header class="header-top">
            <%if(!Objects.equals(session.getAttribute("category"),"Студент"))
{%>
	<script charset=\"utf-8\">
	alert("У вас нет прав для просмотра данной страницы!");
	window.location.href = "Autorization";</script>
<%} %>
      <div class="header-top">
      <input class="size fotobiti" type="image" src="public/images/logoBiti1.png" href="Autorization">
          <a href="Discipline" >Текущие работы</a>
		        <a href="ArchiveWorksStudent">Архив работ</a>
        <div class="header-right">
        <% if(session.getAttribute("current_name") == null)
	  	{%>
		<a href="Autorization"> Авторизация\Регистрация</a>
		
		<%} 
        else{%>
        <i class="menucolor">Студент: <%= GetCookie.GetCookie(request, "fname")+" "+GetCookie.GetCookie(request, "name").charAt(0)+". "+GetCookie.GetCookie(request, "oname").charAt(0)+"."%></i>
        <form method="POST" action="KillSession">
        <input class="size button"  type="submit" value="Выйти из аккаунта" name="kill">
        </form>
        <%}%>
        </div>
        </div>
    </header>