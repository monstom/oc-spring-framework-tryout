package ticket.model.bean.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		System.out.println("ProjectTest - Initialization before each test ");
		first_project = new Project();
	}
	
	@AfterEach
	public void end_Project() {
		first_project = null;
		System.out.println("ProjectTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_ProjectTest() {
		System.out.println("ProjectTest - Start of unite testing for class Project");
	}
	
	@AfterAll 
	public static void end_ProjectTest() {
		System.out.println("ProjectTest - End of unite testing for class Project");
	}
	
	
	@ParameterizedTest(name = "The primary key of the project ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Project-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_ProjectID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_project.setProjectID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_project.getProjectID() > 0);
			return;
		}
		
		// Assert
		assertTrue(first_project.getProjectID() > 0);
	}
	
	
	@ParameterizedTest(name = "The title of the project ({0}) must not contains more than 100 characters !")
	@ValueSource(strings = { "Développement Java",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Project-title_invalidLength")
	public void invalidLengthOf_Title(String arg1) {
		// Arrange
		
		// Act
		try {
			first_project.setProject_title(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_project.getProject_title() == null);
			return;
		}
		
		// Assert
		assertTrue(first_project.getProject_title().length() < this.title_length);
	}
	

	@ParameterizedTest(name = "The title of the project ({0}) must not empty or blank !")
	@ValueSource(strings = { "Développement Java", " " })
	@Tag("Project-title_emptyness")
	public void isEmpty_Title(String arg1) {
		// Arrange
		
		// Act
		try {
			first_project.setProject_title(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_project.getProject_title() == null);
			return;
		}
		
		// Assert
		assertFalse(first_project.getProject_title().isEmpty() 
				 || first_project.getProject_title().isBlank());
	}
	
	
	@ParameterizedTest(name = "The creation date of the project ({0}) must be defined in a correct format !")
	@ValueSource(strings = { "2022-03-21 18:15:23", "2022-03-17", "-8", " " })
	@Tag("Project-creationDate_invalidValue")
	public void isEmpty_CreationDate(String arg1) {
		// Arrange
		
		// Act
		try { 
			first_project.setProject_creationDate(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_project.getProject_creationDate() == null);
			return;
		}
				
		// Assert
		assertTrue(first_project.getProject_creationDate() == null
				|| first_project.getProject_creationDate().getTime() > 0);
	}
	

	@ParameterizedTest(name = "The foreign manager key of the project ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Project-foreignKey_manager_invalidValue")
	public void invalidValueOf_ForeignKey_ManagerID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_project.setProject_managerID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_project.getProject_managerID() > 0);
			return;
		}		
		
		// Assert
		assertTrue(first_project.getProject_managerID() > 0);
	}

}
