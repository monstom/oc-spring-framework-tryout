package ticket.model.bean.bug;

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
		System.out.println("SeverityTest - Initialization before each test ");
		first_severity = new Severity();
	}
	
	@AfterEach
	public void end_Severity() {
		first_severity = null;
		System.out.println("SeverityTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SeverityTest() {
		System.out.println("SeverityTest - Start of unite testing for class Severity");
	}
	
	@AfterAll 
	public static void end_SeverityTest() {
		System.out.println("SeverityTest - End of unite testing for class Severity");
	}
	
	
	@ParameterizedTest(name = "The primary key of the severity ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Severity-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_SeverityID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_severity.setSeverityID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_severity.getSeverityID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_severity.getSeverityID() > 0);
	}
	

	@ParameterizedTest(name = "The level of the severity ({0}) must be in range of 1 to 5!")
	@ValueSource(ints = { 1, 8, 10, -9, 0, 15 })
	@Tag("Severity-level_invalidRange")
	public void invalidValueOf_Level(int arg1) {
		// Arrange
		
		// Act
		try {
			first_severity.setSeverity_level(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_severity.getSeverity_level() <= 0
					|| first_severity.getSeverity_level() > 10);
			return;
		}
		
		// Assert
		assertTrue(first_severity.getSeverity_level() > 0
				&& first_severity.getSeverity_level() <= 10);
	}
	

	@ParameterizedTest(name = "The label of the severity ({0}) must not contains more than 100 characters !")
	@ValueSource(strings = { "Low", "Minor", "Major", "None", "Critical", "Breaking all or parts of project",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Severity-label_invalidLength")
	public void invalidLengthOf_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_severity.setSeverity_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue( first_severity.getSeverity_label() == null);
			return;
		}
		
		// Assert
		assertTrue(first_severity.getSeverity_label().length() < this.severity_label_length);
	}
	

	@ParameterizedTest(name = "The label of the severity ({0}) must not empty or blank !")
	@ValueSource(strings = { "Low", "Minor", "Major", "None", "Critical", "" })
	@Tag("Severity-label_emptyness")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_severity.setSeverity_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue( first_severity.getSeverity_label() == null);
			return;
		}
		
		// Assert
		assertFalse(first_severity.getSeverity_label().isEmpty() 
				 || first_severity.getSeverity_label().isBlank());
	}
}
