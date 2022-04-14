package ticket.model.bean.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("UserClass_UniteTesting")
public class UserTest {

	private final int name_length = 100;
	
	private User first_user;
	
	
	@BeforeEach
	public void init_User() {
		System.out.println("UserTest - Initialization before each test ");
		first_user = new User();
	}
	
	@AfterEach
	public void end_User() {
		first_user = null;
		System.out.println("UserTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_UserTest() {
		System.out.println("UserTest - Start of unite testing for class User");
	}
	
	@AfterAll 
	public static void end_UserTest() {
		System.out.println("UserTest - End of unite testing for class User");
	}
	
	
	@ParameterizedTest(name = "The primary key of the user ({0}) must not be negative or equal 0 !")
		@ValueSource(ints = { 10, -6, 0 })
	@Tag("User-primaryKey_invalidValue")
	public void invalidValueOfUserID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_user.setUserID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_user.getUserID() > 0);
			return;
		}
			
		// Assert
		assertTrue(first_user.getUserID() > 0);
	}
	
	
	@ParameterizedTest(name = "The names of the user ({0},{1}) must not contains more than 100 characters !")
	@CsvSource({ "Thomas,Boullé", 
				 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui,Boullé",
				 "Thomas,abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe",
				 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui, abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe"
			  })
	@Tag("User-Names_invalidLength")
	public void invalidLengthOf_Firstname_Lastname(String arg1, String arg2) {
		// Arrange
		
		// Act
		try {
			first_user.setUser_firstname(arg1);
			first_user.setUser_lastname(arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_user.getUser_firstname() == null				
					|| first_user.getUser_lastname() == null);
			return;
		}
		
		// Assert
		assertTrue(first_user.getUser_firstname().length() < this.name_length				
				&& first_user.getUser_lastname().length() < this.name_length);
	}
	

	@ParameterizedTest(name = "The names of the user ({0},{1}) must not be empty or blank !")
	@CsvSource({ "Thomas,Boullé", "'' , ''", "'', Boullé", "Thomas, ''" })
	@Tag("User-Names_emptyness")
	public void emptyFirstnameOrLastname(String arg1, String arg2) {
		// Arrange
		
		// Act
		try {
			first_user.setUser_firstname(arg1);
			first_user.setUser_lastname(arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_user.getUser_firstname() == null
					|| first_user.getUser_lastname() == null);
			return;
		}
		
		// Assert
		assertFalse(first_user.getUser_firstname().isEmpty()
				 || first_user.getUser_lastname().isEmpty());
	}
}
