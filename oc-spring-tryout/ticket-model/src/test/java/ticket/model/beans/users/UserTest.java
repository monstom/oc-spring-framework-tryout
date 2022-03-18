package ticket.model.beans.users;

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
		System.out.println("UserTest - Appel avant chaque test");
		first_user = new User();
	}
	
	@AfterEach
	public void end_User() {
		first_user = null;
		System.out.println("UserTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_UserTest() {
		System.out.println("UserTest - Début du test de classe User");
	}
	
	@AfterAll 
	public static void end_UserTest() {
		System.out.println("UserTest - Fin du test de classe User");
	}
	
	
	@ParameterizedTest(name = "Lidentifiant utilisateur ({0}) ne doit pas être négatif ou nul !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("User-primaryKey_invalidValue")
	public void invalidValueOfUserID(int arg1) {
		// Arrange
		
		// Act
		first_user.setUserID(arg1);
		
		// Assert
		assertTrue(first_user.getUserID() > 0);
	}
	
	@ParameterizedTest(name = "Le nom et prénom utilisateur ({0},{1}) ne doivent pas dépasser 100 caractères !")
	@CsvSource({ "Thomas,Boullé", 
				 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui,Boullé",
				 "Thomas,abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe",
				 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui, abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe"
			  })
	@Tag("User-Names_invalidLength")
	public void invalidLengthOf_Firstname_Lastname(String arg1, String arg2) {
		// Arrange
		
		// Act
		first_user = new User(arg1,arg2);
		
		// Assert
		assertTrue(first_user.getUser_firstname().length() < this.name_length				
				&& first_user.getUser_lastname().length() < this.name_length 
				);
	}
	
	
	@ParameterizedTest(name = "Le nom et prénom utilisateur ({0},{1}) ne doivent pas être nuls !")
	@CsvSource({ "Thomas,Boullé", "'' , ''", "'', Boullé", "Thomas, ''" })
	@Tag("User-Names_emptyness")
	public void emptyFirstnameOrLastname(String arg1, String arg2) {
		// Arrange
		
		// Act
		first_user = new User(arg1,arg2);
		
		// Assert
		assertFalse(first_user.getUser_firstname().isEmpty()
				 || first_user.getUser_lastname().isEmpty() );
	}
}
