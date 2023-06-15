package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableCell;
import com.spire.doc.TableRow;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;
import com.spire.doc.documents.RowAlignment;
import com.spire.doc.documents.VerticalAlignment;
import com.spire.doc.fields.TextRange;

import classes.ConnectBase;
import classes.TimeWork;

/**
 * Servlet implementation class CreateReport
 */
public class CreateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReport() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String Path="C:\\Users\\micad\\Downloads\\учеба\\4 курс\\ВКР\\1\\VKR\\ProjectServer1\\src\\main\\java\\report\\";
		String name=UUID.randomUUID().toString()+".docx";
		String fileName=Path+name;
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
	
		ResultSet result = null;
		ResultSet resultTeacher = null;
		String Fname=null;
		
	        //Создать документ Word
	        Document document = new Document();
	        try 
	        {
	        PreparedStatement psTeacher = connect.prepareStatement( "SELECT * FROM \"Paper_work\" inner join \"Teacher_data\" ON (teacher = id_teacher) inner join \"Person\" ON (\"Teacher_data\".person =id_person  ) where teacher = ?" );
			   psTeacher.setInt( 1, (Integer)session.getAttribute("id_teacher") );
			   resultTeacher = psTeacher.executeQuery();	
			   if ( resultTeacher.next())
			     {
					Fname=resultTeacher.getString("Fname")+" "+resultTeacher.getString("name").charAt(0)+"."+resultTeacher.getString("Oname").charAt(0)+".";
				}
			}
	        catch (SQLException e) {
		e.printStackTrace();}
		try {
	        PreparedStatement ps = connect.prepareStatement( "SELECT * FROM \"Student_data\"inner join \"Groups\" ON ( \"Student_data\".group = \"Groups\".id_group) inner join \"Person\" ON (person = id_person) inner join \"Paper_work\" on (student=id_student) inner join \"Work_type\" ON (\"type\" = id_work_type)"
	        		+ "inner join \"Discipline\" ON (discipline = id_discipline)"
	        		+ "inner join \"Teacher_data\" ON (teacher = id_teacher) "
	        		+ "where  current_semester=? and \"group\"=? and discipline=?" );
	        
			ps.setInt( 1, Integer.parseInt(request.getParameter("semester")));
	        ps.setInt( 2, Integer.parseInt(request.getParameter("group")));
	        ps.setInt( 3, Integer.parseInt(request.getParameter("disciplines")));
	        result = ps.executeQuery();
	        if(result.next())
	        {
	        //Добавить раздел
	        Section section = document.addSection();

	        //Добавить 4 абзаца в раздел
	        Paragraph para1 = section.addParagraph();
	        para1.appendText("МИНИСТЕРСТВО НАУКИ И ВЫСШЕГО ОБРАЗОВАНИЯ РОССИЙСКОЙ ФЕДЕРАЦИИ\r\n"
	        		+ "ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ АВТОНОМНОЕ ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ ВЫСШЕГО ОБРАЗОВАНИЯ\r\n "
	        		+ "«Национальный исследовательский ядерный университет «МИФИ»\r\n"
	        		+ "Балаковский инженерно-технологический институт \r\n"
	        		+ "филиал федерального государственного автономного образовательного учреждения высшего образования «Национальный исследовательский ядерный университет «МИФИ\r\n"
	        		+ ""
	        		+ "");
	        Paragraph para4 = section.addParagraph();
	        para4.appendText("ВЕДОМОСТЬ ПО ДИСЦИПЛИНЕ( "+result.getString("name_type")+")");
	        Paragraph para7 = section.addParagraph();
	        para7.appendText("Учебный год: "+TimeYearsLastNow() +"/"+TimeYearsNow());
	        Paragraph para5 = section.addParagraph();
	        para5.appendText("Группа: "+result.getString("name_group"));
	        Paragraph para2 = section.addParagraph();
	        para2.appendText("Преподаватель: "+Fname);
	        Paragraph para6 = section.addParagraph();
	        para6.appendText("Семестр:"+result.getString("semester_work"));

	        Paragraph para3 = section.addParagraph();
	        para3.appendText("Дисциплина:"+result.getString("name_discipline"));
	        String[] header = {"Фамилия", "№ зач. книжки", "Баллы", "Оценка", "ECTS","Подпись"};
	        Table table = section.addTable(true);
	        table.resetCells(1, 6);
	        TableRow row = table.getRows().get(0);
	        
	        
	        for (int i = 0; i < 6; i++) {
	            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
	            Paragraph p = row.getCells().get(i).addParagraph();
	            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
	            TextRange txtRange = p.appendText(header[i]);
	            txtRange.getCharacterFormat().setBold(true);
	        }
	        
	        PreparedStatement ps2 = connect.prepareStatement( "SELECT * FROM \"Student_data\"inner join \"Groups\" ON ( \"Student_data\".group = \"Groups\".id_group) inner join \"Person\" ON (person = id_person) inner join \"Paper_work\" on (student=id_student) inner join \"Work_type\" ON (\"type\" = id_work_type)"
	        		+ "inner join \"Discipline\" ON (discipline = id_discipline)"
	        		+ "inner join \"Teacher_data\" ON (teacher = id_teacher) "
	        		+ "where  current_semester=? and \"group\"=? and discipline=?" );
			ps2.setInt( 1, Integer.parseInt(request.getParameter("semester")));
	        ps2.setInt( 2, Integer.parseInt(request.getParameter("group")));
	        ps2.setInt( 3, Integer.parseInt(request.getParameter("disciplines")));
	        result = ps2.executeQuery();
	        while(result.next())
	        {
	        	TableRow row1 = new TableRow(document);
	            TableCell tc = row1.addCell();
	            Paragraph paragraph = tc.addParagraph();
	            paragraph.appendText(result.getString("Fname")+" "+result.getString("name").charAt(0)+"."+result.getString("Oname").charAt(0)+".");
	            
	            tc = row1.addCell();
	            paragraph = tc.addParagraph();
	            paragraph.appendText(result.getString("num_rec"));
	            
	            tc = row1.addCell();
	            paragraph = tc.addParagraph();
	            if(result.getString("assessment")==null)
	            	paragraph.appendText("-");
	            else
	            	paragraph.appendText(result.getString("assessment"));
	            tc = row1.addCell();
	            paragraph = tc.addParagraph();
	            paragraph.appendText(getAssesment(result.getString("assessment")));
	            
	            tc = row1.addCell();
	            paragraph = tc.addParagraph();
	            paragraph.appendText(getECTS(result.getString("assessment")) );
	            
	            tc = row1.addCell();
	            paragraph = tc.addParagraph();
	            paragraph.appendText(" ");
	            
	            table.getRows().add(row1);
	        }
	        Paragraph para11 = section.addParagraph();
	        para11.appendText("\r\n");
	        
	        String[] Stringnum1 = {"Ниже 60", "60-64", "65-69", "70-74", "75-84","85-89","90-100"};
	        String[] Stringnum2 = {"неудовл.", "удовлетв.", "", "хорошо", "","","отлично"};
	        String[] Stringnum3 = {"F", "E", "D", "", "C","B","A"};
	        Table table1 = section.addTable(true);
	        TableRow row2 = new TableRow(document);
	        for (int i = 0; i < 7; i++) {
	        	
	            TableCell tc = row2.addCell();
	            Paragraph paragraph = tc.addParagraph();
	            paragraph.appendText(Stringnum1[i]);
	        }
	        table1.getRows().add(row2);
	        TableRow row3 = new TableRow(document);
	        for (int i = 0; i < 7; i++) {
	        	TableCell tc = row3.addCell();
	            Paragraph paragraph = tc.addParagraph();
	            paragraph.appendText(Stringnum2[i]);
	        }
	        table1.getRows().add(row3);
	        TableRow row4 = new TableRow(document);
	        for (int i = 0; i < 7; i++) {
	        	TableCell tc = row4.addCell();
	            Paragraph paragraph = tc.addParagraph();
	            paragraph.appendText(Stringnum3[i]);
	        }
	        table1.getRows().add(row4);
	        table1.applyHorizontalMerge(1, 1, 2);
	        table1.applyHorizontalMerge(1, 3, 5);
	        table1.applyHorizontalMerge(2, 2, 3);
	        Paragraph para8 = section.addParagraph();
	        para8.appendText("\n\n\n  Декан ФАЭТ/__________/С.Н. Грицюк");
	        // первый абзац в качестве заголовка и отформатируйте заголовок
	        ParagraphStyle style1 = new ParagraphStyle(document);
	        table1.getTableFormat().setHorizontalAlignment(RowAlignment.Right);
	        style1.setName("titleStyle");
	        style1.getCharacterFormat().setBold(true);
	        style1.getCharacterFormat().setTextColor(Color.black);
	        style1.getCharacterFormat().setFontName("Times New Roman");
	        style1.getCharacterFormat().setFontSize(10f);
	        document.getStyles().add(style1);
	        para1.applyStyle("titleStyle");
	        ParagraphStyle style4 = new ParagraphStyle(document);
	        style4.setName("titleStyle4");
	        
	        style4.getCharacterFormat().setTextColor(Color.black);
	        style4.getCharacterFormat().setFontName("Times New Roman");
	        style4.getCharacterFormat().setFontSize(12f);
	        document.getStyles().add(style4);
	        para4.applyStyle("titleStyle4");

			

	        //абзац 1 и абзац 4 для выравнивания по центру по горизонтали
	        para1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
			 para4.getFormat().setHorizontalAlignment(HorizontalAlignment.Center); 
			 
				
	        //Установите пробел после абзаца 1, 2 и 3
	        para1.getFormat().setAfterSpacing(15f);
	        para2.getFormat().setAfterSpacing(10f);
	        para3.getFormat().setAfterSpacing(10f);

	        //Сохраните документ
	        document.saveToFile(fileName, FileFormat.Docx);
	        
	        }
	        SendFile(response,request,name);
		} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
	public void SendFile(HttpServletResponse response, HttpServletRequest request,String name) throws IOException 
	{
		String Path="C:\\Users\\micad\\Downloads\\учеба\\4 курс\\ВКР\\1\\VKR\\ProjectServer1\\src\\main\\java\\report\\";
		/* String name=request.getParameter("id"); */
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
	public String getAssesment(String assesment) 
	{
		if(assesment==null)
			return "-";
		else if(Integer.valueOf(assesment)<60)
		return "неудовлетворительно";
		else if(Integer.valueOf(assesment)<70)
			return "удовлетворительно";
		else if(Integer.valueOf(assesment)<90)
			return "хорошо";
		else
			return "отлично";
	}
	public String getECTS(String assesment) 
	{
		if(assesment==null)
			return "-";
		else if(Integer.valueOf(assesment)<60)
		return "F";
		else if(Integer.valueOf(assesment)<65)
			return "E";
		else if(Integer.valueOf(assesment)<75)
			return "D";
		else if(Integer.valueOf(assesment)<85)
			return "C";
		else if(Integer.valueOf(assesment)<90)
			return "B";
		else
			return "A";
	}
	public static Integer TimeYearsNow()
	{
		Calendar now = Calendar.getInstance();
	    return now.get(Calendar.YEAR);
	}
	public static Integer TimeYearsLastNow()
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.YEAR, -1);
	    return now.get(Calendar.YEAR);
	}

}
