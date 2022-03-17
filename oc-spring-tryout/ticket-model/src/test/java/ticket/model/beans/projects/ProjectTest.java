package ticket.model.beans.projects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ProjectTest {

	private final int title_length = 100;
	
	private Project first_project;
	
	
	@BeforeEach
	public void init_Project() {
		System.out.println("ProjectTest - Appel avant chaque test");
		first_project = new Project();
	}
	
	@AfterEach
	public void end_Project() {
		first_project = null;
		System.out.println("ProjectTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_ProjectTest() {
		System.out.println("ProjectTest - Début du test de classe Project");
	}
	
	@AfterAll 
	public static void end_ProjectTest() {
		System.out.println("ProjectTest - Fin du test de classe Project");
	}
	
	
	@ParameterizedTest(name = "{0} ne doit pas dépasser 100 caractères !")
	@ValueSource(strings = { "Développement Java",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	public void invalidLengthOfTitle(String arg1) {
		// Arrange
		
		// Act
		first_project.setTitle(arg1);;
		
		// Assert
		assertTrue( first_project.getTitle().length() < this.title_length );
	}
	
	@ParameterizedTest(name = "{0} ne doit pas être nul !")
	@ValueSource(strings = { "Développement Java", " " })
	public void emptyTitle(String arg1) {
		// Arrange
		
		// Act
		first_project.setTitle(arg1);
		
		// Assert
		assertFalse( first_project.getTitle().isEmpty() 
				  || first_project.getTitle().isBlank() );
	}
	
	@ParameterizedTest(name = "{0} ou {1} ne doivent pas être négatifs ou nuls  !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-7, -14" })
	public void invalidValueOfProjectIdOrManagerId(int arg1, int arg2) {
		// Arrange
		
		// Act
		first_project.setID_project(arg1);
		first_project.setID_manager(arg2);
		
		// Assert
		assertTrue(first_project.getID_project() > 0
				&& first_project.getID_manager() > 0);
	}

}
