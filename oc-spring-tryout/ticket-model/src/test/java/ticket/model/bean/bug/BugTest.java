package ticket.model.bean.bug;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.model.bean.ticket.TicketTest;


@Tag("BugClass_UniteTesting")
public class BugTest extends TicketTest {

	private Bug first_bug;
	
	
	@BeforeEach
	public void init_Bug() {
		System.out.println("BugTest - Appel avant chaque test");
		first_bug = new Bug();
	}
	
	@AfterEach
	public void end_Bug() {
		first_bug = null;
		System.out.println("BugTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_BugTest() {
		System.out.println("BugTest - Début du test de classe Bug");
	}
	
	@AfterAll 
	public static void end_BugTest() {
		System.out.println("BugTest - Fin du test de classe Bug");
	}
	
	
	@ParameterizedTest(name = "La clé étrangère du bug : sévérité ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Bug-severity_invalidValue")
	public void invalidValueOf_ForeignKey_SeverityID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_bug.setBug_severity(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert
		assertTrue(first_bug.getBug_severity() > 0);
	}
}