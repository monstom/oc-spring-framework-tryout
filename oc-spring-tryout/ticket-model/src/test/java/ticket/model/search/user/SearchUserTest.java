package ticket.model.search.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("SearchUserClass_UniteTesting")
public class SearchUserTest {
	
	private SearchUser first_searchUser;
		
	@BeforeEach
	public void init_SearchUser() {
		System.out.println("SearchUserTest - Appel avant chaque test");
		first_searchUser = new SearchUser();
	}
	
	@AfterEach
	public void end_SearchUser() {
		first_searchUser = null;
		System.out.println("SearchUserTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchUserTest() {
		System.out.println("SearchUserTest - Début du test de classe SearchUser");
	}
	
	@AfterAll 
	public static void end_SearchUserTest() {
		System.out.println("SearchUserTest - Fin du test de classe SearchUser");
	}
	
	
	@ParameterizedTest(name = "Lidentifiant et le couple (nom,prénom) recherchés pour un utilisateur ({0},{1}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,ThomasBoullé", "1, ", "-9,Tom", "0,Thomas", "-4,''" })
	@Tag("SearchUser-Aggregation_valid")
	public void validAggregationOf_SearchUser(int userID, String fname) {
		// Arrange
		
		// Act 1 
		this.first_searchUser = this.first_searchUser.setSearchedUserID(userID);
		
		// Assert 1
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertTrue(this.first_searchUser.getSearchedFullname() == null
				|| this.first_searchUser.getSearchedFullname().isEmpty() 
				|| this.first_searchUser.getSearchedFullname().isBlank());
		
		// Act 2
		this.first_searchUser = this.first_searchUser.setSearchedFullname(fname);
		
		// Assert 2
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertSame(this.first_searchUser.getSearchedFullname(),fname);
	}
}
