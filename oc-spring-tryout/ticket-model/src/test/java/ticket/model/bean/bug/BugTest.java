package ticket.model.bean.bug;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
		System.out.println("BugTest - Initialization before each test ");
		first_bug = new Bug();
	}
	
	@AfterEach
	public void end_Bug() {
		first_bug = null;
		System.out.println("BugTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_BugTest() {
		System.out.println("BugTest - Start of unite testing for class Bug");
	}
	
	@AfterAll 
	public static void end_BugTest() {
		System.out.println("BugTest - End of unite testing for class Bug");
	}
	
	
	@ParameterizedTest(name = "The foreign severity key of a bug ({0}) must not be negative or equal 0")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Bug-severity_invalidValue")
	public void invalidValueOf_ForeignKey_SeverityID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_bug.setBug_severity(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_bug.getBug_severity() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_bug.getBug_severity() > 0);
	}
}