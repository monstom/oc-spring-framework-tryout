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


@Tag("MongoDBConnectionImplClass_UniteTesting")
public class MongoDBConnectionImplTest {
	
	private MongoDBConnectionImpl mongo_con; 
	
	@BeforeEach
	public void init_MongoDBConnectionImpl() {
		System.out.println("MongoDBConnectionImplTest - Appel avant chaque test");
		mongo_con = MongoDBConnectionImpl.getInstance(
						DatabaseConnectionTypes.MONGODB.getDBTitle(),
						DatabaseConnectionTypes.MONGODB.getDBUser(),
						DatabaseConnectionTypes.MONGODB.getDBCredential()
					);
	}
	
	@AfterEach
	public void end_MongoDBConnectionImpl() {
		mongo_con = null;
		System.out.println("MongoDBConnectionImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_MongoDBConnectionImplTest() {
		System.out.println("MongoDBConnectionImplTest - Début du test de classe MongoDBConnectionImpl");
	}
	
	@AfterAll 
	public static void end_MongoDBConnectionImplTest() {
		System.out.println("MongoDBConnectionImplTest - Fin du test de classe MongoDBConnectionImpl");
	}
	
	
	@Test
	@Tag("MongoDBConnectionImpl-instance_valid")
	public void validInstanceOf_MongoDBConnectionImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(mongo_con instanceof MongoDBConnectionImpl && !mongo_con.isOpen());
	}
	
	
	@Test
	@Tag("MongoDBConnectionImpl-connection_opening")
	public void openingConnectionFrom_MongoDBConnectionImpl() {
		// Arrange
		
		// Act
		Connection mongo = mongo_con.getConnection();
		
		// Assert
		assertTrue(mongo instanceof Connection && mongo_con.isOpen());
	}
	
	
	@Test
	@Tag("MongoDBConnectionImpl-connection_opening")
	public void closingConnectionFrom_MongoDBConnectionImpl() {
		// Arrange
		
		// Act
		Connection mongo = mongo_con.closeConnection();
		
		// Assert
		assertNull(mongo);
		assertFalse(mongo_con.isOpen());
	}
	
}
