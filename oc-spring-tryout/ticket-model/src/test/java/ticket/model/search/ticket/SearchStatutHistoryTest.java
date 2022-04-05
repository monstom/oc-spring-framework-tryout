package ticket.model.search.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SearchStatutHistoryTest {

	private SearchStatutHistory first_searchStatutHistory;
	
	@BeforeEach
	public void init_SearchStatutHistory() {
		System.out.println("SearchStatutHistoryTest - Appel avant chaque test");
		first_searchStatutHistory = new SearchStatutHistory();
	}
	
	@AfterEach
	public void end_SearchStatutHistory() {
		first_searchStatutHistory = null;
		System.out.println("SearchStatutHistoryTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchStatutHistoryTest() {
		System.out.println("SearchStatutHistoryTest - Début du test de classe SearchStatutHistory");
	}
	
	@AfterAll 
	public static void end_SearchStatutHistoryTest() {
		System.out.println("SearchStatutHistoryTest - Fin du test de classe SearchStatutHistory");
	}
	
	@ParameterizedTest(name = "Le ticket ainsi que le statut, le commentaire et lauteur recherchés pour un historique des statuts ({0},{1},{2},{3}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,2,3,4", "1,0,2,4", "3,1,4,0", "-4,-3,-2,0", "9,-9,5,-5", "1,3,-4,0", "-4,-2,-1,-3", "0,0,0,0" })
	@Tag("SearchStatutHistory-Aggregation_valid")
	public void validAggregationOf_SearchStatutHistory(int ticketID, int statutID, int commentID, int authorID) {
		// Arrange

		// Assert 0 
		assertTrue(this.first_searchStatutHistory.getSearchedTicket() == 0
				|| this.first_searchStatutHistory.getSearchedTicket() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedStatut() == 0
				|| this.first_searchStatutHistory.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedComment() == 0
				|| this.first_searchStatutHistory.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0
				|| this.first_searchStatutHistory.getSearchedAuthor() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedTicket(ticketID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 1
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertTrue(this.first_searchStatutHistory.getSearchedStatut() == 0
				|| this.first_searchStatutHistory.getSearchedStatut() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedComment() == 0
				|| this.first_searchStatutHistory.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0
				|| this.first_searchStatutHistory.getSearchedAuthor() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedStatut(statutID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 2 
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertTrue(this.first_searchStatutHistory.getSearchedComment() == 0
				|| this.first_searchStatutHistory.getSearchedComment() == (Integer)null);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0
				|| this.first_searchStatutHistory.getSearchedAuthor() == (Integer)null);
		
		// Act 3
		try {
			this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedComment(commentID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 3
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertSame(this.first_searchStatutHistory.getSearchedComment(),commentID);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0
				|| this.first_searchStatutHistory.getSearchedAuthor() == (Integer)null);
		
		// Act 4
		try {
			this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedAuthor(authorID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 4
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertSame(this.first_searchStatutHistory.getSearchedComment(),commentID);
		assertSame(this.first_searchStatutHistory.getSearchedAuthor(),authorID);
	}
}
