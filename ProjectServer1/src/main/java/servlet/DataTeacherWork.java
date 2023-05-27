package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.ConnectBase;
import classes.PersonData;

/**
 * Servlet implementation class DataTeacherWork
 */
public class DataTeacherWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataTeacherWork() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("Cp1251");
		response.setCharacterEncoding("Cp1251");
		PrintWriter pw = response.getWriter();
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		ResultSet result = null;
		String requestURL = request.getHeader("referer");
	 	String completeURL= null;
		HttpSession session = request.getSession();
		 if (request.getQueryString() != null) 
		   {
		        completeURL = requestURL.toString();
		   }
			if(request.getParameter("assessment")!=null & request.getParameter("id_work")!=null& request.getParameter("comment")!=null&request.getParameter("link_AP")!=null)
				{
				
				try {
				PreparedStatement query = connect.prepareStatement( "UPDATE \"Paper_work\" SET \"link_AP\"=?,assessment=?,comment=? where id_work=?;");
					query.setString( 1, (String)request.getParameter("link_AP") );
					query.setInt( 2,Integer.valueOf(request.getParameter("assessment")));
					query.setString( 3,(String)request.getParameter("comment"));
					query.setInt( 4,Integer.valueOf(request.getParameter("id_work")));
					
					query.executeUpdate();
					response.sendRedirect(completeURL); 
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			else {
				{
	        		pw.println("<script charset=\"utf-8\">");
	        		pw.println("alert(\"Ошибка на сервере!"+request.getParameter("assessment")+request.getParameter("id_work")+request.getParameter("comment")+"\");");
	        		pw.println("window.location.href = \""+completeURL+"\";</script>");
	        	}
			}
	}

}
