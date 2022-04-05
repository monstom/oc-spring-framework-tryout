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
	
	@ParameterizedTest(name = "Lidentifiant et le libellé recherchés pour un statut ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
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
			fail(e.getMessage());
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
			fail(e.getMessage());
		}
		
		// Assert 2 
		assertSame(this.first_searchStatut.getSearchedStatutID(),statutID);
		assertSame(this.first_searchStatut.getSearchedLabel(),statut_label);
	}

}
