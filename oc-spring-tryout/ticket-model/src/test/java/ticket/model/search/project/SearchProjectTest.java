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
		System.out.println("SearchProjectTest - Initialization before each test ");
		first_searchProject = new SearchProject();
	}
	
	@AfterEach
	public void end_SearchProject() {
		first_searchProject = null;
		System.out.println("SearchProjectTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchProjectTest() {
		System.out.println("SearchProjectTest - Start of unite testing for class SearchProject");
	}
	
	@AfterAll 
	public static void end_SearchProjectTest() {
		System.out.println("SearchProjectTest - End of unite testing for class SearchProject");
	}


	@ParameterizedTest(name = "The researched primary project and foreign manager keys of the project ({0},{1}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,2", "1,0", "-9,9", "0,3", "-4,-2", "0,0" })
	@Tag("SearchProject-Aggregation_valid")
	public void validAggregationOf_SearchProject(int projectID, int managerID) {
		// Arrange

		// Assert 0 
		assertTrue(this.first_searchProject.getSearchedProjectID() == 0
				|| this.first_searchProject.getSearchedProjectID() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchProject = this.first_searchProject.setSearchedProjectID(projectID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchProject.getSearchedProjectID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchProject.getSearchedProjectID(),projectID);
		assertTrue(this.first_searchProject.getSearchedManager() == 0
				|| this.first_searchProject.getSearchedManager() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchProject = this.first_searchProject.setSearchedManager(managerID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchProject.getSearchedManager() == 0);
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchProject.getSearchedProjectID(),projectID);
		assertSame(this.first_searchProject.getSearchedManager(),managerID);
	}

}
