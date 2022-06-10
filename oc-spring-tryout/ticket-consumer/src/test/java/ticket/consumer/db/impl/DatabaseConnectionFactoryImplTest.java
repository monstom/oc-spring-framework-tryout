package ticket.consumer.db.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import ticket.consumer.db.DatabaseConnectionTypes;


@Tag("DatabaseConnectionFactoryImplClass_UniteTesting")
public class DatabaseConnectionFactoryImplTest {
	
	private AbstractDatabaseConnectionImpl mysql_connector;
	
	@BeforeEach
	public void init_DatabaseConnectionFactoryImpl() {
		System.out.println("DatabaseConnectionFactoryImplTest - Appel avant chaque test");
		mysql_connector = null;
	}
	
	@AfterEach
	public void end_DatabaseConnectionFactoryImpl() {
		mysql_connector = null;
		System.out.println("DatabaseConnectionFactoryImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_DatabaseConnectionFactoryImplTest() {
		System.out.println("DatabaseConnectionFactoryImplTest - Début du test de classe DatabaseConnectionFactoryImpl");
	}
	
	@AfterAll 
	public static void end_DatabaseConnectionFactoryImplTest() {
		System.out.println("DatabaseConnectionFactoryImplTest - Fin du test de classe DatabaseConnectionFactoryImpl");
	}
	
	
	@Test
	@Tag("DatabaseConnectionFactoryImpl-connector_valid")
	public void validConnectorOf_DatabaseConnectionFactoryImpl() {
		// Arrange
		
		// Act
		mysql_connector = (AbstractDatabaseConnectionImpl) new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		
		// Assert
		assertNotNull(mysql_connector);
		assertTrue(mysql_connector instanceof MySQLConnectionImpl);
		assertFalse(mysql_connector.isOpen());
	}

}
