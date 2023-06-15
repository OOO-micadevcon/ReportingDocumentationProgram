<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.SQLException, jakarta.servlet.http.Cookie , classes.*, java.io.PrintWriter, java.sql.Connection, java.sql.ResultSet, java.sql.Statement" %>
<!DOCTYPE html>
<html lang="ru" >
  <head>
   <title>Добавить дисциплину</title> 
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
   <form action="AddWork" method="post">
 <p class="login-text">Добавить письменную работу</p>
  <input class='text_input' type="text" name="semester_work-input"  placeholder="семестр" maxlength="30">
   <input class='text_input' type="text" name="date_end_work-input"  placeholder="Дата окончания" maxlength="30">
   <p class='text_input '><select class='livesearch'  required name="student-input" id="category-input">
    <option   selected disabled>Студент</option>
    <%
	Connection connect=ConnectBase.GetConnection();
	Statement statement =ConnectBase.GetStatementBase(connect);
	ResultSet result = null;
   PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Student_data\" inner join \"Person\"  ON ( \"Student_data\".person = id_person)");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_student") %>"><%=result.getString("Fname")+" "+result.getString("name") %></option>
   <%}
	  
   %>
   </select></p>
   
   <p class='text_input '><select class='livesearch'  required name="teacher-input" id="category-input">
   <option   selected disabled>Преподаватель</option>
    <%
	 connect=ConnectBase.GetConnection();
	 statement =ConnectBase.GetStatementBase(connect);
	 result = null;
    ps = connect.prepareStatement( "SELECT * FROM \"Teacher_data\" inner join \"Person\"  ON ( \"Teacher_data\".person = id_person)");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_teacher") %>"><%=result.getString("Fname")+" "+result.getString("name") %></option>
   <%}
	  
   %>
   </select></p>
   
   <p class='text_input '><select class='livesearch'  required name="type-input" id="category-input">
   <option   selected disabled>Тип работы</option>
    <%
	 connect=ConnectBase.GetConnection();
	 statement =ConnectBase.GetStatementBase(connect);
	 result = null;
    ps = connect.prepareStatement( "SELECT * FROM \"Work_type\" ");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_work_type") %>"><%=result.getString("name_type") %></option>
   <%}
	  
   %>
   </select></p>
   
   <p class='text_input '><select class='livesearch'  required name="discipline-input" id="category-input">
   <option   selected disabled>Дисциплина</option>
    <%
	 connect=ConnectBase.GetConnection();
	 statement =ConnectBase.GetStatementBase(connect);
	 result = null;
    ps = connect.prepareStatement( "SELECT * FROM \"Discipline\" ");
   result = ps.executeQuery();
   while(result.next())
   {%>
	  <option value="<%=result.getString("id_discipline") %>"><%=result.getString("name_discipline") %></option>
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
