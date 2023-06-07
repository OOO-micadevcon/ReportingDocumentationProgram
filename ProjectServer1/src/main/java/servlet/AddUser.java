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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import classes.ConnectBase;
import classes.TimeWork;
import classes.*;
/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				request.setCharacterEncoding("Cp1251");
				response.setCharacterEncoding("Cp1251");
				String path = "/JspFiles/Admin/AddUser.jsp";
		        ServletContext servletContext = getServletContext();
		        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
		        requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String sole="SDFEGbdfbtj33";
		String SecurityPassword=PasswordEncryptionService.md5Custom(request.getParameter("password-input")+sole);
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("Cp1251");
		PrintWriter pw = response.getWriter();
		Date date=TimeWork.TimeNow();
		java.sql.Date sqlDate =  new java.sql.Date(date.getTime() );
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		int result;
		int idPer=0;
		PreparedStatement query =null;
		PreparedStatement queryRole =null;
		try {
		query = connect.prepareStatement("INSERT INTO \"Person\"(login, password,name,\"Fname\",\"Oname\",date_created,category) VALUES (?,?,?,?,?,?,?);",query.RETURN_GENERATED_KEYS);
		
				query.setString( 1,request.getParameter("login-input"));
				query.setString( 2,SecurityPassword);
				query.setString( 3,request.getParameter("name-input"));
				query.setString( 4,request.getParameter("Fname-input"));
				query.setString( 5,request.getParameter("Oname-input"));
				query.setDate( 6, sqlDate );
				query.setInt( 7,Integer.valueOf(request.getParameter("category-input")));
				result= query.executeUpdate();
				
				if(result==0)
				{	
	        		pw.println("<script charset=\"Cp1251\">");
	        		pw.println("alert(\"Ошибка при добавлении!\");");
	        		pw.println("window.location.href = \"Autorization\";</script>");
	        	}
				else
				{	
					try (ResultSet generatedKeys = query.getGeneratedKeys()) {
			            if (generatedKeys.next()) 
			            {
			            	idPer=(int) generatedKeys.getLong(1);
			            }
			           
			            }
					if(Integer.valueOf(request.getParameter("category-input"))==2)
					{
						
						queryRole = connect.prepareStatement("INSERT INTO \"Student_data\"(person) VALUES (?)");
						queryRole.setInt( 1,idPer);
						queryRole.executeUpdate();
					}
					else if(Integer.valueOf(request.getParameter("category-input"))==1)
{
						
						queryRole = connect.prepareStatement("INSERT INTO \"Teacher_data\"(person,job_title) VALUES (?,?)");
						queryRole.setInt( 1,idPer);
						queryRole.setString( 2,"к.т.н");
						queryRole.executeUpdate();
					}
					
					pw.println("<script charset=\"Cp1251\">");
	        		pw.println("alert(\"Успешно добавлен пользователь!!\");");
	        		pw.println("window.location.href = \"AddUser\";</script>");
				}
				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

}
