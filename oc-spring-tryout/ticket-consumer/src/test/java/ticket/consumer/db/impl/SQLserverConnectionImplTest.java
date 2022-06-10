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


@Tag("SQLserverConnectionImplClass_UniteTesting")
public class SQLserverConnectionImplTest {
	
	private SQLServerConnectionImpl mssql_con;
	
	@BeforeEach
	public void init_SQLserverConnectionImpl() {
		System.out.println("SQLserverConnectionImplTest - Appel avant chaque test");
		mssql_con = SQLServerConnectionImpl.getInstance(
						DatabaseConnectionTypes.SQLSERVER.getDBTitle(),
						DatabaseConnectionTypes.SQLSERVER.getDBUser(),
						DatabaseConnectionTypes.SQLSERVER.getDBCredential()
					);
	}
	
	@AfterEach
	public void end_SQLserverConnectionImpl() {
		mssql_con = null;
		System.out.println("SQLserverConnectionImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SQLserverConnectionImplTest() {
		System.out.println("SQLserverConnectionImplTest - Début du test de classe SQLserverConnectionImpl");
	}
	
	@AfterAll 
	public static void end_SQLserverConnectionImplTest() {
		System.out.println("SQLserverConnectionImplTest - Fin du test de classe SQLserverConnectionImpl");
	}
	
	
	@Test
	@Tag("SQLserverConnectionImpl-instance_valid")
	public void validInstanceOf_SQLserverConnectionImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(mssql_con instanceof SQLServerConnectionImpl && !mssql_con.isOpen());
	}
	
	
	@Test
	@Tag("SQLserverConnectionImpl-connection_opening")
	public void openingConnectionFrom_SQLserverConnectionImpl() {
		// Arrange
		
		// Act
		Connection mssql = mssql_con.getConnection();
		
		// Assert
		assertTrue(mssql instanceof Connection && mssql_con.isOpen());
	}
	
	
	@Test
	@Tag("SQLserverConnectionImpl-connection_closing")
	public void closingConnectionFrom_SQLserverConnectionImpl() {
		// Arrange
		
		// Act
		Connection mssql = mssql_con.closeConnection();
		
		// Assert
		assertNull(mssql);
		assertFalse(mssql_con.isOpen());
	}
	
}
