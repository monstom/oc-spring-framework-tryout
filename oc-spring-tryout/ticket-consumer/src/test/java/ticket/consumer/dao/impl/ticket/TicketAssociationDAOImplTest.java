package ticket.consumer.dao.impl.ticket;

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
import ticket.model.bean.ticket.TicketAssociation;
import ticket.model.search.ticket.SearchTicketAssociation;


@Tag("TicketAssociationDAOImplClass_UniteTesting")
public class TicketAssociationDAOImplTest {

	private TicketAssociationDAOImpl ticketAssoDAO;	
	
	@BeforeEach
	public void init_TicketAssociationDAO() {
		System.out.println("TicketAssociationDAOImplTest - Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		ticketAssoDAO = new TicketAssociationDAOImpl();
	}
	
	@AfterEach
	public void end_TicketAssociationDAO() {
		ticketAssoDAO = null;
		System.out.println("TicketAssociationDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_TicketAssociationDAOTest() {
		System.out.println("TicketAssociationDAOImplTest - Start of unite testing for class TicketAssociationDAOImpl");
	}
	
	@AfterAll 
	public static void end_TicketAssociationDAOTest() {
		System.out.println("TicketAssociationDAOImplTest - End of unite testing for class TicketAssociationDAOImpl");
	}
	
	
	@Test
	@Tag("TicketAssociationDAOImpl-instance_valid")
	public void validInstanceOf_TicketAssociationDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(ticketAssoDAO instanceof TicketAssociationDAOImpl && ticketAssoDAO != null);
	}
	
	
	@Test
	@Tag("TicketAssociationDAOImpl-find_allTicketAssociations")
	public void validBehaviorOf_getAllTicketAssociations() {
		// Arrange
		System.out.println("FindAllTicketAssociations");
		List<TicketAssociation> ticketAssos = null;
		
		// Act
		try {
			ticketAssos = ticketAssoDAO.getAllTicketAssos();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(ticketAssos == null);
			return;
		}
		System.out.println(ticketAssos);
		
		// Assert
		assertTrue(!ticketAssos.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a ticket association by both its ticket ids must match only one or no records in the database !")
	@CsvSource({ "1, 2", "-6, 0", "0, 2" })
	@Tag("TicketAssociationDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getTicketAssociationByID(int arg1, int arg2) {
		// Arrange
		System.out.println("FindByIDs");
		System.out.println("main_ticket : ["+arg1+"]");
		System.out.println("associated_ticket : ["+arg2+"]");
		TicketAssociation ticketAsso = null;
		SearchTicketAssociation searched_ticketAsso = new SearchTicketAssociation();
		
		// Act
		try {
			searched_ticketAsso = searched_ticketAsso.setSearchedTicket1AssoID(arg1);
			searched_ticketAsso = searched_ticketAsso.setSearchedTicket2AssoID(arg2);
			ticketAsso = ticketAssoDAO.getTicketAssociationByID(searched_ticketAsso);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(ticketAsso == null);
			return;
		}
		System.out.println(ticketAsso);
		
		// Assert
		assertTrue(ticketAsso != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research ticket associations from main ticket id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 1, 5, -6, 0 })
	@Tag("TicketAssociationDAOImpl-findFrom_MainTicketID")
	public void validBehaviorOf_getTicketAssociationsFromMainTicketID(int arg1) {
		// Arrange
		System.out.println("FindTicketAssociationsFromMainTicketID");
		System.out.println("main_ticket : ["+arg1+"]");
		List<TicketAssociation> ticketAssos = null;
		SearchTicketAssociation searched_ticketAsso = new SearchTicketAssociation();
		
		// Act
		try {
			searched_ticketAsso = searched_ticketAsso.setSearchedTicket1AssoID(arg1);
			ticketAssos = ticketAssoDAO.getAssociatedTicketOf_ticketID(searched_ticketAsso);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(ticketAssos == null);
			return;
		}
		System.out.println(ticketAssos);
		
		// Assert
		assertTrue(ticketAssos != null);
	}	
	
	
	@ParameterizedTest(name = "The sql query used to create an association of tickets by its identifiers must successfully add a new record in the database !")
	@CsvSource({ "-1,2", "2,10", "1,-2", "2,1", "0,0" })
	@Order(1)
	@Tag("TicketAssociationDAOImpl-createTicketAssociation")
	public void validBehaviorOf_createTicketAssociation(int arg1, int arg2) {
		// Arrange
		System.out.println("createTicketAssociation(main_ticket,associated_ticket)");
		System.out.println("main_ticket : ["+arg1+"]");
		System.out.println("associated_ticket : ["+arg2+"]");
		TicketAssociation ticketAsso = null;
		int result = -1;
		
		// Act
		try {
			ticketAsso = new TicketAssociation(arg1,arg2);
			result = ticketAssoDAO.createTicketAssociation(ticketAsso);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete an association of ticket by its identifiers must successfully match only one record in the database !")
	@CsvSource({ "-1,2", "2,10", "1,-2", "2,1", "0,0" })
	@Order(3)
	@Tag("TicketAssociationDAOImpl-deleteTicketAssociation")
	public void validBehaviorOf_deleteTicketAssociation(int arg1, int arg2) {
		// Arrange
		System.out.println("deleteTicketAssociation(main_ticket,associated_ticket)");
		System.out.println("main_ticket : ["+arg1+"]");
		System.out.println("associated_ticket : ["+arg2+"]");
		SearchTicketAssociation search_ticketAsso = new SearchTicketAssociation();
		int result = -1;
		
		// Act
		try {
			search_ticketAsso.setSearchedTicket1AssoID(arg1);
			search_ticketAsso.setSearchedTicket2AssoID(arg2);
			result = ticketAssoDAO.deleteTicketAssociation(search_ticketAsso);
			
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
