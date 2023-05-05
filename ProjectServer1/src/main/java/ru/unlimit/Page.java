package ru.unlimit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.classes.ConnectBase;
import ru.classes.GetCookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Page
 */
public class Page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//кодировка
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		//подключение jsp
		String path = "/JspFiles/Page.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("Cp1251");
//время
		Date date1 = new Date();
		SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
		String query = "INSERT INTO Comments (Notes_id, comment, name, date) VALUES (?, ?, ?, ?);";
		PrintWriter pw = response.getWriter();
		Connection connect=ConnectBase.GetConnection();
		PreparedStatement statement;
		ResultSet result = null;
        try {
        	statement = connect.prepareStatement(query);
            statement.setString(1, request.getParameter("id"));
            statement.setString(2, request.getParameter("comment"));
            statement.setString(3, GetCookie.GetCookie(request, "name"));
            statement.setString(4, formatForDateNow.format(date1).toString());
            statement.executeUpdate();
            statement.close();
        			
    			statement.close();
    			connect.close();
        	}
        catch (SQLException throwables) 
        {
			throwables.printStackTrace();
        }
        response.setIntHeader("Refresh", 0);
		}

	}


