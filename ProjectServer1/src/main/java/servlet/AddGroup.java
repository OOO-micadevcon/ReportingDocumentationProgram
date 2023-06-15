package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import classes.ConnectBase;
import classes.PasswordEncryptionService;
import classes.TimeWork;

/**
 * Servlet implementation class AddGroup
 */
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		String path = "/JspFiles/YMR/AddGroup.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("Cp1251");
		PrintWriter pw = response.getWriter();
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		int result;
		PreparedStatement query =null;
		
		try {
			query = connect.prepareStatement("INSERT INTO \"Groups\"(name_group, training_period) VALUES (?,?);");
			query.setString( 1,request.getParameter("group-input"));
			query.setInt( 2,Integer.valueOf(request.getParameter("period-input")));
			query.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		pw.println("<script charset=\"Cp1251\">");
		pw.println("alert(\"Успешно!\");");
		pw.println("window.location.href = \"AddGroup\";</script>");
	}
}
