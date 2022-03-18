package ticket.model.beans.projects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Timestamp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("ProjectClass_UniteTesting")
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
	
	
	@ParameterizedTest(name = "La clé primaire du projet : id ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Project-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_ProjectID(int arg1) {
		// Arrange
		
		// Act
		first_project.setProjectID(arg1);
		
		// Assert
		assertTrue(first_project.getProjectID() > 0);
	}
	
	
	@ParameterizedTest(name = "Le titre du projet ({0}) ne doit pas dépasser 100 caractères !")
	@ValueSource(strings = { "Développement Java",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Project-title_invalidLength")
	public void invalidLengthOf_Title(String arg1) {
		// Arrange
		
		// Act
		first_project.setProject_title(arg1);;
		
		// Assert
		assertTrue(first_project.getProject_title().length() < this.title_length);
	}
	
	
	@ParameterizedTest(name = "Le titre du projet ({0}) ne doit pas être nul !")
	@ValueSource(strings = { "Développement Java", " " })
	@Tag("Project-title_emptyness")
	public void isEmpty_Title(String arg1) {
		// Arrange
		
		// Act
		first_project.setProject_title(arg1);
		
		// Assert
		assertFalse(first_project.getProject_title().isEmpty() 
				 || first_project.getProject_title().isBlank());
	}
	
	
	@ParameterizedTest(name = "La date de création du projet ({0}) doit être non nulle et correctement définie !")
	@ValueSource(strings = { "2022-03-17 17:15:23", "2022-03-17", "-8", " " })
	@Tag("Project-creationDate_invalidValue")
	public void isEmpty_CreationDate(String arg1) {
		// Arrange
		
		// Act
		try { 
			first_project.setProject_creationDate(Timestamp.valueOf(arg1));
		} catch(IllegalArgumentException e) {
			fail(e.getMessage());
		}
				
		// Assert
		assertTrue(first_project.getProject_creationDate().getTime() > 0);
	}
	
	
	@ParameterizedTest(name = "La clé étrangère du projet : managerID ({0}) ne doit pas être négatif ou nul !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Project-foreignKey_manager_invalidValue")
	public void invalidValueOf_ForeignKey_ManagerID(int arg1) {
		// Arrange
		
		// Act
		first_project.setProject_managerID(arg1);
		
		// Assert
		assertTrue(first_project.getProject_managerID() > 0);
	}

}
