<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Добавить группу</title> 
   <script src="https://www.google.com/recaptcha/api.js"></script>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
  </head>
  <body>
    <div class="page">
    <jsp:include page="header.jsp" />
    <div class="content" >
    <div id="login_container">
    <div id="form_container"  >
   <form action="AddGroup" method="post">
 <p class="login-text">Добавить группу</p>
  <input class='text_input' type="text" name="group-input"  placeholder="Название группы" maxlength="30">
  <input class='text_input' type="text" name="period-input"  placeholder="Период обучения" maxlength="30">
  <input class='text_input' class="create-account" type="submit" value="Отправить" onclick=""> 
  
  </form>
  </div>
</div>
      </div>
     <jsp:include page="footer.jsp" />
    </div>
  </body>

</html>
