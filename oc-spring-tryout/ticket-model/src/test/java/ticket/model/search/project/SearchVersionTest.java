package ticket.model.search.project;

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

public class SearchVersionTest {

private SearchVersion first_searchVersion;
	
	@BeforeEach
	public void init_SearchVersion() {
		System.out.println("SearchVersionTest - Appel avant chaque test");
		first_searchVersion = new SearchVersion();
	}
	
	@AfterEach
	public void end_SearchVersion() {
		first_searchVersion = null;
		System.out.println("SearchVersionTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchVersionTest() {
		System.out.println("SearchVersionTest - Début du test de classe SearchVersion");
	}
	
	@AfterAll 
	public static void end_SearchVersionTest() {
		System.out.println("SearchVersionTest - Fin du test de classe SearchVersion");
	}
	
	@ParameterizedTest(name = "Lidentifiant et le libellé recherchés pour une version ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,'1.0.2'", "1,''", "-9,''", "0,8", "-4,'1.2.2'", "0,''" })
	@Tag("SearchVersion-Aggregation_valid")
	public void validAggregationOf_SearchVersion(int projectID, String version_label) {
		// Arrange

		// Assert 0 
		assertTrue(this.first_searchVersion.getSearchedVersionID() == 0
				|| this.first_searchVersion.getSearchedVersionID() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchVersion = this.first_searchVersion.setSearchedVersionID(projectID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert 1
		assertSame(this.first_searchVersion.getSearchedVersionID(),projectID);
		assertTrue(this.first_searchVersion.getSearchedLabel() == null
				|| this.first_searchVersion.getSearchedLabel().isEmpty() 
				|| this.first_searchVersion.getSearchedLabel().isBlank());
		
		// Act 2
		try {
			this.first_searchVersion = this.first_searchVersion.setSearchedLabel(version_label);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert
		assertSame(this.first_searchVersion.getSearchedVersionID(),projectID);
		assertSame(this.first_searchVersion.getSearchedLabel(),version_label);
	}

}
