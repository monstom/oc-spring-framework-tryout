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


@Tag("SearchHistoryStatutClass_UniteTesting")
public class SearchHistoryStatutTest {

	private SearchHistoryStatut first_searchHistoryStatut;
	
	@BeforeEach
	public void init_SearchHistoryStatut() {
		System.out.println("SearchHistoryStatutTest - Initialization before each test ");
		first_searchHistoryStatut = new SearchHistoryStatut();
	}
	
	@AfterEach
	public void end_SearchHistoryStatut() {
		first_searchHistoryStatut = null;
		System.out.println("SearchHistoryStatutTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchHistoryStatutTest() {
		System.out.println("SearchHistoryStatutTest - Start of unite testing for class SearchHistoryStatut");
	}
	
	@AfterAll 
	public static void end_SearchHistoryStatutTest() {
		System.out.println("SearchHistoryStatutTest - End of unite testing for class SearchHistoryStatut");
	}
	
	@ParameterizedTest(name = "The researched primary ticket/status and the foreign comment/user keys of the history of status ({0},{1},{2},{3}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,2,3,4", "1,0,2,4", "3,1,4,0", "-4,-3,-2,0", "9,-9,5,-5", "1,3,-4,0", "-4,-2,-1,-3", "0,0,0,0" })
	@Tag("SearchHistoryStatut-Aggregation_valid")
	public void validAggregationOf_SearchHistoryStatut(int ticketID, int statutID, int commentID, int authorID) {
		// Arrange

		// Assert 0 
		assertTrue(this.first_searchHistoryStatut.getSearchedTicket() == 0
				|| this.first_searchHistoryStatut.getSearchedTicket() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedStatut() == 0
				|| this.first_searchHistoryStatut.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedComment() == 0
				|| this.first_searchHistoryStatut.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedAuthor() == 0
				|| this.first_searchHistoryStatut.getSearchedAuthor() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchHistoryStatut = this.first_searchHistoryStatut.setSearchedTicket(ticketID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchHistoryStatut.getSearchedTicket() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchHistoryStatut.getSearchedTicket(),ticketID);
		assertTrue(this.first_searchHistoryStatut.getSearchedStatut() == 0
				|| this.first_searchHistoryStatut.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedComment() == 0
				|| this.first_searchHistoryStatut.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedAuthor() == 0
				|| this.first_searchHistoryStatut.getSearchedAuthor() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchHistoryStatut = this.first_searchHistoryStatut.setSearchedStatut(statutID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchHistoryStatut.getSearchedStatut() == 0);
			return;
		}
		
		// Assert 2 
		assertSame(this.first_searchHistoryStatut.getSearchedTicket(),ticketID);
		assertSame(this.first_searchHistoryStatut.getSearchedStatut(),statutID);
		assertTrue(this.first_searchHistoryStatut.getSearchedComment() == 0
				|| this.first_searchHistoryStatut.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchHistoryStatut.getSearchedAuthor() == 0
				|| this.first_searchHistoryStatut.getSearchedAuthor() == (Integer)null);
		
		// Act 3
		try {
			this.first_searchHistoryStatut = this.first_searchHistoryStatut.setSearchedComment(commentID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchHistoryStatut.getSearchedComment() == 0);
			return;
		}
		
		// Assert 3
		assertSame(this.first_searchHistoryStatut.getSearchedTicket(),ticketID);
		assertSame(this.first_searchHistoryStatut.getSearchedStatut(),statutID);
		assertSame(this.first_searchHistoryStatut.getSearchedComment(),commentID);
		assertTrue(this.first_searchHistoryStatut.getSearchedAuthor() == 0
				|| this.first_searchHistoryStatut.getSearchedAuthor() == (Integer)null);
		
		// Act 4
		try {
			this.first_searchHistoryStatut = this.first_searchHistoryStatut.setSearchedAuthor(authorID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchHistoryStatut.getSearchedAuthor() == 0);
			return;
		}
		
		// Assert 4
		assertSame(this.first_searchHistoryStatut.getSearchedTicket(),ticketID);
		assertSame(this.first_searchHistoryStatut.getSearchedStatut(),statutID);
		assertSame(this.first_searchHistoryStatut.getSearchedComment(),commentID);
		assertSame(this.first_searchHistoryStatut.getSearchedAuthor(),authorID);
	}
}
