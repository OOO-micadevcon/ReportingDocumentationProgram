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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import classes.ConnectBase;
import classes.TimeWork;

/**
 * Servlet implementation class AddWork
 */
public class AddWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWork() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		String path = "/JspFiles/YMR/AddWork.jsp";
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
		String s=request.getParameter("date_end_work-input");
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("dd.MM.yyyy");
		try {
			Date docDate= format.parse(s);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date date=TimeWork.TimeNow();
		java.sql.Date sqlDate =  new java.sql.Date(date.getTime() );

		try {
			query = connect.prepareStatement("INSERT INTO \"Paper_work\"(student,teacher,type,discipline,date_end_work,semester_work) VALUES (?,?,?,?,?,?);");
			query.setInt( 1,Integer.valueOf(request.getParameter("student-input")));
			query.setInt( 2,Integer.valueOf(request.getParameter("teacher-input")));
			query.setInt( 3,Integer.valueOf(request.getParameter("type-input")));
			query.setInt( 4,Integer.valueOf(request.getParameter("discipline-input")));
			query.setDate( 5,sqlDate);
			query.setInt( 6,Integer.valueOf(request.getParameter("semester_work-input")));
			query.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		pw.println("<script charset=\"Cp1251\">");
		pw.println("alert(\"Успешно!\");");
		pw.println("window.location.href = \"AddWork\";</script>");
	}
}