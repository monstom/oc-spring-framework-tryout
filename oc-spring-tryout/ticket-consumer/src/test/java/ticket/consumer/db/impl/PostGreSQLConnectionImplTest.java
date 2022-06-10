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


@Tag("PostGreSQLConnectionImplClass_UniteTesting")
public class PostGreSQLConnectionImplTest {
	
	private PostGreSQLConnectionImpl postgre_con; 
	
	@BeforeEach
	public void init_PostGreSQLConnectionImpl() {
		System.out.println("PostGreSQLConnectionImplTest - Appel avant chaque test");
		postgre_con = PostGreSQLConnectionImpl.getInstance(
						  DatabaseConnectionTypes.POSTGRESQL.getDBTitle(),
						  DatabaseConnectionTypes.POSTGRESQL.getDBUser(),
						  DatabaseConnectionTypes.POSTGRESQL.getDBCredential()
					  );
	}
	
	@AfterEach
	public void end_PostGreSQLConnectionImpl() {
		postgre_con = null;
		System.out.println("PostGreSQLConnectionImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_PostGreSQLConnectionImplTest() {
		System.out.println("PostGreSQLConnectionImplTest - Début du test de classe PostGreSQLConnectionImpl");
	}
	
	@AfterAll 
	public static void end_PostGreSQLConnectionImplTest() {
		System.out.println("PostGreSQLConnectionImplTest - Fin du test de classe PostGreSQLConnectionImpl");
	}
	
	
	@Test
	@Tag("PostGreSQLConnectionImpl-instance_valid")
	public void validInstanceOf_PostGreSQLConnectionImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(postgre_con instanceof PostGreSQLConnectionImpl && !postgre_con.isOpen());
	}
	
	
	@Test
	@Tag("PostGreSQLConnectionImpl-connection_opening")
	public void openingConnectionFrom_PostGreSQLConnectionImpl() {
		// Arrange
		
		// Act
		Connection postgre = postgre_con.getConnection();
		
		// Assert
		assertTrue(postgre instanceof Connection && postgre_con.isOpen());
	}
	
	
	@Test
	@Tag("PostGreSQLConnectionImpl-connection_closing")
	public void closingConnectionFrom_PostGreSQLConnectionImpl() {
		// Arrange
		
		// Act
		Connection postgre = postgre_con.closeConnection();
		
		// Assert
		assertNull(postgre);
		assertFalse(postgre_con.isOpen());
	}
	
}
