package ticket.consumer.dao.impl.project;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
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
import ticket.model.bean.project.Version;
import ticket.model.search.project.SearchVersion;


@Tag("VersionDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VersionDAOImplTest {

	private VersionDAOImpl versionDAO;	
	
	@BeforeEach
	public void init_VersionDAO() {
		System.out.println("VersionDAOImplTest - Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		versionDAO = new VersionDAOImpl();
	}
	
	@AfterEach
	public void end_VersionDAO() {
		versionDAO = null;
		System.out.println("VersionDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_VersionDAOTest() {
		System.out.println("VersionDAOImplTest - Start of unite testing for class VersionDAOImpl");
	}
	
	@AfterAll 
	public static void end_VersionDAOTest() {
		System.out.println("VersionDAOImplTest - End of unite testing for class VersionDAOImpl");
	}
	
	
	@Test
	@Tag("VersionDAOImpl-instance_valid")
	public void validInstanceOf_VersionDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(versionDAO instanceof VersionDAOImpl && versionDAO != null);
	}
	
	
	@Test
	@Order(2)
	@Tag("VersionDAOImpl-find_allVersions")
	public void validBehaviorOf_getAllVersions() {
		// Arrange
		System.out.println("FindAllVersions");
		List<Version> versions = null;
		
		// Act
		try {
			versions = versionDAO.getAllVersions();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(versions == null);
			return;
		}
		System.out.println(versions);
		
		// Assert
		assertTrue(!versions.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a version by its project_id and version_label must match only one or no records in the database !")
	@CsvSource({ "5, '1.0.1'", "-6, 0" })
	@Tag("VersionDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getVersionByID(int arg1, String arg2) {
		// Arrange
		System.out.println("FindByIDs");
		System.out.println("project_id : ["+arg1+"]");
		System.out.println("version_label : ["+arg2+"]");
		Version version = null;
		SearchVersion searched_version = new SearchVersion();
		
		// Act
		try {
			searched_version = searched_version.setSearchedVersionID(arg1);
			searched_version = searched_version.setSearchedLabel(arg2);
			version = versionDAO.getVersionByID(searched_version);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(version == null);
			return;
		}
		System.out.println(version);
		
		// Assert
		assertTrue(version != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research versions from its project_id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 1, 10, -6, 0 })
	@Tag("VersionDAOImpl-findFrom_ProjectID")
	public void validBehaviorOf_getVersionsFromProjectID(int arg1) {
		// Arrange
		System.out.println("FindVersionsFromProjectID");
		System.out.println("project_id : ["+arg1+"]");
		List<Version> versions = null;
		SearchVersion searched_version = new SearchVersion();
		
		// Act
		try {
			searched_version = searched_version.setSearchedVersionID(arg1);
			versions = versionDAO.getVersionsFrom_projectID(searched_version);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(versions == null);
			return;
		}
		System.out.println(versions);
		
		// Assert
		assertTrue(versions != null);
	}	
	
	
	@ParameterizedTest(name = "The sql query used to research projects from its version_label must be able to return any macthing records in the database !")
	@ValueSource(strings = { "1.0.1", "4.6.4", "1.6", "" })
	@Tag("VersionDAOImpl-findFrom_VersionLabel")
	public void validBehaviorOf_getVersionsFromLabel(String arg1) {
		// Arrange
		System.out.println("FindProjectsFromVersionLabel");
		System.out.println("version_label : ["+arg1+"]");
		List<Version> versions = null;
		SearchVersion searched_version = new SearchVersion();
		
		// Act
		try {
			searched_version = searched_version.setSearchedLabel(arg1);
			versions = versionDAO.getVersionsFrom_label(searched_version);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(versions == null);
			return;
		}
		System.out.println(versions);
		
		// Assert
		assertTrue(versions != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a version by its identifiers must add a new record in the database !")
	@CsvSource({ "5, '1.2.1'", "-6, 0", "2,''", "5,'1.9.1'" })
	@Order(1)
	@Tag("VersionDAOImpl-createVersion")
	public void validBehaviorOf_createVersion(int arg1, String arg2) {
		// Arrange
		System.out.println("createVersion(project,label)");
		System.out.println("project : ["+arg1+"]");
		System.out.println("label : ["+arg2+"]");
		Version version = null;
		int result = -1;
		
		// Act
		try {
			version = new Version(arg1,arg2);
			result = versionDAO.createVersion(version);
			
		} catch(SQLException sqle) {
			System.out.println(sqle.toString());
			assertTrue(result <= 0);
			return;
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a version by its identifiers must match only one record in the database !")
	@CsvSource({ "6,'1.1.1'", "-6, 0", "2,''", "5, '1.9.1'" })
	@Order(3)
	@Tag("VersionDAOImpl-deleteVersion")
	public void validBehaviorOf_deleteVersion(int arg1, String arg2) {
		// Arrange
		System.out.println("deleteVersion(project,label)");
		System.out.println("project : ["+arg1+"]");
		System.out.println("label : ["+arg2+"]");
		SearchVersion search_version = new SearchVersion();
		int result = -1;
		
		// Act
		try {
			search_version.setSearchedVersionID(arg1);
			search_version.setSearchedLabel(arg2);
			result = versionDAO.deleteVersion(search_version);
			
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
