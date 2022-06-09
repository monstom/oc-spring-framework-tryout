package ticket.model.bean.evolution;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("EvolutionClass_UniteTesting")
public class EvolutionTest {

	private Evolution first_evolution;
	
	
	@BeforeEach
	public void init_Evolution() {
		System.out.println("EvolutionTest - Initialization before each test ");
		first_evolution = new Evolution();
	}
	
	@AfterEach
	public void end_Evolution() {
		first_evolution = null;
		System.out.println("EvolutionTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_EvolutionTest() {
		System.out.println("EvolutionTest - Start of unite testing for class Evolution");
	}
	
	@AfterAll 
	public static void end_EvolutionTest() {
		System.out.println("EvolutionTest - End of unite testing for class Evolution");
	}
	
	
	@ParameterizedTest(name = "The primary key of an evolution ({0}) must not be negative or equal 0")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Evolution-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_TicketID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_evolution.setEvolution_ticketID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_evolution.getEvolution_ticketID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_evolution.getEvolution_ticketID() > 0);
	}
	

	@ParameterizedTest(name = "The priority of the evolution ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 5, 11, -6, 0 })
	@Tag("Evolution-priority_invalidValue")
	public void invalidValueOf_Priority(int arg1) {
		// Arrange
		
		// Act
		try {
			first_evolution.setEvolution_priority(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_evolution.getEvolution_priority() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_evolution.getEvolution_priority() > 0);
	}
}
