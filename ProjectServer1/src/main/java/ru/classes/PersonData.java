package ru.classes;

import java.sql.Statement;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonData {
	public static boolean SetAttributePerson(Statement statement, String login, String password, HttpSession session) {
		 ResultSet result = null;
		 ResultSet resultCategory=null;
		 if(session==null)
			 return false;
			 try 
		 {
			 result = statement.executeQuery("select * from \"Person\" where "+"login= '"+
					 login+"' and "+ "password= '"+
					 password+ "' ;");
			 if (result.next())
			 { 
	    		//за проверку
	    		session.setAttribute("id_person",result.getInt("id_person"));
	    		session.setAttribute("current_name",result.getString("name"));
	    		session.setAttribute("oname",result.getString("Oname"));
	    		session.setAttribute("fname",result.getString("Fname"));
	    		
				resultCategory= statement.executeQuery("SELECT name_category\r\n"
						 		+ "FROM \"Person\"\r\n"
						 		+ "JOIN \"Category\" ON category = id_category where id_person="+session.getAttribute("id_person")+";");
				
	    		if (resultCategory.next())
		        	{
	    				session.setAttribute("category",resultCategory.getString("name_category"));
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

	public static void SetAttributeStudent(Statement statement, int id_student, HttpSession session) {
		try {
		ResultSet result= statement.executeQuery("select * from \"Student_data\" where person="+id_student+";");
		
			if (result.next()){
				session.setAttribute("id_student",result.getInt("id_student"));
				session.setAttribute("group",result.getInt("group"));
				session.setAttribute("current_semester",(Integer)result.getInt("current_semester"));
	    	}
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
        }
	
}
	public static void SetAttributeTeacher(Statement statement, int id_teacher, HttpSession session) {
		
}
	}
