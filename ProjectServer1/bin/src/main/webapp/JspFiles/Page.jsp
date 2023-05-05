<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , ru.classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="Cp1251">
 <link rel="stylesheet" type="text/css" href="/public/login.css">
    <link rel="stylesheet" type="text/css" href="/public/style.css">
<title>Товар</title>
</head>
<body>
<header>
      <div class="header-top">
          <a href="/" >главная</a>
		        <a href="/Catalog"> Магазин</a>
		         <a href="/Support" >Поддержка</a></span>
        <div class="header-right">
        <% if(session.getAttribute("current_name") == null)
	  	{%>
		<a href="/Autorization"> Авторизация\Регистрация</a>
		
		<%} 
        else{%>
        Пользователь:<%= GetCookie.GetCookie(request, "name")%>
        <form method="POST" action="/Autorization">
        <input  type="submit" value="Выйти из аккаунта" name="kill">
        </form>
        <%}%>
        </div>
        </div>
        
    </header>

	
	<div  class="page-title">
	<% 
			String id=request.getParameter("id");
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			try {
		        	result = statement.executeQuery("select * FROM Notes where id="+id);
			 
		        	while (result.next())
		        	{%>
		        	
		        	<div  class="page-data" >
		        	<div class="page-margin">
		        		<img src="/public/product/<%=result.getString("foto") %>" alt="<%=result.getString("name")%>" width="300px"><br>
			        	<p><%= result.getInt("price") %>₽</p>
				        <p>дата добавления:<%= result.getString("data") %></p>
				       <%
			if(session.getAttribute("current_name") == null)
	  	{%>
        				<input  type="submit" value="Купить" onclick="alert('Вы не авторизованы')" name="buy">
        				<%} 
			else{
        				%>
        				<input  type="submit" value="Купить" onclick="alert('<%= GetCookie.GetCookie(request, "name")%>, на данный момент продажи прекращены.')" name="buy">
		        	<%} %>
		        	</div>
			        	
		        	<div>
			        	<strong><p><%= result.getString("name") %> </p></strong>
			        	<br>
			        	<p><%= result.getString("description") %></p>
		        	</div>
		        	<br>
		        	</div>
		        		
		        		
		        	<% } 
		        		
			}
        	
			
	        catch (SQLException throwables) 
	        {
				throwables.printStackTrace();
	        }
		        	
		        	
	%>
	</div>
	<div class="page-title">
	Последние комментарии:
	<br><br>
	<hr>
	<%
	ResultSet resultComment = null;
			try {
				resultComment = statement.executeQuery("select * FROM Comments where Notes_id="+id);
				if (!resultComment.next())
	        	{%>
	        	<div>Комментарии еще никто оставил. Мы можете стать первым!</div>
	        	<br><br>
	        	<hr>
	        	<% } 
				resultComment = statement.executeQuery("select * FROM Comments where Notes_id="+id);
		        	while (resultComment.next())
		        	{%>
		        	
		        	<div  align="left" >
		        	
		        	<p><%= resultComment.getString("name") %></p>
		        	<p><%= resultComment.getString("comment") %></p>
		        	<p align="right"><%= resultComment.getString("date") %></p>
		        	<hr>
		        	</div>
		        		
		        		
		        	<% } 
		        		
			}
        	
			
	        catch (SQLException throwables) 
	        {
				throwables.printStackTrace();
	        }
			statement.close();
			connect.close();
			%>
			
			
			</div>
			<br><br>
			<div class="page-title" align="center">
			<%
			if(session.getAttribute("current_name") == null)
	  	{%>
	  	
		Писать комментарии могут только авторизированные пользователи
		<br><a href='/Autorization'>Войти\Зарегистрироваться</a>
		
<%}
	else { %>
			<form action="" method="post">
			<div> Комментарий: </div>
			<input type="text" name="comment" value="" + "size="30">
			<button type="submit">Отправить комментарий</button>
			</form>
			<%}%>
			</div>
</body>
</html>