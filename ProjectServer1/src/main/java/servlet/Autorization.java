package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import classes.ConnectBase;
import classes.PersonData;
import classes.VerifyRecaptcha;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Autorization extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public Autorization() {
        super();
        // TODO Auto-generated constructor stub
    }
    //<a href="javascript:history.back()" class="add"> Вернуться</a>
	
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
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		
		//кодировка
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		
		PrintWriter pw = response.getWriter();
		if(!verify)
		{
			pw.println("<script charset=\"utf-8\">");
    		pw.println("alert(\"Капча не пройдена!\");");
    		pw.println("window.location.href = \"Autorization\";</script>");
		}
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		ResultSet result = null;
		ResultSet resultCategory = null;
		HttpSession session = request.getSession();
    		if(PersonData.SetAttributePerson(connect, request.getParameter("Email"), request.getParameter("Password"), session))
				{
	    		// за выдачу имени
	    		Cookie cookie = new Cookie("name",(String) session.getAttribute("current_name"));
	    		cookie.setMaxAge(6000);
	    		response.addCookie(cookie);
		    		if(Objects.equals(session.getAttribute("category"),"Студент"))
		    		{
		    			PersonData.SetAttributeStudent(connect, (Integer)session.getAttribute("id_person"),  session);
		    			response.sendRedirect("Discipline");
		    		}
		    			
		    		else if(Objects.equals(session.getAttribute("category"),"Преподаватель"))
		    		{
		    			PersonData.SetAttributeTeacher(connect, (Integer)session.getAttribute("id_person"),  session);
		    			response.sendRedirect("DisciplineTeacher");
		    		}
		    			
		    		 
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
					e.printStackTrace();
    				}
		}
	}

