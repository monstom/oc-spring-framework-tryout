package ticket.model.search.evolution;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.model.search.ticket.SearchTicketTest;

public class SearchEvolutionTest extends SearchTicketTest {

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
	@ValueSource(ints = { 1, 0, -9 })
	@Tag("SearchEvolution-Aggregation_valid")
	public void validAggregationOf_SearchEvolution(int priority) {
		// Arrange
		
		// Assert 0 
		assertTrue(this.first_searchEvolution.getSearchedPriority() == 0
				|| this.first_searchEvolution.getSearchedPriority() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchEvolution = this.first_searchEvolution.setSearchedPriority(priority);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchEvolution.getSearchedPriority() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchEvolution.getSearchedPriority(),priority);
	}

}
