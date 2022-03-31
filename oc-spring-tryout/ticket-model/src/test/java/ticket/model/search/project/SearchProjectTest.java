package ticket.model.search.project;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class SearchProjectTest {

	private SearchProject first_searchProject;
	
	@BeforeEach
	public void init_SearchProject() {
		System.out.println("SearchProjectTest - Appel avant chaque test");
		first_searchProject = new SearchProject();
	}
	
	@AfterEach
	public void end_SearchProject() {
		first_searchProject = null;
		System.out.println("SearchProjectTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchProjectTest() {
		System.out.println("SearchProjectTest - Début du test de classe SearchProject");
	}
	
	@AfterAll 
	public static void end_SearchProjectTest() {
		System.out.println("SearchProjectTest - Fin du test de classe SearchProject");
	}
	
	@ParameterizedTest(name = "Lidentifiant et le manager recherchés pour un projet ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,2", "1,0", "-9,9", "0,3", "-4,-2", "0,0" })
	@Tag("SearchProject-Aggregation_valid")
	public void validAggregationOf_SearchProject(int projectID, int managerID) {
		// Arrange
		
		// Act 1 
		this.first_searchProject = this.first_searchProject.setSearchedProjectID(projectID);
		
		// Assert 1
		assertSame(this.first_searchProject.getSearchedProjectID(),projectID);
		assertTrue(this.first_searchProject.getSearchedManager() == 0);
		
		// Act 2
		this.first_searchProject = this.first_searchProject.setSearchedManager(managerID);
		
		// Assert 2
		assertSame(this.first_searchProject.getSearchedProjectID(),projectID);
		assertSame(this.first_searchProject.getSearchedManager(),managerID);
	}

}
