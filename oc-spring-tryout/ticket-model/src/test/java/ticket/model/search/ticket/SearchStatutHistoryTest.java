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
	@CsvSource({ "1,2,3,4", "1,0,2,4", "0,1,4,0", "-4,-3,-2,0", "-9,9,5,-5", "0,-3,4,0", "-4,-2,-1,-3", "0,0,0,0" })
	@Tag("SearchStatutHistory-Aggregation_valid")
	public void validAggregationOf_SearchStatutHistory(int ticketID, int statutID, int commentID, int authorID) {
		// Arrange
		
		// Act 1 
		this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedTicket(ticketID);
		
		// Assert 1
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertTrue(this.first_searchStatutHistory.getSearchedStatut() == 0);
		assertTrue(this.first_searchStatutHistory.getSearchedComment() == 0);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0);
		
		// Act 2
		this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedStatut(statutID);
		
		// Assert 2 
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertTrue(this.first_searchStatutHistory.getSearchedComment() == 0);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0);
		
		// Act 3
		this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedComment(commentID);
		
		// Assert 3
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertSame(this.first_searchStatutHistory.getSearchedComment(),commentID);
		assertTrue(this.first_searchStatutHistory.getSearchedAuthor() == 0);
		
		// Act 4
		this.first_searchStatutHistory = this.first_searchStatutHistory.setSearchedAuthor(authorID);
		
		// Assert 4
		assertSame(this.first_searchStatutHistory.getSearchedTicket(),ticketID);
		assertSame(this.first_searchStatutHistory.getSearchedStatut(),statutID);
		assertSame(this.first_searchStatutHistory.getSearchedComment(),commentID);
		assertSame(this.first_searchStatutHistory.getSearchedAuthor(),authorID);
	}
}
