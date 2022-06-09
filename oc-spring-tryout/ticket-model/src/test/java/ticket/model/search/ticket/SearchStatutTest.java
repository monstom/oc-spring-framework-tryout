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


@Tag("SearchStatutClass_UniteTesting")
public class SearchStatutTest {

	private SearchStatut first_searchStatut;
	
	@BeforeEach
	public void init_SearchStatut() {
		System.out.println("SearchStatutTest - Initialization before each test ");
		first_searchStatut = new SearchStatut();
	}
	
	@AfterEach
	public void end_SearchStatut() {
		first_searchStatut = null;
		System.out.println("SearchStatutTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchStatutTest() {
		System.out.println("SearchStatutTest - Start of unite testing for class SearchStatut");
	}
	
	@AfterAll 
	public static void end_SearchStatutTest() {
		System.out.println("SearchStatutTest - End of unite testing for class SearchStatut");
	}
	
	@ParameterizedTest(name = "The researched primary key and label of the status ({0},{1}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,'Open'", "0,'Closed'", "-9,'Commited'", "1,''" })
	@Tag("SearchStatut-Aggregation_valid")
	public void validAggregationOf_SearchStatut(int statutID, String statut_label) {
		// Arrange
		
		// Assert 0
		assertTrue(this.first_searchStatut.getSearchedStatutID() == 0
				|| this.first_searchStatut.getSearchedStatutID() == (Integer)null);
		assertTrue(this.first_searchStatut.getSearchedLabel() == null 
				|| this.first_searchStatut.getSearchedLabel().isBlank()
				|| this.first_searchStatut.getSearchedLabel().isEmpty());
		
		
		// Act 1
		try {
			this.first_searchStatut = this.first_searchStatut.setSearchedStatutID(statutID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchStatut.getSearchedStatutID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchStatut.getSearchedStatutID(),statutID);
		assertTrue(this.first_searchStatut.getSearchedLabel() == null 
				|| this.first_searchStatut.getSearchedLabel().isBlank()
				|| this.first_searchStatut.getSearchedLabel().isEmpty());
		
		// Act 2 
		try {
			this.first_searchStatut = this.first_searchStatut.setSearchedLabel(statut_label);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchStatut.getSearchedLabel() == null);
			return;
		}
		
		// Assert 2 
		assertSame(this.first_searchStatut.getSearchedStatutID(),statutID);
		assertSame(this.first_searchStatut.getSearchedLabel(),statut_label);
	}

}
