package ticket.consumer.dao.impl.bug;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.db.DatabaseConnectionTypes;
import ticket.consumer.db.contract.DatabaseConnection;
import ticket.consumer.db.impl.DatabaseConnectionFactoryImpl;
import ticket.model.bean.bug.Severity;
import ticket.model.search.bug.SearchSeverity;


@Tag("SeverityDAOImplClass_UniteTesting")
public class SeverityDAOImplTest {

	private SeverityDAOImpl severityDAO;
	private static int newSeverityid;
	
	@BeforeEach
	public void init_SeverityDAO() {
		System.out.println("SeverityDAOImplTest - Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		severityDAO = new SeverityDAOImpl();
	}
	
	@AfterEach
	public void end_SeverityDAO() {
		severityDAO = null;
		System.out.println("SeverityDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_SeverityDAOTest() {
		newSeverityid = -1;
		System.out.println("SeverityDAOImplTest - Start of unite testing for class SeverityDAOImpl");
	}
	
	@AfterAll 
	public static void end_SeverityDAOTest() {
		newSeverityid = -1;
		System.out.println("SeverityDAOImplTest - End of unite testing for class SeverityDAOImpl");
	}
	
	
	@Test
	@Tag("SeverityDAOImpl-instance_valid")
	public void validInstanceOf_SeverityDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(severityDAO instanceof SeverityDAOImpl && severityDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("SeverityDAOImpl-find_allSeverities")
	public void validBehaviorOf_getAllSeverities() {
		// Arrange
		System.out.println("FindAllSeverities");
		List<Severity> severities = null;
		
		// Act
		try {
			severities = severityDAO.getAllSeverities();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(severities == null);
			return;
		}
		System.out.println(severities);
		
		// Assert
		assertTrue(!severities.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a severity by id must match only one or no records in the database !")
	@ValueSource(ints = { 1, 10, -6, 0 })
	@Tag("SeverityDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getSeveritybyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Severity severity = null;
		SearchSeverity searched_severity = new SearchSeverity();
		
		// Act
		try {
			searched_severity = searched_severity.setSearchedSeverityID(arg1);
			severity = severityDAO.getSeverityByID(searched_severity);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(severity == null);
			return;
		}
		System.out.println(severity);
		
		// Assert
		assertTrue(severity != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research severities from its level must be able to return any macthing records in the database !")
	@ValueSource(ints = { 1, 10, -6, 0 })
	@Tag("SeverityDAOImpl-findfrom_Level")
	public void validBehaviorOf_getSeveritiesFromLevel(int arg1) {
		// Arrange
		System.out.println("FindFromLevel");
		System.out.println("level : ["+arg1+"]");
		List<Severity> severities = null;
		SearchSeverity searched_severity = new SearchSeverity();
		
		// Act
		try {
			searched_severity = searched_severity.setSearchedLevel(arg1);
			severities = severityDAO.getSeveritiesFrom_level(searched_severity);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(severities == null);
			return;
		}
		System.out.println(severities);
		
		// Assert
		assertTrue(severities != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research severities from its label must be able to return any macthing records in the database !")
	@ValueSource(strings = { "CRITICAL", "OPEN", "OR", "" })
	@Tag("SeverityDAOImpl-findFrom_Label")
	public void validBehaviorOf_getSeveritiesLikeLabel(String arg1) {
		// Arrange
		System.out.println("FindFromLabel");
		System.out.println("label : ["+arg1+"]");
		List<Severity> severities = null;
		SearchSeverity searched_severity = new SearchSeverity();
		
		// Act
		try {
			searched_severity = searched_severity.setSearchedLabel(arg1);
			severities = severityDAO.getSeveritiesLike_label(searched_severity);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(severities == null);
			return;
		}
		System.out.println(severities);
		
		// Assert
		assertTrue(severities != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a severity by its id must add a new record in the database !")
	@CsvSource({ "0,6,'ABSOLUTE'", "0,0,'Open'", "0,5,''", "-2,0," })
	@Order(1)
	@Tag("SeverityDAOImpl-createSeverity")
	public void validBehaviorOf_createSeverity(int arg1, int arg2, String arg3) {
		// Arrange
		System.out.println("createSeverity(id,level,label)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("level : ["+arg2+"]");		
		System.out.println("label : ["+arg3+"]");
		Severity severity = null;
		int newid = -1;
		
		// Act
		try {
			if(arg1 > 0) severity = new Severity(arg1,arg2,arg3);
			else severity = new Severity(arg2,arg3);
			newid = severityDAO.createSeverity(severity);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newSeverityid = newid;
		
		// Assert
		assertTrue(newid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a severity by its id must successfully match only one record in the database !")
	@CsvSource({  "0,0,", "0,6,'ABSOLUTE'" })
	@Order(2)
	@Tag("SeverityDAOImpl-updateSeverity")
	public void validBehaviorOf_updateSeverity(int arg1, int arg2, String arg3) {
		// Arrange
		System.out.println("updateSeverity(id,level,label)");
		if(arg1 == 0) arg1 = newSeverityid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("level : ["+arg2+"]");		
		System.out.println("label : ["+arg3+"]");
		SearchSeverity search_severity = new SearchSeverity();
		int id = -1;
		
		// Act
		try {
			search_severity.setSearchedSeverityID(arg1);
			search_severity.setSearchedLevel(arg2);
			search_severity.setSearchedLabel(arg3);
			id = severityDAO.updateSeverity(search_severity);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newSeverityid);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a severity by its id must successfully match only one record in the database !")
	@Order(4)
	@Tag("SeverityDAOImpl-deleteSeverity")
	public void validBehaviorOf_deleteSeverity() {
		// Arrange
		System.out.println("deleteSeverity(id)");
		System.out.println("id : ["+newSeverityid+"]");
		SearchSeverity search_severity = new SearchSeverity();
		int id = -1;
		
		// Act
		try {
			search_severity.setSearchedSeverityID(newSeverityid);
			id = severityDAO.deleteSeverity(search_severity);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newSeverityid);
	}

}
