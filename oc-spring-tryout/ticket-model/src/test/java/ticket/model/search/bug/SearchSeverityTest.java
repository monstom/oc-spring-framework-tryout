package ticket.model.search.bug;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("SearchSeverityClass_UniteTesting")
public class SearchSeverityTest {

	private SearchSeverity first_searchSeverity;
	
	@BeforeEach
	public void init_SearchSeverity() {
		System.out.println("SearchSeverityTest - Initialization before each test ");
		first_searchSeverity = new SearchSeverity();
	}
	
	@AfterEach
	public void end_SearchSeverity() {
		first_searchSeverity = null;
		System.out.println("SearchSeverityTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchSeverityTest() {
		System.out.println("SearchSeverityTest - Start of unite testing for class SearchSeverity");
	}
	
	@AfterAll 
	public static void end_SearchSeverityTest() {
		System.out.println("SearchSeverityTest - End of unite testing for class SearchSeverity");
	}
	
	
	@ParameterizedTest(name = "The researched primary severity key and the level ({0},{1}) must allow the aggregation of the search attributes !")
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
			assertTrue(this.first_searchSeverity.getSearchedSeverityID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchSeverity.getSearchedSeverityID(),severityID);
		assertTrue(this.first_searchSeverity.getSearchedLevel() == 0
				|| this.first_searchSeverity.getSearchedLevel() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchSeverity = this.first_searchSeverity.setSearchedLevel(severity_level);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchSeverity.getSearchedLevel() == 0);
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchSeverity.getSearchedSeverityID(),severityID);
		assertSame(this.first_searchSeverity.getSearchedLevel(),severity_level);
	}

}
