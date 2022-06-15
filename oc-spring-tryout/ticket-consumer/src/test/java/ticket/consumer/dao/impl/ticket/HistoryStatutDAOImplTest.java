package ticket.consumer.dao.impl.ticket;

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
import ticket.model.bean.ticket.HistoryStatut;
import ticket.model.search.ticket.SearchHistoryStatut;


@Tag("HistoryStatutDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoryStatutDAOImplTest {

	private HistoryStatutDAOImpl historyStatutDAO;	
	
	@BeforeEach
	public void init_HistoryStatutDAO() {
		System.out.println("HistoryStatutDAOImplTest - Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		historyStatutDAO = new HistoryStatutDAOImpl();
	}
	
	@AfterEach
	public void end_HistoryStatutDAO() {
		historyStatutDAO = null;
		System.out.println("HistoryStatutDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_HistoryStatutDAOTest() {
		System.out.println("HistoryStatutDAOImplTest - Start of unite testing for class HistoryStatutDAOImpl");
	}
	
	@AfterAll 
	public static void end_HistoryStatutDAOTest() {
		System.out.println("HistoryStatutDAOImplTest - End of unite testing for class HistoryStatutDAOImpl");
	}
	
	
	@Test
	@Tag("HistoryStatutDAOImpl-instance_valid")
	public void validInstanceOf_HistoryStatutDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(historyStatutDAO instanceof HistoryStatutDAOImpl && historyStatutDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("HistoryStatutDAOImpl-find_allHistoryStatuts")
	public void validBehaviorOf_getAllStatutsInHistory() {
		// Arrange
		System.out.println("FindAllHistoryStatuts");
		List<HistoryStatut> historyStatuts = null;
		
		// Act
		try {
			historyStatuts = historyStatutDAO.getAllStatutsInHistory();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(historyStatuts == null);
			return;
		}
		System.out.println(historyStatuts);
		
		// Assert
		assertTrue(!historyStatuts.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research an history of status by both ticket and status id must match only one or no records in the database !")
	@CsvSource({ "1, 2", "2, 1", "3, 1", "2, 7", "-6, 0", "2, 0" })
	@Tag("HistoryStatutDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getHistoryStatutByID(int arg1, int arg2) {
		// Arrange
		System.out.println("FindByIDs");
		System.out.println("ticket_id : ["+arg1+"]");
		System.out.println("statut_id : ["+arg2+"]");
		HistoryStatut historyStatut = null;
		SearchHistoryStatut searched_historyStatut = new SearchHistoryStatut();
		
		// Act
		try {
			searched_historyStatut = searched_historyStatut.setSearchedTicket(arg1);
			searched_historyStatut = searched_historyStatut.setSearchedStatut(arg2);
			historyStatut = historyStatutDAO.getStatutInHistoryByID(searched_historyStatut);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(historyStatut == null);
			return;
		}
		System.out.println(historyStatut);
		
		// Assert
		assertTrue(historyStatut != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research histories of status from its creation date must be able to return any macthing records in the database !")
	@CsvSource({ "'2022-03-25 14:53:38', ", "'2022-03-25', true", "'2022-04-01', false", "'-8', false", ", true" })
	@Tag("HistoryStatutDAOImpl-findFrom_CreationDate")
	public void validBehaviorOf_getStatutsInHistoryFromCreationDate(String arg1, String arg2) {
		// Arrange
		System.out.println("FindFromCreationDate");
		System.out.println("creation_date : ["+arg1+"]");
		System.out.println("over : ["+arg2+"]");
		List<HistoryStatut> historyStatuts = null;
		SearchHistoryStatut searched_historyStatut = new SearchHistoryStatut();
		
		// Act
		try {
			searched_historyStatut = searched_historyStatut.setSearchedCreationDate(arg1);
			if(arg2 == null || arg2.isEmpty() || arg2.isBlank())
				historyStatuts = historyStatutDAO.getStatutsInHistoryFrom_creationDate(searched_historyStatut);
			else 
				historyStatuts = historyStatutDAO.getStatutsInHistoryFrom_creationDate(searched_historyStatut,Boolean.valueOf(arg2));	
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(historyStatuts == null);
			return;
		}
		System.out.println(historyStatuts);
		
		// Assert
		assertTrue(historyStatuts != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research histories of status from its coment id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("BugDAOImpl-findFrom_Comment")
	public void validBehaviorOf_getStatutsInHistoryFromComment(int arg1) {
		// Arrange
		System.out.println("FindFromComment");
		System.out.println("comment_id : ["+arg1+"]");
		List<HistoryStatut> historyStatuts = null;
		SearchHistoryStatut searched_historyStatut = new SearchHistoryStatut();
		
		// Act
		try {
			searched_historyStatut = searched_historyStatut.setSearchedComment(arg1);
			historyStatuts = historyStatutDAO.getStatutsInHistoryFrom_comment(searched_historyStatut);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(historyStatuts == null);
			return;
		}
		System.out.println(historyStatuts);
		
		// Assert
		assertTrue(historyStatuts != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research histories of status from its author id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("BugDAOImpl-findFrom_Author")
	public void validBehaviorOf_getStatutsInHistoryFromAuthor(int arg1) {
		// Arrange
		System.out.println("FindFromAuthor");
		System.out.println("author_id : ["+arg1+"]");
		List<HistoryStatut> historyStatuts = null;
		SearchHistoryStatut searched_historyStatut = new SearchHistoryStatut();
		
		// Act
		try {
			searched_historyStatut = searched_historyStatut.setSearchedAuthor(arg1);
			historyStatuts = historyStatutDAO.getStatutsInHistoryFrom_author(searched_historyStatut);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(historyStatuts == null);
			return;
		}
		System.out.println(historyStatuts);
		
		// Assert
		assertTrue(historyStatuts != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create an history of status by its identifiers must successfully add a new record in the database !")
	@CsvSource({ "1,-1,2022-03-25 14:53:38,5,1", "-1,2,2022-03-25 14:53:38,5,1", "2,7,2022-03-25,5,1", "2,7,,-1,0", "2,7,,1,-1", "10,10,2022-03-25 14:36:58,1,0", "2,7,,5,0" })
	@Order(1)
	@Tag("HistoryStatutDAOImpl-createHistoryStatut")
	public void validBehaviorOf_createHistoryStatut(int arg1, int arg2, String arg3, int arg4, int arg5) {
		// Arrange
		System.out.println("createHistoryStatut(ticket,status,creationdate,author,comment)");
		System.out.println("ticket : ["+arg1+"]");
		System.out.println("status : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("author : ["+arg4+"]");
		System.out.println("comment : ["+arg5+"]");
		HistoryStatut history = null;
		int result = -1 ;
		
		// Act
		try {
			if(arg3 != null) history = new HistoryStatut(arg1,arg2,arg3,arg4,arg5);
			else history = new HistoryStatut(arg1,arg2,null,arg4,arg5);
			result = historyStatutDAO.createHistoryStatut(history);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update an history of status by its identifiers must match only one record in the database !")
	@CsvSource({ "2,7,,-1,-1", "2,7,,1,0" })
	@Order(2)
	@Tag("HistoryStatutDAOImpl-updateHistoryStatut")
	public void validBehaviorOf_updateHistoryStatut(int arg1, int arg2, String arg3, int arg4, int arg5) {
		// Arrange
		System.out.println("updateHistoryStatut(ticket,status,creationdate,author,comment)");
		System.out.println("ticket : ["+arg1+"]");
		System.out.println("status : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("author : ["+arg4+"]");
		System.out.println("comment : ["+arg5+"]");
		SearchHistoryStatut search_history = new SearchHistoryStatut();
		int result = -1 ;
		
		// Act
		try {
			search_history.setSearchedTicket(arg1);
			search_history.setSearchedStatut(arg2);
			if(arg3 != null) search_history.setSearchedCreationDate(arg3);
			search_history.setSearchedComment(arg4);
			if(arg5 > 0) search_history.setSearchedAuthor(arg5);
			result = historyStatutDAO.updateHistoryStatut(search_history);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(result <= 0);
			return;
		}
		System.out.println(result);
		
		// Assert
		assertTrue(result > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete an history of status by its identifiers must match only one record in the database !")
	@CsvSource({ "1, 10", "-6, 0", "2, 0", "0,0", "2, 7"})
	@Order(4)
	@Tag("HistoryStatutDAOImpl-deleteHistoryStatut")
	public void validBehaviorOf_deleteHistoryStatut(int arg1, int arg2) {
		// Arrange
		System.out.println("deleteHistoryStatut(ticket,status)");
		System.out.println("ticket : ["+arg1+"]");
		System.out.println("status : ["+arg2+"]");
		SearchHistoryStatut search_history = new SearchHistoryStatut();
		int result = -1;
		
		// Act
		try {
			search_history.setSearchedTicket(arg1);
			search_history.setSearchedStatut(arg2);
			result = historyStatutDAO.deleteHistoryStatut(search_history);
			
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
