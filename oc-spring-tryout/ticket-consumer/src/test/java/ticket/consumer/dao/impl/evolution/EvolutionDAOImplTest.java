package ticket.consumer.dao.impl.evolution;

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
import ticket.model.bean.evolution.Evolution;
import ticket.model.search.evolution.SearchEvolution;


@Tag("EvolutionDAOImplClass_UniteTesting")
public class EvolutionDAOImplTest {

	private EvolutionDAOImpl evolutionDAO;
	private static int newEvolutionid;
	
	@BeforeEach
	public void init_EvolutionDAO() {
		System.out.println("EvolutionDAOImplTest - Appel avant chaque test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		evolutionDAO = new EvolutionDAOImpl();
	}
	
	@AfterEach
	public void end_EvolutionDAO() {
		evolutionDAO = null;
		System.out.println("EvolutionDAOImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_EvolutionDAOTest() {
		newEvolutionid = -1;
		System.out.println("EvolutionDAOImplTest - Début du test de classe EvolutionDAOImpl");
	}
	
	@AfterAll 
	public static void end_EvolutionDAOTest() {
		newEvolutionid = -1;
		System.out.println("EvolutionDAOImplTest - Fin du test de classe EvolutionDAOImpl");
	}
	
	
	@Test
	@Tag("EvolutionDAOImpl-instance_valid")
	public void validInstanceOf_EvolutionDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(evolutionDAO instanceof EvolutionDAOImpl && evolutionDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("EvolutionDAOImpl-find_allEvolutions")
	public void validBehaviorOf_getAllEvolutions() {
		// Arrange
		System.out.println("FindAllEvolutions");
		List<Evolution> evolutions = null;
		
		// Act
		try {
			evolutions = evolutionDAO.getAllEvolutions();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(evolutions == null);
			return;
		}
		System.out.println(evolutions);
		
		// Assert
		assertTrue(!evolutions.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research an evolution by ticket id must match only one or no records in the database !")
	@ValueSource(ints = { 2, 10, -6, 0 })
	@Tag("EvolutionDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getEvolutionbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Evolution evolution = null;
		SearchEvolution searched_evolution = new SearchEvolution();
		
		// Act
		try {
			searched_evolution = searched_evolution.setSearchedTicketID(arg1);
			evolution = evolutionDAO.getEvolutionByID(searched_evolution);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(evolution == null);
			return;
		}
		System.out.println(evolution);
		
		// Assert
		assertTrue(evolution != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research evolutions from its priority must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 10, -6, 0 })
	@Tag("EvolutionDAOImpl-findFrom_Priority")
	public void validBehaviorOf_getEvolutionsFromPriority(int arg1) {
		// Arrange
		System.out.println("FindFromPriority");
		System.out.println("priority : ["+arg1+"]");
		List<Evolution> evolutionsfrompriority = null;
		SearchEvolution searched_evolution = new SearchEvolution();
		
		// Act
		try {
			searched_evolution = searched_evolution.setSearchedPriority(arg1);
			evolutionsfrompriority = evolutionDAO.getEvolutionsFrom_priority(searched_evolution);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(evolutionsfrompriority == null);
			return;
		}
		System.out.println(evolutionsfrompriority);
		
		// Assert
		assertTrue(evolutionsfrompriority != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a evolution by its ticket id must successfully add a new record in the database !")
	@CsvSource({ "1,4", "-2,10", "2,-6", "0,6", "0,0" })
	@Order(1)
	@Tag("EvolutionDAOImpl-createEvolution")
	public void validBehaviorOf_createEvolution(int arg1, int arg2) {
		// Arrange
		System.out.println("createEvolution(id,priority)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("priority : ["+arg2+"]");
		Evolution evolution = null; 
		int newid = -1;
		
		// Act
		try {
			evolution = new Evolution(arg1,arg2);
			newid = evolutionDAO.createEvolution(evolution);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newEvolutionid = newid;
		
		// Assert
		assertTrue(newEvolutionid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a evolution by its ticket id must successfully match only one record in the database !")
	@CsvSource({ "-2,10", "0,10", "0,4" })
	@Order(2)
	@Tag("EvolutionDAOImpl-updateEvolution")
	public void validBehaviorOf_updateEvolution(int arg1, int arg2) {
		// Arrange
		System.out.println("updateEvolution(id,priority)");
		if(arg1 == 0) arg1 = newEvolutionid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("priority : ["+arg2+"]");
		SearchEvolution search_evolution = new SearchEvolution();
		int id = -1;
		
		// Act
		try {
			search_evolution.setSearchedTicketID(arg1);
			search_evolution.setSearchedPriority(arg2);
			id = evolutionDAO.updateEvolution(search_evolution);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newEvolutionid);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a evolution by its ticket id must successfully match only one record in the database !")
	@Order(4)
	@Tag("EvolutionDAOImpl-deleteEvolution")
	public void validBehaviorOf_deleteEvolution() {
		// Arrange
		System.out.println("deleteEvolution(id)");
		System.out.println("id : ["+newEvolutionid+"]");
		SearchEvolution search_evolution = new SearchEvolution();
		int id = -1;
		
		// Act
		try {
			search_evolution.setSearchedTicketID(newEvolutionid);
			id = evolutionDAO.deleteEvolution(search_evolution);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newEvolutionid);
	}

}
