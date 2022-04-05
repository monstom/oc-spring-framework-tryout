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
import org.junit.jupiter.params.provider.CsvSource;


public class SearchSeverityTest {

	private SearchSeverity first_searchSeverity;
	
	@BeforeEach
	public void init_SearchSeverity() {
		System.out.println("SearchSeverityTest - Appel avant chaque test");
		first_searchSeverity = new SearchSeverity();
	}
	
	@AfterEach
	public void end_SearchSeverity() {
		first_searchSeverity = null;
		System.out.println("SearchSeverityTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchSeverityTest() {
		System.out.println("SearchSeverityTest - Début du test de classe SearchSeverity");
	}
	
	@AfterAll 
	public static void end_SearchSeverityTest() {
		System.out.println("SearchSeverityTest - Fin du test de classe SearchSeverity");
	}
	
	@ParameterizedTest(name = "Lidentifiant et le niveau recherchés pour une sévérité ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,2", "1,0", "-9,9", "0,3", "-4,-2", "0,0" })
	@Tag("SearchSeverity-Aggregation_valid")
	public void validAggregationOf_SearchSeverity(int severityID, int severity_level) {
		// Arrange
		
		// Assert 0 
		assertTrue(this.first_searchSeverity.getSearchedSeverityID() == 0
				|| this.first_searchSeverity.getSearchedSeverityID() == (Integer)null);
		assertTrue(this.first_searchSeverity.getSearchedLevel() == 0
				|| this.first_searchSeverity.getSearchedLevel() == (Integer)null);
		
		// Act 1
		try {
			this.first_searchSeverity = this.first_searchSeverity.setSearchedSeverityID(severityID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 1
		assertSame(this.first_searchSeverity.getSearchedSeverityID(),severityID);
		assertTrue(this.first_searchSeverity.getSearchedLevel() == 0);
		
		// Act 2
		try {
			this.first_searchSeverity = this.first_searchSeverity.setSearchedLevel(severity_level);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 2
		assertSame(this.first_searchSeverity.getSearchedSeverityID(),severityID);
		assertSame(this.first_searchSeverity.getSearchedLevel(),severity_level);
	}

}
