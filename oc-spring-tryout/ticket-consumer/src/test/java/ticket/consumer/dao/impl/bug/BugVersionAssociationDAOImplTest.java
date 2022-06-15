package ticket.consumer.dao.impl.bug;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.db.DatabaseConnectionTypes;
import ticket.consumer.db.contract.DatabaseConnection;
import ticket.consumer.db.impl.DatabaseConnectionFactoryImpl;
import ticket.model.bean.bug.BugVersionAssociation;
import ticket.model.search.bug.SearchBugVersionAssociation;


@Tag("BugVersionAssociationDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BugVersionAssociationDAOImplTest {

	private BugVersionAssociationDAOImpl bugversionAssoDAO;	
	
	@BeforeEach
	public void init_BugVersionAssociationDAO() {
		System.out.println("BugVersionAssociationDAOImplTest - Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		bugversionAssoDAO = new BugVersionAssociationDAOImpl();
	}
	
	@AfterEach
	public void end_BugVersionAssociationDAO() {
		bugversionAssoDAO = null;
		System.out.println("BugVersionAssociationDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_BugVersionAssociationDAOTest() {
		System.out.println("BugVersionAssociationDAOImplTest - Start of unite testing for class BugVersionAssociationDAOImpl");
	}
	
	@AfterAll 
	public static void end_BugVersionAssociationDAOTest() {
		System.out.println("BugVersionAssociationDAOImplTest - End of unite testing for class BugVersionAssociationDAOImpl");
	}
	
	
	@Test
	@Tag("BugVersionAssociationDAOImpl-instance_valid")
	public void validInstanceOf_BugVersionAssociationDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(bugversionAssoDAO instanceof BugVersionAssociationDAOImpl && bugversionAssoDAO != null);
	}
	
	
	@Test
	@Order(2)
	@Tag("BugVersionAssociationDAOImpl-find_allBugVersionAssociations")
	public void validBehaviorOf_getAllBugVersionAssociations() {
		// Arrange
		System.out.println("FindAllBugVersionAssociations");
		List<BugVersionAssociation> bugversionAssos = null;
		
		// Act
		try {
			bugversionAssos = bugversionAssoDAO.getAllBugVersionAssociations();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugversionAssos == null);
			return;
		}
		System.out.println(bugversionAssos);
		
		// Assert
		assertTrue(!bugversionAssos.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a bug-version association by its bug_id, project_id and version_label must match only one or no records in the database !")
	@CsvSource({ "2, 5, '1.2.1'", "1, -6, '1.0.1'", "2, 5, '1.0.1", "1, 5, '1.2.1" })
	@Tag("BugVersionAssociationDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getBugVersionAssociationByID(int arg1, int arg2, String arg3) {
		// Arrange
		System.out.println("FindByIDs");
		System.out.println("bug_id : ["+arg1+"]");
		System.out.println("project_id : ["+arg2+"]");
		System.out.println("version_label : ["+arg3+"]");
		BugVersionAssociation bugversionAsso = null;
		SearchBugVersionAssociation searched_bugversionAsso = new SearchBugVersionAssociation();
		
		// Act
		try {
			searched_bugversionAsso = searched_bugversionAsso.setSearchedBugID(arg1);
			searched_bugversionAsso = searched_bugversionAsso.setBugVersionAssociation_versionParams(arg2,arg3);
			bugversionAsso = bugversionAssoDAO.getBugVersionAssociationByID(searched_bugversionAsso);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugversionAsso == null);
			return;
		}
		System.out.println(bugversionAsso);
		
		// Assert
		assertTrue(bugversionAsso != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research associated versions by bug_id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 2, 10, -6, 0 })
	@Tag("BugVersionAssociationDAOImpl-findfrom_BugID")
	public void validBehaviorOf_getBugVersionAssociationsFromBugID(int arg1) {
		// Arrange
		System.out.println("FindBugVersionAssociationsFromBugID");
		System.out.println("bug_id : ["+arg1+"]");
		List<BugVersionAssociation> bugversionAssos = null;
		SearchBugVersionAssociation searched_bugversionAsso = new SearchBugVersionAssociation();
		
		// Act
		try {
			searched_bugversionAsso = searched_bugversionAsso.setSearchedBugID(arg1);
			bugversionAssos = bugversionAssoDAO.getAssociatedVersionsFrom_bugID(searched_bugversionAsso);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugversionAssos == null);
			return;
		}
		System.out.println(bugversionAssos);
		
		// Assert
		assertTrue(bugversionAssos != null);
	}	
	
	
	@ParameterizedTest(name = "The sql query used to research associated bugs from its project_id and/or version_label must be able to return any macthing records in the database !")
	@CsvSource({ "5, '1.2.1'", "-6, '1.0.1'", "5, '1.0.1'", "1, '1.2.1'", "5, ", "5, ''", "0,1.2.1"})
	@Tag("BugVersionAssociationDAOImpl-findfrom_VersionIDs")
	public void validBehaviorOf_getBugVersionAssociationsFrom_VersionIDs(int arg1, String arg2) {
		// Arrange
		System.out.println("FindBugVersionAssociationsFromVersionIDs");
		System.out.println("version_id : ["+arg1+"]");
		System.out.println("version_label : ["+arg2+"]");
		List<BugVersionAssociation> bugversionAsso = null;
		SearchBugVersionAssociation searched_bugversionAsso = new SearchBugVersionAssociation();
		
		// Act
		try {
			searched_bugversionAsso = searched_bugversionAsso.setBugVersionAssociation_versionParams(arg1,arg2);
			bugversionAsso = bugversionAssoDAO.getAssociatedBugsFrom_versionID(searched_bugversionAsso);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(bugversionAsso == null);
			return;
		}
		System.out.println(bugversionAsso);
		
		// Assert
		assertTrue(bugversionAsso != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create an association of bug and version by their identifiers must successfully add a new record in the database !")
	@CsvSource({ "-6,5,'1.2.2'", "2,-2,''", "1,1, ", "2,5,'1.2.4'" })
	@Order(1)
	@Tag("BuvgVersionAssociationDAOImpl-createBugVersionAssociation")
	public void validBehaviorOf_createBugVersionAssociation(int arg1, int arg2, String arg3) {
		// Arrange
		System.out.println("createBugVersionAssociation(bug,project,label)");
		System.out.println("bug : ["+arg1+"]");
		System.out.println("project : ["+arg2+"]");
		System.out.println("label : ["+arg3+"]");
		BugVersionAssociation bugversionAsso = null;
		int result = -1;
		
		// Act
		try {
			bugversionAsso = new BugVersionAssociation(arg1,arg2,arg3);
			result = bugversionAssoDAO.createBugVersionAssociation(bugversionAsso);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete an association of bug and version by their identifiers must successfully match only one record in the database !")
	@CsvSource({ "2,6,'1.0.4'", "-6,5,'1.2.2'", "2,-2,''", "1,1, ", "2,5,'1.2.4'" })
	@Order(3)
	@Tag("BuvgVersionAssociationDAOImpl-deleteBugVersionAssociation")
	public void validBehaviorOf_deleteBugVersionAssociation(int arg1, int arg2, String arg3) {
		// Arrange
		System.out.println("deleteBugVersionAssociation(bug,project,label)");
		System.out.println("bug : ["+arg1+"]");
		System.out.println("project : ["+arg2+"]");
		System.out.println("label : ["+arg3+"]");
		SearchBugVersionAssociation search_bugversionAsso = new SearchBugVersionAssociation();
		int result = -1;
		
		// Act
		try {
			search_bugversionAsso.setSearchedBugID(arg1);
			search_bugversionAsso.setSearchedVersionID(arg2);
			search_bugversionAsso.setSearchedVersionLabel(arg3);
			result = bugversionAssoDAO.deleteBugVersionAssociation(search_bugversionAsso);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
}
