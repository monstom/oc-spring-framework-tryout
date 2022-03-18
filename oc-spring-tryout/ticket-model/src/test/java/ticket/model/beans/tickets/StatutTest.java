package ticket.model.beans.tickets;

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
		System.out.println("StatutTest - Appel avant chaque test");
		first_statut = new Statut();
	}
	
	@AfterEach
	public void end_Statut() {
		first_statut = null;
		System.out.println("StatutTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_StatutTest() {
		System.out.println("StatutTest - Début du test de classe Statut");
	}
	
	@AfterAll 
	public static void end_StatutTest() {
		System.out.println("StatutTest - Fin du test de classe Statut");
	}
	
	
	@ParameterizedTest(name = "La clé primaire du statut : id ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, 0, -3  })
	@Tag("Statut-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_StatutID(int arg1) {
		// Arrange
		
		// Act
		first_statut.setStatutID(arg1);
		
		// Assert
		assertTrue(first_statut.getStatutID() > 0);
	}
	
	
	@ParameterizedTest(name = "Le libellé du statut ({0}) ne doit pas dépasser 100 caractères !")
	@ValueSource(strings = { "Open", "Closed", "Commited", "Stashed", "Resolving require admin verification",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Statut-label_invalidLength")
	public void invalidLengthOf_Label(String arg1) {
		// Arrange
		
		// Act
		first_statut.setStatut_label(arg1);
		
		// Assert
		assertTrue( first_statut.getStatut_label().length() < this.statut_label_length );
	}
	
	
	@ParameterizedTest(name = "Le libellé du statut ({0}) ne doit pas être nul !")
	@ValueSource(strings = { "Open", "Closed", "Commited", "Stashed", " " })
	@Tag("Statut-label_emptyness")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		first_statut.setStatut_label(arg1);
		
		// Assert
		assertFalse( first_statut.getStatut_label().isEmpty() 
				  || first_statut.getStatut_label().isBlank() );
	}
	
}
