package classes;

import java.sql.Statement;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonData {

	public static boolean SetAttributePerson(Connection connect, String login, String password, HttpSession session) {
		 ResultSet resultCategory=null;
		 if(session==null)
			 return false;
			 try 
		 {
			 PreparedStatement query = connect.prepareStatement( "select * from \"Person\" where \"login\"="
			 		+" ? and \"password\"= ?");
				query.setString( 1, login );
				query.setString( 2, password );

				ResultSet result =  query.executeQuery();
			 if (result.next())
			 { 
	    		//за проверку
	    		session.setAttribute("id_person",result.getInt("id_person"));
	    		session.setAttribute("current_name",result.getString("name"));
	    		session.setAttribute("oname",result.getString("Oname"));
	    		session.setAttribute("fname",result.getString("Fname"));
	    		
				PreparedStatement query2 = connect.prepareStatement( "SELECT name_category\r\n"
				 		+ "FROM \"Person\"\r\n"
				 		+ "JOIN \"Category\" ON category = id_category where id_person=?");
					query2.setInt(1, (int) session.getAttribute("id_person"));
					ResultSet result2  = query2.executeQuery();
	    		if (result2.next())
		        	{
	    				session.setAttribute("category",result2.getString("name_category"));
		        	}
	    	}
			 else
				 return false;
		 } 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
    	}

	public static void SetAttributeStudent(Connection connect, int id_student, HttpSession session) {
		try {
		PreparedStatement query = connect.prepareStatement( "select * from \"Student_data\" where person=?");
		query.setInt( 1, id_student );

		ResultSet result =  query.executeQuery();
			if (result.next()){
				session.setAttribute("id_student",result.getInt("id_student"));
				session.setAttribute("group",(Integer)result.getInt("group"));
				session.setAttribute("current_semester",(Integer)result.getInt("current_semester"));
	    	}
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
        }
	
}
	public static void SetAttributeTeacher(Connection connect, int id_teacher, HttpSession session) 
	{
		try {
			PreparedStatement query = connect.prepareStatement( "select * from \"Teacher_data\" where person=?");
			query.setInt( 1, id_teacher );

			ResultSet result =  query.executeQuery();
				if (result.next()){
					session.setAttribute("id_teacher",result.getInt("id_teacher"));
					session.setAttribute("job_title",result.getString("job_title"));
		    	}
			}
			catch (SQLException throwables) {
				throwables.printStackTrace();
	        }
	}
}
