package ticket.model.bean.ticket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("StatutClass_UniteTesting")
public class StatutTest {

	private final int statut_label_length = 100;
	
	private Statut first_statut;
	
	
	@BeforeEach
	public void init_Statut() {
		System.out.println("StatutTest - Initialization before each test ");
		first_statut = new Statut();
	}
	
	@AfterEach
	public void end_Statut() {
		first_statut = null;
		System.out.println("StatutTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_StatutTest() {
		System.out.println("StatutTest - Start of unite testing for class Statut");
	}
	
	@AfterAll 
	public static void end_StatutTest() {
		System.out.println("StatutTest - End of unite testing for class Statut");
	}
	

	@ParameterizedTest(name = "The primary key of the status ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, 0, -3  })
	@Tag("Statut-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_StatutID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_statut.setStatutID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_statut.getStatutID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_statut.getStatutID() > 0);
	}
	

	@ParameterizedTest(name = "The label of the status ({0}) must not contains more than 100 characters !")
	@ValueSource(strings = { "Open", "Closed", "Commited", "Stashed", "Resolving require admin verification",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Statut-label_invalidLength")
	public void invalidLengthOf_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_statut.setStatut_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue( first_statut.getStatut_label() == null);
			return;
		}
		
		// Assert
		assertTrue( first_statut.getStatut_label().length() < this.statut_label_length );
	}
	

	@ParameterizedTest(name = "The label of the status ({0}) must not be empty or blank !")
	@ValueSource(strings = { "Open", "Closed", "Commited", "Stashed", " " })
	@Tag("Statut-label_emptyness")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_statut.setStatut_label(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue( first_statut.getStatut_label() == null);
			return;
		}
		
		// Assert
		assertFalse( first_statut.getStatut_label().isEmpty() 
				  || first_statut.getStatut_label().isBlank() );
	}
	
}
