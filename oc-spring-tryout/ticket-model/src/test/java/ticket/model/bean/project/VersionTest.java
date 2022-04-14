package ticket.model.bean.project;

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
		System.out.println("VersionTest - Initialization before each test ");
		first_version = new Version();
	}
	
	@AfterEach
	public void end_Version() {
		first_version = null;
		System.out.println("VersionTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_VersionTest() {
		System.out.println("VersionTest - Start of unite testing for class Version");
	}
	
	@AfterAll 
	public static void end_VersionTest() {
		System.out.println("VersionTest - End of unite testing for class Version");
	}
	
	
	@ParameterizedTest(name = "The foreign project primary key of the version ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, 0, -3 })
	@Tag("Version-foreignKey_project_invalidValue")
	public void invalidValueOf_PrimaryKey_ProjectID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_version.setVersion_projectID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_version.getVersion_projectID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_version.getVersion_projectID() > 0);
	}
	
	
	@ParameterizedTest(name = "The primary label key of the version ({0}) must not contains more than 30 characters !")
	@ValueSource(strings = { "Développement Java", "1.0.1-SNAPSHOT", "3.1.5-RELEASE",
							 "bzeuivbziueviubeziuvbueivbezivbiuezbvui" 
						   })
	@Tag("Version-primaryKey_Label_invalidLength")
	public void invalidLengthOf_PrimaryKey_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_version.setVersion_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_version.getVersion_label() == null);
			return;
		}
		
		// Assert
		assertTrue(first_version.getVersion_label().length() < this.version_label_length);
	}
	
	
	@ParameterizedTest(name = "The primary label key of the version ({0}) must not by empty or blank !")
	@ValueSource(strings = { "1.0.1-SNAPSHOT", "3.1.5-RELEASE", " " })
	@Tag("Version-primaryKey_Label_emptyness")
	public void isEmpty_PrimaryKey_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_version.setVersion_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_version.getVersion_label() == null);
			return;
		}
		
		// Assert
		assertFalse(first_version.getVersion_label().isEmpty() 
				 || first_version.getVersion_label().isBlank());
	}
	
}
