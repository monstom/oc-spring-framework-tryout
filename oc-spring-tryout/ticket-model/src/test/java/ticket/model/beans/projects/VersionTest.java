package ticket.model.beans.projects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("VersionClass_UniteTesting")
public class VersionTest {

	private final int version_label_length = 30;
	
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
	
	
	@ParameterizedTest(name = "La clé étrangère de la version : projectID ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, 0, -3 })
	@Tag("Version-foreignKey_project_invalidValue")
	public void invalidValueOf_PrimaryKey_ProjectID(int arg1) {
		// Arrange
		
		// Act
		first_version.setVersion_projectID(arg1);
		
		// Assert
		assertTrue(first_version.getVersion_projectID() > 0);
	}
	
	
	@ParameterizedTest(name = "Cette clé primaire de la version : libéllé ({0}) ne doit pas dépasser 30 caractères !")
	@ValueSource(strings = { "Développement Java", "1.0.1-SNAPSHOT", "3.1.5-RELEASE",
							 "bzeuivbziueviubeziuvbueivbezivbiuezbvui" 
						   })
	@Tag("Version-primaryKey_Label_invalidLength")
	public void invalidLengthOf_PrimaryKey_Label(String arg1) {
		// Arrange
		
		// Act
		first_version.setVersion_label(arg1);;
		
		// Assert
		assertTrue( first_version.getVersion_label().length() < this.version_label_length );
	}
	
	
	@ParameterizedTest(name = "Cette clé primaire de la version : libéllé ({0}) ne doit pas être vide ou nulle !")
	@ValueSource(strings = { "1.0.1-SNAPSHOT", "3.1.5-RELEASE", " " })
	@Tag("Version-primaryKey_Label_emptyness")
	public void isEmpty_PrimaryKey_Label(String arg1) {
		// Arrange
		
		// Act
		first_version.setVersion_label(arg1);
		
		// Assert
		assertFalse( first_version.getVersion_label().isEmpty() 
				  || first_version.getVersion_label().isBlank() );
	}
	
}
