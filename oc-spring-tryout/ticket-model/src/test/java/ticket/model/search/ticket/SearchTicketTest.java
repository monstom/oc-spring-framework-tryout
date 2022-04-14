package ticket.model.search.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SearchTicketTest {

	private SearchTicket first_searchTicket;
	
	@BeforeEach
	public void init_SearchTicket() {
		System.out.println("SearchTicketTest - Initialization before each test ");
		first_searchTicket = new SearchTicket();
	}
	
	@AfterEach
	public void end_SearchTicket() {
		first_searchTicket = null;
		System.out.println("SearchTicketTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchTicketTest() {
		System.out.println("SearchTicketTest - Start of unite testing for class SearchTicket");
	}
	
	@AfterAll 
	public static void end_SearchTicketTest() {
		System.out.println("SearchTicketTest - End of unite testing for class SearchTicket");
	}
	
	@ParameterizedTest(name = "The researched primary key and the foreign statut/author/project keys of the ticket ({0},{1},{2},{3}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,2,3,4", "1,0,2,4", "3,1,4,0", "-4,-3,-2,0", "9,-9,5,-5", "1,3,-4,0", "-4,-2,-1,-3", "0,0,0,0" })
	@Tag("SearchTicket-Aggregation_valid")
	public void validAggregationOf_SearchTicket(int ticketID, int statutID, int projectID, int authorID) {
		// Arrange
		
		// Assert 0
		assertTrue(this.first_searchTicket.getSearchedTicketID() == 0
				|| this.first_searchTicket.getSearchedTicketID() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedStatut() == 0
				|| this.first_searchTicket.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedProject() == 0
				|| this.first_searchTicket.getSearchedProject() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedAuthor() == 0
				|| this.first_searchTicket.getSearchedAuthor() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchTicket = this.first_searchTicket.setSearchedTicketID(ticketID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchTicket.getSearchedTicketID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchTicket.getSearchedTicketID(),ticketID);
		assertTrue(this.first_searchTicket.getSearchedStatut() == 0
				|| this.first_searchTicket.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedProject() == 0
				|| this.first_searchTicket.getSearchedProject() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedAuthor() == 0
				|| this.first_searchTicket.getSearchedAuthor() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchTicket = this.first_searchTicket.setSearchedStatut(statutID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchTicket.getSearchedStatut() == 0);
			return;
		}
		
		// Assert 2 
		assertSame(this.first_searchTicket.getSearchedTicketID(),ticketID);
		assertSame(this.first_searchTicket.getSearchedStatut(),statutID);
		assertTrue(this.first_searchTicket.getSearchedProject() == 0
				|| this.first_searchTicket.getSearchedProject() == (Integer)null);
		assertTrue(this.first_searchTicket.getSearchedAuthor() == 0
				|| this.first_searchTicket.getSearchedAuthor() == (Integer)null);
		
		// Act 3
		try {
			this.first_searchTicket = this.first_searchTicket.setSearchedProject(projectID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchTicket.getSearchedProject() == 0);
			return;
		}
		
		// Assert 3 
		assertSame(this.first_searchTicket.getSearchedTicketID(),ticketID);
		assertSame(this.first_searchTicket.getSearchedStatut(),statutID);
		assertSame(this.first_searchTicket.getSearchedProject(),projectID);
		assertTrue(this.first_searchTicket.getSearchedAuthor() == 0
				|| this.first_searchTicket.getSearchedAuthor() == (Integer)null);
		
		// Act 4
		try {
			this.first_searchTicket = this.first_searchTicket.setSearchedAuthor(authorID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchTicket.getSearchedAuthor() == 0);
			return;
		}
		
		// Assert 4
		assertSame(this.first_searchTicket.getSearchedTicketID(),ticketID);
		assertSame(this.first_searchTicket.getSearchedStatut(),statutID);
		assertSame(this.first_searchTicket.getSearchedProject(),projectID);
		assertSame(this.first_searchTicket.getSearchedAuthor(),authorID);
	}

}
