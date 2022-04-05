package ticket.model.search.bug;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.model.search.ticket.SearchTicketTest;


public class SearchBugTest extends SearchTicketTest {

	private SearchBug first_searchBug;
	
	@BeforeEach
	public void init_SearchBug() {
		System.out.println("SearchBugTest - Appel avant chaque test");
		first_searchBug = new SearchBug();
	}
	
	@AfterEach
	public void end_SearchBug() {
		first_searchBug = null;
		System.out.println("SearchBugTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchBugTest() {
		System.out.println("SearchBugTest - Début du test de classe SearchBug");
	}
	
	@AfterAll 
	public static void end_SearchBugTest() {
		System.out.println("SearchBugTest - Fin du test de classe SearchBug");
	}
	
	@ParameterizedTest(name = "La sévérité recherchée pour un bug ({0}) doit permettre l'agrégation des attributs de recherche !")
	@ValueSource(ints = { 1, 0, -9 })
	@Tag("SearchBug-Aggregation_valid")
	public void validAggregationOf_SearchBug(int severityID) {
		// Arrange
		
		// Assert 0
		assertTrue(this.first_searchBug.getSearchedSeverity() == 0
				|| this.first_searchBug.getSearchedSeverity() == (Integer)null);
		
		// Act 1
		try {
			this.first_searchBug = this.first_searchBug.setSearchedSeverity(severityID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 1
		assertSame(this.first_searchBug.getSearchedSeverity(),severityID);
	}

}
