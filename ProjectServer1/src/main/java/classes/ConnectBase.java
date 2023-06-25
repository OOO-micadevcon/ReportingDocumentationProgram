package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBase {
	
	public static Connection GetConnection()  {
		
		 final String URL = "jdbc:postgresql://127.0.0.1:5432/edudoc";
		 final String User = "postgres";
		 final String Pass = "0000";
		Connection connectionBase = null;
        try {
        	try {
        		Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	connectionBase = DriverManager.getConnection(URL,User,Pass);
        	
        	} 
         catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connectionBase;
	}
	public static Statement GetStatementBase(Connection connectionBase)  {
		Statement statement = null;
		try {
		if(connectionBase==null)
			throw new NullPointerException("connection=null");
        	statement = connectionBase.createStatement();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
	}
}
