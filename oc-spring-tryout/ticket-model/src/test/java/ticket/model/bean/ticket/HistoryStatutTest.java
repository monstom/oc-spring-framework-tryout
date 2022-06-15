package ticket.model.bean.ticket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("HistoryStatutClass_UniteTesting")
public class HistoryStatutTest {
	
	private HistoryStatut first_history;
	
	
	@BeforeEach
	public void init_HistoryStatut() {
		System.out.println("HistoryStatutTest - Initialization before each test ");
		first_history = new HistoryStatut();
	}
	
	@AfterEach
	public void end_HistoryStatut() {
		first_history = null;
		System.out.println("HistoryStatutTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_HistoryStatutTest() {
		System.out.println("HistoryStatutTest - Start of unite testing for class HistoryStatut");
	}
	
	@AfterAll 
	public static void end_HistoryStatutTest() {
		System.out.println("HistoryStatutTest - End of unite testing for class HistoryStatut");
	}
	
	
	@ParameterizedTest(name = "Both primary and foreign ticket/status keys of the history of status ({0},{1}) must not be negative or equal 0 !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("HistoryStatut-primaryKeys_StatutTicket_invalidValue")
	public void invalidValueOf_PrimaryKeys_StatutID_TicketID(int arg1, int arg2) {
		// Arrange
		
		// Act
		try {
			first_history.setHistory_statutID(arg1);
			first_history.setHistory_ticketID(arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_history.getHistory_statutID() <= 0
					|| first_history.getHistory_ticketID() <= 0);
			return;
		}
		
		// Assert
		assertTrue(first_history.getHistory_statutID() > 0
				&& first_history.getHistory_ticketID() > 0);
	}
	
	
	@ParameterizedTest(name = "The creation date of the history of status ({0}) must be defined in a correct format !")
	@ValueSource(strings = { "2022-03-17 17:15:23", "2022-03-17", "-8", " " })
	@Tag("HistoryStatut-creationDate_invalidValue")
	public void isEmpty_CreationDate(String arg1) {
		// Arrange
		
		// Act
		try { 
			first_history.setHistory_creationDate(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_history.getHistory_creationDate() == null);
			return;
		}
				
		// Assert
		assertTrue(first_history.getHistory_creationDate() == null
				|| first_history.getHistory_creationDate().getTime() > 0);
	}
	

	@ParameterizedTest(name = "The foreign comment key of the history of status ({0}) must not be negative or equal 0")
	@ValueSource(ints = { 10, 0, -8 })
	@Tag("HistoryStatut-foreignKey_Comment_invalidValue")
	public void invalidValueOf_ForeignKey_CommentID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_history.setHistory_commentID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_history.getHistory_commentID() <= 0);
			return;
		}
		
		// Assert
		assertTrue(first_history.getHistory_commentID() >= 0);
	}
	

	@ParameterizedTest(name = "The foreign user key of the history of status ({0}) must not be negative or equal 0")
	@ValueSource(ints = { 10, 0, -8 })
	@Tag("HistoryStatut-foreignKey_User_invalidValue")
	public void invalidValueOf_ForeignKey_UserID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_history.setHistory_userID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_history.getHistory_userID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_history.getHistory_userID() > 0);
	}
}
