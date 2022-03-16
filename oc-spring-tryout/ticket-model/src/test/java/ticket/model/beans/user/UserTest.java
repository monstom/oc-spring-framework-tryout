package ticket.model.beans.user;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


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
	
	
	@ParameterizedTest(name = "{0} ou {1} ne doit dépasser 100 caractères !")
	@CsvSource({ "Thomas,Boullé", 
				 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui,Boullé",
				 "Thomas,abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe" })
	public void invalidLengthOfFirstnameOrLastname(String arg1, String arg2) {
		// Arrange
		
		// Act
		first_user = new User(arg1,arg2);
		
		// Assert
		assertTrue(first_user.getFirstname().length() < this.name_length 
				&& first_user.getLastname().length() < this.name_length);
	}
	
	@ParameterizedTest(name = "{0} ne doit être négatif ou nul !")
	@ValueSource(ints = { 10, -6, 0 })
	public void invalidValueOfUserID(int arg1) {
		// Arrange
		
		// Act
		first_user.setID_user(arg1);
		
		// Assert
		assertTrue(first_user.getID_user() > 0);
	}
}
