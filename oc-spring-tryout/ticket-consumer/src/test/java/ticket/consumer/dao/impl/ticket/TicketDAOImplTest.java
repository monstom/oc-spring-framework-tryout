package ticket.consumer.dao.impl.ticket;

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
import ticket.model.bean.ticket.Ticket;
import ticket.model.search.ticket.SearchTicket;


@Tag("TicketDAOImplClass_UniteTesting")
public class TicketDAOImplTest {

	private TicketDAOImpl ticketDAO;
	private static int newTicketid;
	
	@BeforeEach
	public void init_TicketDAO() {
		System.out.println("TicketDAOImplTest - Appel avant chaque test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		ticketDAO = new TicketDAOImpl();
	}
	
	@AfterEach
	public void end_TicketDAO() {
		ticketDAO = null;
		System.out.println("TicketDAOImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_TicketDAOTest() {
		System.out.println("TicketDAOImplTest - Début du test de classe TicketDAOImpl");
	}
	
	@AfterAll 
	public static void end_TicketDAOTest() {
		System.out.println("TicketDAOImplTest - Fin du test de classe TicketDAOImpl");
	}
	
	
	@Test
	@Tag("TicketDAOImpl-instance_valid")
	public void validInstanceOf_TicketDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(ticketDAO instanceof TicketDAOImpl && ticketDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("TicketDAOImpl-find_allTickets")
	public void validBehaviorOf_getAllTickets() {
		// Arrange
		System.out.println("FindAllTickets");
		List<Ticket> tickets = null;
		
		// Act
		try {
			tickets = ticketDAO.getAllTickets();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(!tickets.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a ticket by its id must match only one or no records in the database !")
	@ValueSource(ints = { 2, 1, -6, 0, 10 })
	@Tag("TicketDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getTicketbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Ticket ticket = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedTicketID(arg1);
			ticket = ticketDAO.getTicketByID(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(ticket == null);
			return;
		}
		System.out.println(ticket);
		
		// Assert
		assertTrue(ticket != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research tickets from its title must be able to return any macthing records in the database !")
	@ValueSource(strings = { "code review", "ticket webapp project", "request", "" })
	@Tag("TicketDAOImpl-findFrom_Title")
	public void validBehaviorOf_getTicketsLikeTitle(String arg1) {
		// Arrange
		System.out.println("FindFromTitle");
		System.out.println("title : ["+arg1+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedTitle(arg1);
			tickets = ticketDAO.getTicketsLike_title(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research tickets from its description must be able to return any macthing records in the database !")
	@ValueSource(strings = { "development", "validate", "peer review", "pull request", "" })
	@Tag("TicketDAOImpl-findLike_Description")
	public void validBehaviorOf_getTicketsLikeDescription(String arg1) {
		// Arrange
		System.out.println("FindLikeDescription");
		System.out.println("description : ["+arg1+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedDescription(arg1);
			tickets = ticketDAO.getTicketsLike_description(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research tickets from its creation date must be able to return any macthing records in the database !")
	@CsvSource({ "'2022-03-25 14:53:37', ", "'2022-03-25', true", "'2022-04-01', false", "'-8', false", ", true", "'', false" })
	@Tag("TicketDAOImpl-findLike_CreationDate")
	public void validBehaviorOf_getTicketsFromCreationDate(String arg1, String arg2) {
		// Arrange
		System.out.println("FindFromCreationDate");
		System.out.println("creation_date : ["+arg1+"]");
		System.out.println("over : ["+arg2+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedCreationDate(arg1);
			if(arg2 == null || arg2.isEmpty() || arg2.isBlank())
				tickets = ticketDAO.getTicketsFrom_creationDate(searched_ticket);
			else 
				tickets = ticketDAO.getTicketsFrom_creationDate(searched_ticket,Boolean.valueOf(arg2));
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	

	@ParameterizedTest(name = "The sql query used to research tickets from its status id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 3, 1, -6, 0, 10 })
	@Tag("TicketDAOImpl-findFrom_Statut")
	public void validBehaviorOf_getTicketsFromStatut(int arg1) {
		// Arrange
		System.out.println("FindFromStatut");
		System.out.println("statut_id : ["+arg1+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedStatut(arg1);
			tickets = ticketDAO.getTicketsFrom_statut(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research tickets from its author id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("TicketDAOImpl-findFrom_Author")
	public void validBehaviorOf_getTicketsFromAuthor(int arg1) {
		// Arrange
		System.out.println("FindFromAuthor");
		System.out.println("author_id : ["+arg1+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedAuthor(arg1);
			tickets = ticketDAO.getTicketsFrom_author(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research tickets from its project id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("TicketDAOImpl-findFrom_Project")
	public void validBehaviorOf_getTicketsFromProject(int arg1) {
		// Arrange
		System.out.println("FindFromProject");
		System.out.println("project_id : ["+arg1+"]");
		List<Ticket> tickets = null;
		SearchTicket searched_ticket = new SearchTicket();
		
		// Act
		try {
			searched_ticket = searched_ticket.setSearchedProject(arg1);
			tickets = ticketDAO.getTicketsFrom_project(searched_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(tickets == null);
			return;
		}
		System.out.println(tickets);
		
		// Assert
		assertTrue(tickets != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a ticket by its id must successfully add a new record with the highest id in the database !")
	@CsvSource({ "1,'test1',2022-03-25,,3,5,5", "0,,2022-03-25,,3,5,5", "0,'test1',,,3,5,5", "0,'test1',2022-03-25,,-3,5,5", "0,'test1',2022-03-25,,3,-5,5", "0,'test1',2022-03-25,,3,5,-5","0,'test1',2022-03-25,'test',3,5,5" })
	@Order(1)
	@Tag("TicketDAOImpl-createTicket")
	public void validBehaviorOf_createTicket(int arg1, String arg2, String arg3, String arg4, int arg5, int arg6, int arg7) {
		// Arrange
		System.out.println("createTicket(id,title,creationdate,description,status,author,project)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("title : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("description : ["+arg4+"]");
		System.out.println("status : ["+arg5+"]");
		System.out.println("author : ["+arg6+"]");
		System.out.println("project : ["+arg7+"]");
		Ticket ticket = null;
		int newid = -1;
		
		// Act
		try {
			if(arg1 > 0 && arg4 != null) ticket = new Ticket(arg1,arg2,arg3,arg4,arg5,arg6,arg7);
			else if(arg1 > 0) ticket = new Ticket(arg1,arg2,arg3,null,arg5,arg6,arg7);
			else if(arg4 != null)  ticket = new Ticket(arg2,arg3,arg4,arg5,arg6,arg7);
			else ticket = new Ticket(arg2,arg3,null,arg5,arg6,arg7);
			newid = ticketDAO.createTicket(ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newTicketid = newid;
		
		// Assert
		assertTrue(newTicketid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a ticket by its id must successfully match only one record in the database !")
	@CsvSource({ "0,,,,-3,-5,-5", "0,'test2',,,0,0,0" })
	@Order(2)
	@Tag("TicketDAOImpl-updateTicket")
	public void validBehaviorOf_updateTicket(int arg1, String arg2, String arg3, String arg4, int arg5, int arg6, int arg7) {
		// Arrange
		System.out.println("updateTicket(id,title,creationdate,description,status,author,project)");
		if(arg1 == 0) arg1 = newTicketid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("title : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("description : ["+arg4+"]");
		System.out.println("status : ["+arg5+"]");
		System.out.println("author : ["+arg6+"]");
		System.out.println("project : ["+arg7+"]");
		SearchTicket search_ticket = new SearchTicket();
		int id = -1;
		
		// Act
		try {
			search_ticket.setSearchedTicketID(arg1);
			if(arg2 != null) search_ticket.setSearchedTitle(arg2);
			if(arg3 != null) search_ticket.setSearchedCreationDate(arg3);
			if(arg4 != null) search_ticket.setSearchedDescription(arg4);
			if(arg5 > 0) search_ticket.setSearchedStatut(arg5);
			if(arg6 > 0) search_ticket.setSearchedAuthor(arg6);
			if(arg7 > 0) search_ticket.setSearchedProject(arg7);
			id = ticketDAO.updateTicket(search_ticket);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		newTicketid = id;
		
		// Assert
		assertSame(id,newTicketid);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a ticket by its id must successfully match only one record in the database !")
	@Order(4)
	@Tag("TicketDAOImpl-deleteTicket")
	public void validBehaviorOf_deleteTicket() {
		// Arrange
		System.out.println("deleteTicket(id)");
		System.out.println("id : ["+newTicketid+"]");
		SearchTicket search_ticket = new SearchTicket();
		int id = -1;
		
		// Act
		try {
			search_ticket.setSearchedTicketID(newTicketid);
			id = ticketDAO.deleteTicket(search_ticket);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newTicketid);
	}

}
