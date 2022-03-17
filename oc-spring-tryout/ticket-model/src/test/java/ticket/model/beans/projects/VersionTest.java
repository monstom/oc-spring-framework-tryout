package ticket.model.beans.projects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class VersionTest {

	private final int id_version_length = 30;
	
	private Version first_version;
	
	
	@BeforeEach
	public void init_Version() {
		System.out.println("VersionTest - Appel avant chaque test");
		first_version = new Version();
	}
	
	@AfterEach
	public void end_Version() {
		first_version = null;
		System.out.println("VersionTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_VersionTest() {
		System.out.println("VersionTest - Début du test de classe Version");
	}
	
	@AfterAll 
	public static void end_VersionTest() {
		System.out.println("VersionTest - Fin du test de classe Version");
	}
	
	
	@ParameterizedTest(name = "{0} ne doit pas dépasser 30 caractères !")
	@ValueSource(strings = { "Développement Java",
							 "1.0.1-SNAPSHOT", 
							 "3.1.5-RELEASE",
							 "bzeuivbziueviubeziuvbueivbezivbiuezbvui" 
						   })
	public void invalidLengthOfVersionID(String arg1) {
		// Arrange
		
		// Act
		first_version.setVersionID(arg1);;
		
		// Assert
		assertTrue( first_version.getVersionID().length() < this.id_version_length );
	}
	
	@ParameterizedTest(name = "{0} ne doit pas être nul !")
	@ValueSource(strings = { "1.0.1-SNAPSHOT", "3.1.5-RELEASE", " " })
	public void emptyVersionID(String arg1) {
		// Arrange
		
		// Act
		first_version.setVersionID(arg1);
		
		// Assert
		assertFalse( first_version.getVersionID().isEmpty() 
				  || first_version.getVersionID().isBlank() );
	}
	
	@ParameterizedTest(name = "{0} ne doit pas être négatifs ou nuls !")
	@ValueSource(ints = { 10, 0, -3,  })
	public void invalidValueOfProjectID(int arg1) {
		// Arrange
		
		// Act
		first_version.setProjectID(arg1);
		
		// Assert
		assertTrue(first_version.getProjectID() > 0);
	}
}
