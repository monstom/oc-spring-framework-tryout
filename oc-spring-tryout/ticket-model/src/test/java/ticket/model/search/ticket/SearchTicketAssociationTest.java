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


public class SearchTicketAssociationTest {

	private SearchTicketAssociation first_searchProject;
	
	@BeforeEach
	public void init_SearchTicketAssociation() {
		System.out.println("SearchTicketAssociationTest - Appel avant chaque test");
		first_searchProject = new SearchTicketAssociation();
	}
	
	@AfterEach
	public void end_SearchTicketAssociation() {
		first_searchProject = null;
		System.out.println("SearchTicketAssociationTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchTicketAssociationTest() {
		System.out.println("SearchTicketAssociationTest - Début du test de classe SearchTicketAssociation");
	}
	
	@AfterAll 
	public static void end_SearchTicketAssociationTest() {
		System.out.println("SearchTicketAssociationTest - Fin du test de classe SearchTicketAssociation");
	}
	
	@ParameterizedTest(name = "Les identifiants des tickets recherchés pour une association de ticket ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,2", "1,0", "-9,9", "0,3", "-4,-2", "0,0" })
	@Tag("SearchTicketAssociation-Aggregation_valid")
	public void validAggregationOf_SearchTicketAssociation(int ticket1ID, int ticket2ID) {
		// Arrange
		
		// Act 1 
		this.first_searchProject = this.first_searchProject.setSearchedTicket1AssoID(ticket1ID);
		
		// Assert 1
		assertSame(this.first_searchProject.getSearchedTicket1AssoID(),ticket1ID);
		assertTrue(this.first_searchProject.getSearchedTicket2AssoID() == 0);
		
		// Act 2
		this.first_searchProject = this.first_searchProject.setSearchedTicket2AssoID(ticket2ID);
		
		// Assert 2
		assertSame(this.first_searchProject.getSearchedTicket1AssoID(),ticket1ID);
		assertSame(this.first_searchProject.getSearchedTicket2AssoID(),ticket2ID);
	}

}
