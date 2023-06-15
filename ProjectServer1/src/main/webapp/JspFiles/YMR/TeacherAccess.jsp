<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Добавить доступ преподавателю</title> 
    <link rel="stylesheet" type="text/css" href="public/login.css">
    <link rel="stylesheet" type="text/css" href="public/style.css">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.5.1/chosen.min.css">

  </head>
  <body>
    <div class="page">
    <jsp:include page="header.jsp" />
    <div class="content" >
    <div id="login_container">
    <div id="form_container"  >
   <form action="TeacherAccess" method="post">
 <p class="login-text">Добавить доступ преподавателю</p>
  <input class='text_input' type="text" name="semester_work-input"  placeholder="семестр" maxlength="30">
   <p class='text_input '><select class='livesearch'  required name="teacher-input" id="category-input">
    <option   selected disabled>Преподаватель</option>
    <%
	Connection connect=ConnectBase.GetConnection();
	Statement statement =ConnectBase.GetStatementBase(connect);
	ResultSet result = null;
   PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Teacher_data\" inner join \"Person\"  ON ( \"Teacher_data\".person = id_person)");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_teacher") %>"><%=result.getString("Fname")+" "+result.getString("name") %></option>
   <%}
	  
   %>
   </select></p>
   
   <p class='text_input '><select class='livesearch'  required name="discipline-input" >
   <option   selected disabled>Дисциплина</option>
    <%
	 connect=ConnectBase.GetConnection();
	 statement =ConnectBase.GetStatementBase(connect);
	 result = null;
    ps = connect.prepareStatement( "SELECT * FROM \"Group_access\" inner join \"Group_disciplines\"  ON (group_disciplines = id_group_disciplines) inner join \"Groups\" ON ( \"Group_disciplines\".group = \"Groups\".id_group) inner join \"Discipline\" ON disciplines = id_discipline");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_group_access") %>"><%=result.getString("semester") %> <%=result.getString("name_discipline") %> <%=result.getString("name_group") %></option>
   <%}
	  
   %>
   </select></p>
   
   
   
  <input class='text_input' class="create-account" type="submit" value="Отправить" onclick=""> 
  </form>
  </div>
</div>
      </div>
     <jsp:include page="footer.jsp" />
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.5.1/chosen.jquery.min.js"></script>
<script type="text/javascript">
      $(".livesearch").chosen();
</script>
  </body>

</html>
