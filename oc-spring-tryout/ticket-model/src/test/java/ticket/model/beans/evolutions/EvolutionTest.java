package ticket.model.beans.evolutions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.model.beans.tickets.TicketTest;


@Tag("EvolutionClass_UniteTesting")
public class EvolutionTest extends TicketTest {

	private Evolution first_evolution;
	
	
	@BeforeEach
	public void init_Evolution() {
		System.out.println("EvolutionTest - Appel avant chaque test");
		first_evolution = new Evolution();
	}
	
	@AfterEach
	public void end_Evolution() {
		first_evolution = null;
		System.out.println("EvolutionTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_EvolutionTest() {
		System.out.println("EvolutionTest - Début du test de classe Evolution");
	}
	
	@AfterAll 
	public static void end_EvolutionTest() {
		System.out.println("EvolutionTest - Fin du test de classe Evolution");
	}
	
	
	@ParameterizedTest(name = "La priorité de lévolution ({0}) ne doit pas être négatif ou nul !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Evolution-priority_invalidValue")
	public void invalidValueOf_Priority(int arg1) {
		// Arrange
		
		// Act
		first_evolution.setEvolution_priority(arg1);
		
		// Assert
		assertTrue(first_evolution.getEvolution_priority() > 0);
	}
}
