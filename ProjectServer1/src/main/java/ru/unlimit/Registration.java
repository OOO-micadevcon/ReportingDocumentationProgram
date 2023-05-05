package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.classes.*;
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Registration() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{			
		//кодировка
				request.setCharacterEncoding("Cp1251");
				response.setCharacterEncoding("Cp1251");
				//подключение jsp
				String path = "/JspFiles/Registration.jsp";
		        ServletContext servletContext = getServletContext();
		        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
		        requestDispatcher.forward(request, response);
//		//кодировка
//			request.setCharacterEncoding("Cp1251");
//			response.setCharacterEncoding("Cp1251");
//				

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("Cp1251");
		String query = "INSERT INTO Autorization (email, пароль, имя, фамилия, отчество,пол, организация, номер_телефона, страна, город) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?);";
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		Connection connect=ConnectBase.GetConnection();
		PreparedStatement statement;
		
		ResultSet result = null;
        try {
//	          public void insertUser(String login, String password, String name, String surname, String gender, String city, String photo) throws SQLException {
//            String query = "INSERT INTO users (name, surname, gender, login, password, city, photo) VALUES (?, ?, ?, ?, ?, ?, ?);";
//            
//            PreparedStatement statement = connect.dbConnector().prepareStatement(query);
//            statement.setString(1, name);
//            statement.setString(2, surname);
//            statement.setString(3, gender);
//            statement.setString(4, login);
//            statement.setString(5, password);
//            statement.setString(6, city);
//            statement.setString(7, photo);
//            statement.executeUpdate();
//            statement.close();
//        }	
        	statement = connect.prepareStatement(query);
           statement.setString(1, request.getParameter("Email"));
           statement.setString(2, request.getParameter("Password"));
           statement.setString(3, request.getParameter("Name"));
           statement.setString(4, request.getParameter("Fname"));
           statement.setString(5, request.getParameter("Oname"));
           statement.setString(6, request.getParameter("Gender"));
           statement.setString(7, request.getParameter("Organization"));
           statement.setString(8, request.getParameter("Number"));
           statement.setString(9, request.getParameter("Country"));
           statement.setString(10, request.getParameter("Town"));
           statement.executeUpdate();
           statement.close();
//        	statement.execute("INSERT INTO Autorization (email, пароль, имя, фамилия, отчество,"
//        			+ "пол, организация, номер_телефона, страна, город)"
//        			+ "VALUES ( '"+request.getParameter("Email")+"', "
//        			+"'"+request.getParameter("Password")+"', "
//        			+"'"+request.getParameter("Name")+"', "
//        			+"'"+request.getParameter("Fname")+"', "
//        			+"'"+request.getParameter("Oname")+"', "        			
//        			+"'"+request.getParameter("Gender")+"', "
//        			+"'"+request.getParameter("Organization")+"', "        					
//        			+"'"+request.getParameter("Number")+"', "
//        			+"'"+request.getParameter("Country")+"', "
//        			+"'"+request.getParameter("Town")+"' "		
//        			+");");
    			
        			
    			statement.close();
    			connect.close();
        	}

        

        catch (SQLException throwables) 
        {
			throwables.printStackTrace();
        }
        pw.println("alert(\"Неверные данные\");\n");
        response.sendRedirect("http://storemica.jelastic.regruhosting.ru/Autorization");	
//        pw.println("Данные занесены!");
//        pw.println(request.getParameter("Name"));
//        pw.println("</html>");
		}

}
