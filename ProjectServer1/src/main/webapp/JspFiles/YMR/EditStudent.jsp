<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Добавить пользователя</title> 
   <script src="https://www.google.com/recaptcha/api.js"></script>
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
  </head>
  <body>
    <div class="page">
    <jsp:include page="JspFiles/YMR/header.jsp" />
    <div class="content" >
    <div id="login_container">
    <div id="form_container"  >
   <form action="AddUser" method="post">
 <p class="login-text">Редактировать данные студента</p>
  <input class='text_input' type="text" name="name-input" id="name-input" placeholder="Имя" maxlength="30">
  <input class='text_input' type="text" name="Fname-input" id="Fname-input" placeholder="Фамилия" maxlength="30">
  <input class='text_input' type="text" name="Oname-input" id="Oname-input" placeholder="Отчество" maxlength="30">
  <input class='text_input' type="text" name="Oname-input" id="group-input" placeholder="Группа" maxlength="30">
   <input class='text_input' type="text" name="Oname-input" id="num-rec-input" placeholder="Номер зачетной книжки" maxlength="30">
  <input class='text_input' class="create-account" type="submit" value="Отправить" onclick=""> 
  
  </form>
  </div>
</div>
      </div>
     <jsp:include page="JspFiles/YMR/footer.jsp" />
    </div>
  </body>
<script>
  function checkForm()
  {

    if (document.getElementById("password-input").value.trim() == '')
    {
    alert ('Заполните пароль');
    return false;
    }
    else if (document.getElementById("Email").value.trim() == '')
    {
    alert ('Заполните логин');
    return false;
    }
  }
  }
</script>
</html>
