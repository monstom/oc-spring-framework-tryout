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


@Tag("SearchBugVersionAssociationClass_UniteTesting")
public class SearchBugVersionAssociationTest {

	private SearchBugVersionAssociation first_searchBugVersionAsso;
	
	@BeforeEach
	public void init_SearchBugVersionAssociation() {
		System.out.println("SearchBugVersionAssociationTest - Initialization before each test ");
		first_searchBugVersionAsso = new SearchBugVersionAssociation();
	}
	
	@AfterEach
	public void end_SearchBugVersionAssociation() {
		first_searchBugVersionAsso = null;
		System.out.println("SearchBugVersionAssociationTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchBugVersionAssociationTest() {
		System.out.println("SearchBugVersionAssociationTest - Start of unite testing for class SearchBugVersionAssociation");
	}
	
	@AfterAll 
	public static void end_SearchBugVersionAssociationTest() {
		System.out.println("SearchBugVersionAssociationTest - End of unite testing for class SearchBugVersionAssociation");
	}
	
	
	@ParameterizedTest(name = "The researched primary bug and version keys of the association ({0},{1},{2}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,1,'1.4.1'", "1,2, ", "9,-2,'8'", "0,0,'1.2.2'", "-4,1,'1.3.4'", "1,1,''" })
	@Tag("SearchBugVersionAssociation-Aggregation_valid")
	public void validAggregationOf_SearchBugVersionAssociation(int bugID, int versionID, String version_label) {
		// Arrange
		
		// Assert 0 
		assertTrue(this.first_searchBugVersionAsso.getSearchedBugID() == 0
				|| this.first_searchBugVersionAsso.getSearchedBugID() == (Integer)null);
		assertTrue(this.first_searchBugVersionAsso.getSearchedVersionID() == 0
				|| this.first_searchBugVersionAsso.getSearchedVersionID() == (Integer)null);
		assertTrue(this.first_searchBugVersionAsso.getSearchedVersionLabel() == null
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isEmpty() 
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isBlank());		
		
		// Act 1
		try {
			this.first_searchBugVersionAsso = this.first_searchBugVersionAsso.setSearchedBugID(bugID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchBugVersionAsso.getSearchedBugID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchBugVersionAsso.getSearchedBugID(),bugID);
		assertTrue(this.first_searchBugVersionAsso.getSearchedVersionID() == 0
				|| this.first_searchBugVersionAsso.getSearchedVersionID() == (Integer)null);
		assertTrue(this.first_searchBugVersionAsso.getSearchedVersionLabel() == null
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isEmpty() 
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isBlank());
		
		// Act 2
		try {
			this.first_searchBugVersionAsso = this.first_searchBugVersionAsso.setSearchedVersionID(versionID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchBugVersionAsso.getSearchedVersionID() == 0);
			return;
		}
		
		
		// Assert 1
		assertSame(this.first_searchBugVersionAsso.getSearchedBugID(),bugID);
		assertSame(this.first_searchBugVersionAsso.getSearchedVersionID(),versionID);
		assertTrue(this.first_searchBugVersionAsso.getSearchedVersionLabel() == null
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isEmpty() 
				|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isBlank());
		
		// Act 2
		try {
			this.first_searchBugVersionAsso = this.first_searchBugVersionAsso.setSearchedVersionLabel(version_label);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchBugVersionAsso.getSearchedVersionLabel() == null
					|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isEmpty() 
					|| this.first_searchBugVersionAsso.getSearchedVersionLabel().isBlank());
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchBugVersionAsso.getSearchedBugID(),bugID);
		assertSame(this.first_searchBugVersionAsso.getSearchedVersionID(),versionID);
		assertSame(this.first_searchBugVersionAsso.getSearchedVersionLabel(),version_label);
	}

}
