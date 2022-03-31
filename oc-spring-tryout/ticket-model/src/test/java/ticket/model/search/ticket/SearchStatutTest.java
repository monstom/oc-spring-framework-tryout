package ticket.model.search.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class SearchStatutTest {

	private SearchStatut first_searchStatut;
	
	@BeforeEach
	public void init_SearchStatut() {
		System.out.println("SearchStatutTest - Appel avant chaque test");
		first_searchStatut = new SearchStatut();
	}
	
	@AfterEach
	public void end_SearchStatut() {
		first_searchStatut = null;
		System.out.println("SearchStatutTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchStatutTest() {
		System.out.println("SearchStatutTest - Début du test de classe SearchStatut");
	}
	
	@AfterAll 
	public static void end_SearchStatutTest() {
		System.out.println("SearchStatutTest - Fin du test de classe SearchStatut");
	}
	
	@ParameterizedTest(name = "Lidentifiant recherché pour un statut ({0}) doit permettre l'agrégation des attributs de recherche !")
	@ValueSource(ints = { 1, 0, -9 })
	@Tag("SearchStatut-Aggregation_valid")
	public void validAggregationOf_SearchStatut(int statutID) {
		// Arrange
		
		// Assert 0
		assertTrue(this.first_searchStatut.getSearchedStatutID() == 0);
		
		// Act 1
		this.first_searchStatut = this.first_searchStatut.setSearchedStatutID(statutID);
		
		// Assert 1
		assertSame(this.first_searchStatut.getSearchedStatutID(),statutID);
	}

}
