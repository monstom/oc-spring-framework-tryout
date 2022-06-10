package ticket.consumer.db.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import ticket.consumer.db.DatabaseConnectionTypes;


@Tag("MySQLConnectionImplClass_UniteTesting")
public class MySQLConnectionImplTest {
	
	private MySQLConnectionImpl mysql_con; 
	
	@BeforeEach
	public void init_MySQLConnectionImpl() {
		System.out.println("MySQLConnectionImplTest - Appel avant chaque test");
		mysql_con = MySQLConnectionImpl.getInstance(
						DatabaseConnectionTypes.MYSQL.getDBTitle(),
						DatabaseConnectionTypes.MYSQL.getDBUser(),
						DatabaseConnectionTypes.MYSQL.getDBCredential()
					);
	}
	
	@AfterEach
	public void end_MySQLConnectionImpl() {
		mysql_con = null;
		System.out.println("MySQLConnectionImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_MySQLConnectionImplTest() {
		System.out.println("MySQLConnectionImplTest - Début du test de classe MySQLConnectionImpl");
	}
	
	@AfterAll 
	public static void end_MySQLConnectionImplTest() {
		System.out.println("MySQLConnectionImplTest - Fin du test de classe MySQLConnectionImpl");
	}
	
	
	@Test
	@Tag("MySQLConnectionImpl-instance_valid")
	public void validInstanceOf_MySQLConnectionImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(mysql_con instanceof MySQLConnectionImpl && !mysql_con.isOpen());
	}
	
	
	@Test
	@Tag("MySQLConnectionImpl-connection_opening")
	public void openingConnectionFrom_MySQLConnectionImpl() {
		// Arrange
		
		// Act
		Connection mysql = mysql_con.getConnection();
		
		// Assert
		assertTrue(mysql instanceof Connection && mysql_con.isOpen());
	}
	
	
	@Test
	@Tag("MySQLConnectionImpl-connection_closing")
	public void closingConnectionFrom_MySQLConnectionImpl() {
		// Arrange
		
		// Act
		Connection mysql = mysql_con.closeConnection();
		
		// Assert
		assertNull(mysql);
		assertFalse(mysql_con.isOpen());
	}

}
