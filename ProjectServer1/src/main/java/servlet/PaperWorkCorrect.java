package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import classes.ConnectBase;

/**
 * Servlet implementation class PaperWorkCorrect
 */

@MultipartConfig(location = "C:\\Users\\micad\\Downloads\\�����\\4 ����\\���\\1\\VKR\\ProjectServer1\\src\\main\\java\\work",
fileSizeThreshold=1024*1024,
maxFileSize=1024*1024*50, 
maxRequestSize=1024*1024*5*5)


public class PaperWorkCorrect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaperWorkCorrect() {
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
//			StringBuffer requestURL = "";
//	 	   String completeURL= null;
	 	  String filename=setnameFile(request);
//	 	  if (request.getQueryString() != null) 
//		   {
//		       requestURL.append("?").append(request.getQueryString());
//		        completeURL = requestURL.toString();
//		   }
	 	   try 
	 	   {
	 	   setFileBase(request,filename);
	 	   }
	 	   catch (SQLException e) { 
			e.printStackTrace();}
		
		   response.sendRedirect("http://localhost:8080/edudoc/"); 
		}
		public String setnameFile(HttpServletRequest request)
		{
			String file_name=UUID.randomUUID().toString() + ".pdf";
			try {
				for (Part part : request.getParts()) 
				  {
				     if(part.getName().equals("file")) 
				     {
				       if (part.getSubmittedFileName()!=null) 
				       {
				    	   try 
				    	  {
							part.write(file_name);
							
				    	  } 
				    	   catch (IOException e) 
				    	{
							e.printStackTrace();
						}
				       }
				     }
				  }
			} catch (IOException | ServletException e) {
				e.printStackTrace();
			}
			return file_name;
		}
		public void setFileBase(HttpServletRequest request,String file_name) throws SQLException
		{
			Connection connect=ConnectBase.GetConnection();
			Statement statement =ConnectBase.GetStatementBase(connect);
			ResultSet result = null;
			PreparedStatement query = connect.prepareStatement( "UPDATE \"Paper_work\" SET correct_file_work=? where id_work=?;");
				query.setString( 1, (String)file_name );
				query.setInt( 2,Integer.valueOf(request.getParameter("id"))  );
				query.executeUpdate();
			
		}
	}
