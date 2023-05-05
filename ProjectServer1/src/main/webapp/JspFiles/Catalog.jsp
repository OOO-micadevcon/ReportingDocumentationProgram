<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , ru.classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="Cp1251">
<title>Личный кабинет</title>
 <link rel="stylesheet" type="text/css" href="/public/style.css">
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
		<div>
		<%if (request.getParameter("usersearch")==null || request.getParameter("usersearch").trim().length()==0)
		{%>
			<form action="/Catalog?note=">
    <input type="text" name="usersearch" id="usersearch" value = "" />
    <button>Искать</button>
		<%}
		else
		{%>
			<form action="/Catalog?note=<%=request.getParameter("usersearch").trim()%>">
    <input type="text" name="usersearch" id="usersearch" value = "<%=request.getParameter("usersearch").trim()%>" />
    <button>Искать</button>
		<%}
			
			%>
			   
    </form>
			
		</div>
		<br>
	<div class="catalog">
	
	<% 
			
			PrintWriter pw = response.getWriter();
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			
			try {
				if(request.getParameter("usersearch")==null || request.getParameter("usersearch").trim().isEmpty()    )
				{
		        	result = statement.executeQuery("select * FROM Notes");
			 
		        	while (result.next())
		        	{%>
		        	
		        	<div class=cen >
		        	<img object-fit="cover" height="170" width="170" src="/public/product/<%=result.getString("foto") %>" alt="<%=result.getString("name")%>" width="25%"><br>
		        	<a href="/Pages?id=<%=result.getInt("id")%>"><%= result.getString("name") %> </a>
		        	<p><%= result.getInt("price")  %>₽</p>
		        	<p>дата добавления:<%= result.getString("data") %></p>
		        	<br>
		        	</div>
		        		
		        		
		        	<% } 
			}
			else
			{	String usersearch= request.getParameter("usersearch").trim().toLowerCase();
				result = statement.executeQuery("select * FROM Notes where name LIKE '%"+usersearch+"%'   ");
				if (!result.next())
	        	{%>
	        	<div>По данному запросу записи не найдены</div>
	        	
	        	<% } 
				result = statement.executeQuery("select * FROM Notes where name LIKE '%"+usersearch+"%'   ");
	        	while (result.next())
	        	{%>
	        	
	        	<div  align="left" >
	        	<img src="/public/product/<%=result.getString("foto") %>" alt="<%=result.getString("name")%>" width="25%"><br>
	        	<a href="/Pages?id=<%=result.getInt("id")%>"><%= result.getString("name") %> </a>
	        	<p><%= result.getInt("price") %>₽</p>
	        	<p>дата добавления:<%= result.getString("data") %></p>
	        	<br>
	        	</div>
	        		
	        		
	        	<% } 
			}
				}
			
			
	        catch (SQLException throwables) 
	        {
				throwables.printStackTrace();
	        }
		        	
			statement.close();
			connect.close();	
	%>
	</div>
</body>
</html>