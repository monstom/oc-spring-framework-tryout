package ticket.model.beans.bugs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("SeverityClass_UniteTesting")
public class SeverityTest {
	
	private final int severity_label_length = 100;
	
	private Severity first_severity;
	
	
	@BeforeEach
	public void init_Severity() {
		System.out.println("SeverityTest - Appel avant chaque test");
		first_severity = new Severity();
	}
	
	@AfterEach
	public void end_Severity() {
		first_severity = null;
		System.out.println("SeverityTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SeverityTest() {
		System.out.println("SeverityTest - Début du test de classe Severity");
	}
	
	@AfterAll 
	public static void end_SeverityTest() {
		System.out.println("SeverityTest - Fin du test de classe Severity");
	}
	
	
	@ParameterizedTest(name = "La clé primaire de la sévérité : id ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Severity-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_SeverityID(int arg1) {
		// Arrange
		
		// Act
		first_severity.setSeverityID(arg1);
		
		// Assert
		assertTrue(first_severity.getSeverityID() > 0);
	}
	
	
	@ParameterizedTest(name = "Le niveau de la sévérité ({0}) ne doit être compris entre 1 et 10 !")
	@ValueSource(ints = { 1, 8, 10, -9, 0, 15 })
	@Tag("Severity-level_invalidRange")
	public void invalidValueOf_Level(int arg1) {
		// Arrange
		
		// Act
		first_severity.setSeverity_level(arg1);
		
		// Assert
		assertTrue(first_severity.getSeverity_level() > 0
				&& first_severity.getSeverity_level() <= 10);
	}
	
	
	@ParameterizedTest(name = "Le libellé de la sévérité ({0}) ne doit pas dépasser 100 caractères !")
	@ValueSource(strings = { "Low", "Minor", "Major", "None", "Critical", "Breaking all or parts of project",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Severity-label_invalidLength")
	public void invalidLengthOf_Label(String arg1) {
		// Arrange
		
		// Act
		first_severity.setSeverity_label(arg1);
		
		// Assert
		assertTrue( first_severity.getSeverity_label().length() < this.severity_label_length );
	}
	
	
	@ParameterizedTest(name = "Le libellé de la sévérité ({0}) ne doit pas être nul !")
	@ValueSource(strings = { "Low", "Minor", "Major", "None", "Critical", "" })
	@Tag("Severity-label_emptyness")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		first_severity.setSeverity_label(arg1);
		
		// Assert
		assertFalse( first_severity.getSeverity_label().isEmpty() 
				  || first_severity.getSeverity_label().isBlank() );
	}
}
