package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import classes.ConnectBase;
import classes.PersonData;

class test {

	@Test
	public void SetAttributePersonNull() {
		HttpSession session = null;
		Connection connect=ConnectBase.GetConnection();
		Statement statement =ConnectBase.GetStatementBase(connect);
		PersonData junit= new PersonData();
		String login="test";
		String password="test";
		assertEquals(false, junit.SetAttributePerson( connect,  login,  password, session));
		try {
			connect.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void GetStatementBaseNull() throws IOException {
		Connection connect=null;
		try {
			ConnectBase junit= new ConnectBase();
			junit.GetStatementBase( connect);
			Assert.fail("Expected IOException");
		} catch (NullPointerException thrown) {
			Assert.assertNotEquals("", thrown.getMessage());
		}
	}

}
