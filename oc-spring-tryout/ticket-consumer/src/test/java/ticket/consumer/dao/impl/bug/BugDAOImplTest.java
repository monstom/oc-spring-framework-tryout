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
import ticket.model.bean.bug.Bug;
import ticket.model.search.bug.SearchBug;


@Tag("BugDAOImplClass_UniteTesting")
public class BugDAOImplTest {
	
	/* IMPLEMENT ALL TEST METHODS FOR CRUD OPERATIONS OF THE DAO */

	private BugDAOImpl bugDAO;
	private static int newBugid;
	
	@BeforeEach
	public void init_BugDAO() {
		System.out.println("BugDAOImplTest - Appel avant chaque test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		bugDAO = new BugDAOImpl();
	}
	
	@AfterEach
	public void end_BugDAO() {
		bugDAO = null;
		System.out.println("BugDAOImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_BugDAOTest() {
		newBugid = -1;
		System.out.println("BugDAOImplTest - Début du test de classe BugDAOImpl");
	}
	
	@AfterAll 
	public static void end_BugDAOTest() {
		newBugid = -1;
		System.out.println("BugDAOImplTest - Fin du test de classe BugDAOImpl");
	}
	
	
	@Test
	@Tag("BugDAOImpl-instance_valid")
	public void validInstanceOf_BugDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(bugDAO instanceof BugDAOImpl && bugDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("BugDAOImpl-find_allBugs")
	public void validBehaviorOf_getAllBugs() {
		// Arrange
		System.out.println("FindAllBugs");
		List<Bug> bugs = null;
		
		// Act
		try {
			bugs = bugDAO.getAllBugs();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugs == null);
			return;
		}
		System.out.println(bugs);
		
		// Assert
		assertTrue(!bugs.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a bug by ticket id must match only one or no records in the database !")
	@ValueSource(ints = { 2, 10, -6, 0 })
	@Tag("BugDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getBugbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Bug bug = null;
		SearchBug searched_bug = new SearchBug();
		
		// Act
		try {
			searched_bug = searched_bug.setSearchedTicketID(arg1);
			bug = bugDAO.getBugByID(searched_bug);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bug == null);
			return;
		}
		System.out.println(bug);
		
		// Assert
		assertTrue(bug != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research bugs from its severity must be able to return any macthing records in the database !")
	@ValueSource(ints = { 4, 10, -6, 0 })
	@Tag("BugDAOImpl-findFrom_Severity")
	public void validBehaviorOf_getBugsFromSeverity(int arg1) {
		// Arrange
		System.out.println("FindFromSeverity");
		System.out.println("severity : ["+arg1+"]");
		List<Bug> bugsfromseverity = null;
		SearchBug searched_bug = new SearchBug();
		
		// Act
		try {
			searched_bug = searched_bug.setSearchedSeverity(arg1);
			bugsfromseverity = bugDAO.getBugsFrom_severity(searched_bug);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugsfromseverity == null);
			return;
		}
		System.out.println(bugsfromseverity);
		
		// Assert
		assertTrue(bugsfromseverity != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a bug by its ticket id must successfully add a new record in the database !")
	@CsvSource({ "1,4", "-2,10", "2,-6", "0,6", "0,0" })
	@Order(1)
	@Tag("BugDAOImpl-createBug")
	public void validBehaviorOf_createBug(int arg1, int arg2) {
		// Arrange
		System.out.println("createBug(id,severity)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("severity : ["+arg2+"]");
		Bug bug = null; 
		int newid = -1;
		
		// Act
		try {
			bug = new Bug(arg1,arg2);
			newid = bugDAO.createBug(bug);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newBugid = newid;
		
		// Assert
		assertTrue(newBugid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a bug by its ticket id must successfully match only one record in the database !")
	@CsvSource({ "-2,10", "0,10", "0,4" })
	@Order(2)
	@Tag("BugDAOImpl-updateBug")
	public void validBehaviorOf_updateBug(int arg1, int arg2) {
		// Arrange
		System.out.println("updateBug(id,severity)");
		if(arg1 == 0) arg1 = newBugid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("severity : ["+arg2+"]");
		SearchBug search_bug = new SearchBug();
		int id = -1;
		
		// Act
		try {
			search_bug.setSearchedTicketID(arg1);
			search_bug.setSearchedSeverity(arg2);
			id = bugDAO.updateBug(search_bug);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newBugid);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a bug by its ticket id must successfully match only one record in the database !")
	@Order(4)
	@Tag("BugDAOImpl-deleteBug")
	public void validBehaviorOf_deleteBug() {
		// Arrange
		System.out.println("deleteBug(id)");
		System.out.println("id : ["+newBugid+"]");
		SearchBug search_bug = new SearchBug();
		int id = -1;
		
		// Act
		try {
			search_bug.setSearchedTicketID(newBugid);
			id = bugDAO.deleteBug(search_bug);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newBugid);
	}

}
