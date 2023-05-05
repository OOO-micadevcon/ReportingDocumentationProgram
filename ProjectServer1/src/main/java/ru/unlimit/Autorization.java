package ru.unlimit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.classes.ConnectBase;
import ru.classes.PersonData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


public class Autorization extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public Autorization() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//кодировка
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		//подключение jsp
		String path = "/JspFiles/login.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//кодировка
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		
		PrintWriter pw = response.getWriter();
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		ResultSet result = null;
		ResultSet resultCategory = null;
		if(request.getParameter( "kill" )!=null)
			{
			HttpSession session = request.getSession();
			session.setAttribute("current_name",null);
			Cookie cookie = new Cookie("name",null);
			response.sendRedirect("index");	
			
			session.setMaxInactiveInterval(-1);
			}
		else 
			{
			HttpSession session = request.getSession();
    		if(PersonData.SetAttributePerson(statement, request.getParameter("Email"), request.getParameter("Password"), session))
				{
				
	    		// за выдачу имени
	    		Cookie cookie = new Cookie("name",(String) session.getAttribute("current_name"));
	    		cookie.setMaxAge(6000);
	    		response.addCookie(cookie);
		    		if(session.getAttribute("category")=="Студент")
		    			PersonData.SetAttributeStudent(statement, (Integer)session.getAttribute("id_person"),  session);
		    		else if(session.getAttribute("category")=="Преподаватель")
		    			;
		    		response.sendRedirect("Discipline"); 
				}
	        else 
    	        	{
    	        		pw.println("<script charset=\"utf-8\">");
    	        		pw.println("alert(\"Неверные данные!\");");
    	        		pw.println("window.location.href = \"Autorization\";</script>");
    	        	}
    			try 
    				{
    				connect.close();
					statement.close();
					
    				} 
    			catch (SQLException e) 
    				{
					// TODO Auto-generated catch block
					e.printStackTrace();
    				}
    			
			
		/* try {
			 int id_person=0;
			 result = statement.executeQuery("select * from \"Person\" where "+"login= '"+ request.getParameter("Email")+"' and "+ "password= '"+ request.getParameter("Password")+ "' ;");
			 */
	        	/*if (result.next())
	        	{ */
	        		//за проверку
					/*
					 * id_person=result.getInt("id_person");
					 * session.setAttribute("current_name",result.getString("name"));
					 * session.setAttribute("oname",result.getString("Oname"));
					 * session.setAttribute("fname",result.getString("Fname"));
					 */
					/*
					 * resultCategory= statement.executeQuery("SELECT name_category\r\n" +
					 * "FROM \"Person\"\r\n" + "JOIN \"Category\" ON "+id_person+" = id_category;");
					 * if (resultCategory.next()) {
					 * session.setAttribute("category",result.getString("name_category")); }
					 */
		       /* }*/
	        /*}*/
		      /*  catch (SQLException throwables) 
		        {
					throwables.printStackTrace();
		        }*/
		}
	}

}
