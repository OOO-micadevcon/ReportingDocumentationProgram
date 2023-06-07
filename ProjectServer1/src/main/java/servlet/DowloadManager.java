package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.rmi.server.ExportException;

/**
 * Servlet implementation class DowloadManager
 */
public class DowloadManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowloadManager() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SendFile(response,request);
	    }
	
	public void SendFile(HttpServletResponse response, HttpServletRequest request) throws IOException 
	{
		String Path="C:\\Users\\micad\\Downloads\\учеба\\4 курс\\ВКР\\1\\VKR\\ProjectServer1\\src\\main\\java\\work\\";
		String name=request.getParameter("id");
		String fileName=Path+name;
		File file = new File(fileName);
	    ServletOutputStream outputStream = null;
	    BufferedInputStream inputStream = null;
	    try {
	        outputStream = response.getOutputStream();
			response.setContentType("application/pdf");//application/vnd.ms-excel
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
	        response.setContentLength((int) file.length());
	        FileInputStream fileInputStream = new FileInputStream(file);
	        inputStream = new BufferedInputStream(fileInputStream);
	        int readBytes = 0;
	        while ((readBytes = inputStream.read()) != -1)
	            outputStream.write(readBytes);
	    }catch (ExportException e){
	        e.printStackTrace();
	    }finally {
	        outputStream.flush();
	        outputStream.close();
	        inputStream.close();
	}
 
	}
}
