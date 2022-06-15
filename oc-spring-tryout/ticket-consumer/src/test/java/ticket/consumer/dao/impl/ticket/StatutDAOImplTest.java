package ticket.consumer.dao.impl.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
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
import ticket.model.bean.ticket.Statut;
import ticket.model.search.ticket.SearchStatut;


@Tag("StatutDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatutDAOImplTest {

	private StatutDAOImpl statutDAO;
	private static int newStatutid;
	
	@BeforeEach
	public void init_StatutDAO() {
		System.out.println("StatutDAOImplTest -  Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		statutDAO = new StatutDAOImpl();
	}
	
	@AfterEach
	public void end_StatutDAO() {
		statutDAO = null;
		System.out.println("StatutDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_StatutDAOTest() {
		System.out.println("StatutDAOImplTest - Start of unite testing for class StatutDAOImpl");
	}
	
	@AfterAll 
	public static void end_StatutDAOTest() {
		System.out.println("StatutDAOImplTest - End of unite testing for class StatutDAOImpl");
	}
	
	
	@Test
	@Tag("StatutDAOImpl-instance_valid")
	public void validInstanceOf_StatutDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(statutDAO instanceof StatutDAOImpl && statutDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("StatutDAOImpl-find_allStatuts")
	public void validBehaviorOf_getAllStatuts() {
		// Arrange
		System.out.println("FindAllStatuts");
		List<Statut> statuts = null;
		
		// Act
		try {
			statuts = statutDAO.getAllStatuts();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(statuts == null);
			return;
		}
		System.out.println(statuts);
		
		// Assert
		assertTrue(!statuts.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a status by id must match only one or no records in the database !")
	@ValueSource(ints = { 1, 10, -6, 0 })
	@Tag("StatutDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getStatutbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Statut statut = null;
		SearchStatut searched_statut = new SearchStatut();
		
		// Act
		try {
			searched_statut = searched_statut.setSearchedStatutID(arg1);
			statut = statutDAO.getStatutByID(searched_statut);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(statut == null);
			return;
		}
		System.out.println(statut);
		
		// Assert
		assertTrue(statut != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research status from its label must be able to return any macthing records in the database !")
	@ValueSource(strings = { "Closed", "Open", "RE", "" })
	@Tag("StatutDAOImpl-findFrom_Label")
	public void validBehaviorOf_getStatutsFromLabel(String arg1) {
		// Arrange
		System.out.println("FindFromLabel");
		System.out.println("label : ["+arg1+"]");
		List<Statut> statuts = null;
		SearchStatut searched_statut = new SearchStatut();
		
		// Act
		try {
			searched_statut = searched_statut.setSearchedLabel(arg1);
			statuts = statutDAO.getStatutsLike_label(searched_statut);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(statuts == null);
			return;
		}
		System.out.println(statuts);
		
		// Assert
		assertTrue(statuts != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a status by its id must successfully add a new record with the highest id in the database !")
	@CsvSource({ "1,'UPDATED'", "2, ", "0,''", "-2,'UPDATED'" })
	@Order(1)
	@Tag("StatutDAOImpl-createStatut")
	public void validBehaviorOf_createStatut(int arg1, String arg2) {
		// Arrange
		System.out.println("createStatut(id,severity)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("severity : ["+arg2+"]");
		Statut statut = null;
		int newid = -1;
		
		// Act
		try {
			if(arg1 > 0) statut = new Statut(arg1,arg2);
			else statut = new Statut(arg2);
			newid = statutDAO.createStatut(statut);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newStatutid = newid;
		
		// Assert
		assertTrue(newid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a status by its id must successfully match only one record in the database !")
	@CsvSource({ "-2,'DELETED'", "0," , "0,'DELETED'"})
	@Order(2)
	@Tag("StatutDAOImpl-updateStatut")
	public void validBehaviorOf_updateStatut(int arg1, String arg2) {
		// Arrange
		System.out.println("updateStatut(id,severity)");
		if(arg1 == 0) arg1 = newStatutid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("severity : ["+arg2+"]");
		SearchStatut search_statut = new SearchStatut();
		int id = -1;
		
		// Act
		try {
			search_statut.setSearchedStatutID(arg1);
			if(arg2 != null) search_statut.setSearchedLabel(arg2);
			id = statutDAO.updateStatut(search_statut);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newStatutid);
	}
	
	
	@Test
	@Order(4)
	@Tag("StatutDAOImpl-deleteStatut")
	public void validBehaviorOf_deleteStatut() {
		// Arrange
		System.out.println("deleteStatut(id)");
		System.out.println("id : ["+newStatutid+"]");
		SearchStatut search_statut = new SearchStatut();
		int id = -1;
		
		// Act
		try {
			search_statut.setSearchedStatutID(newStatutid);
			id = statutDAO.deleteStatut(search_statut);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newStatutid);
	}
}
