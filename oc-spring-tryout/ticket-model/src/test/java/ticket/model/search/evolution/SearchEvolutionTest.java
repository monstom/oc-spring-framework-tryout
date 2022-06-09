package ticket.model.search.evolution;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("SearchEvolutionClass_UniteTesting")
public class SearchEvolutionTest {

	private SearchEvolution first_searchEvolution;
	
	@BeforeEach
	public void init_SearchEvolution() {
		System.out.println("SearchEvolutionTest - Initialization before each test ");
		first_searchEvolution = new SearchEvolution();
	}
	
	@AfterEach
	public void end_SearchEvolution() {
		first_searchEvolution = null;
		System.out.println("SearchEvolutionTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchEvolutionTest() {
		System.out.println("SearchEvolutionTest - Start of unite testing for class SearchEvolution");
	}
	
	@AfterAll 
	public static void end_SearchEvolutionTest() {
		System.out.println("SearchEvolutionTest - End of unite testing for class SearchEvolution");
	}
	
	
	@ParameterizedTest(name = "The researched priority of the evolution ({0}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,1", "1,0", "-9,9", "0,3", "-4,-2", "0,0" })
	@Tag("SearchEvolution-Aggregation_valid")
	public void validAggregationOf_SearchEvolution(int ticketID, int priority) {
		// Arrange
		
		assertTrue(this.first_searchEvolution.getSearchedTicketID() == 0
				|| this.first_searchEvolution.getSearchedTicketID() == (Integer)null);
		assertTrue(this.first_searchEvolution.getSearchedPriority() == 0
				|| this.first_searchEvolution.getSearchedPriority() == (Integer)null);
		
		// Act 1
		try {
			this.first_searchEvolution = this.first_searchEvolution.setSearchedTicketID(ticketID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchEvolution.getSearchedTicketID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchEvolution.getSearchedTicketID(),ticketID);
		assertTrue(this.first_searchEvolution.getSearchedPriority() == 0
				|| this.first_searchEvolution.getSearchedPriority() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchEvolution = this.first_searchEvolution.setSearchedPriority(priority);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchEvolution.getSearchedPriority() == 0);
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchEvolution.getSearchedTicketID(),ticketID);
		assertSame(this.first_searchEvolution.getSearchedPriority(),priority);
	}

}
