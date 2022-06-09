package ticket.model.search.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
		System.out.println("SearchUserTest - Initialization before each test ");
		first_searchUser = new SearchUser();
	}
	
	@AfterEach
	public void end_SearchUser() {
		first_searchUser = null;
		System.out.println("SearchUserTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchUserTest() {
		System.out.println("SearchUserTest - Start of unite testing for class SearchUser");
	}
	
	@AfterAll 
	public static void end_SearchUserTest() {
		System.out.println("SearchUserTest - End of unite testing for class SearchUser");
	}
	
	
	@ParameterizedTest(name = "The researched primary and the names of the user ({0},{1},{2}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,Thomas,Boullé", "1, , ", "1,Tom, ", "-2,Thomas,Boullé ", "1,'',''" })
	@Tag("SearchUser-Aggregation_valid")
	public void validAggregationOf_SearchUser(int userID, String firstname, String lastname) {
		// Arrange
		
		// Assert 0 
		assertTrue(this.first_searchUser.getSearchedUserID() == 0
				|| this.first_searchUser.getSearchedUserID() == (Integer)null);
		assertTrue(this.first_searchUser.getSearchedFirstname() == null
				 || this.first_searchUser.getSearchedFirstname().isEmpty() 
				 || this.first_searchUser.getSearchedFirstname().isBlank());
		assertTrue(this.first_searchUser.getSearchedLastname() == null
				 || this.first_searchUser.getSearchedLastname().isEmpty() 
				 || this.first_searchUser.getSearchedLastname().isBlank());
		
		// Act 1 
		try {
			this.first_searchUser = this.first_searchUser.setSearchedUserID(userID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertFalse(first_searchUser.getSearchedUserID() > 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertTrue(this.first_searchUser.getSearchedFirstname() == null
				 || this.first_searchUser.getSearchedFirstname().isEmpty() 
				 || this.first_searchUser.getSearchedFirstname().isBlank());
		assertTrue(this.first_searchUser.getSearchedLastname() == null
				 || this.first_searchUser.getSearchedLastname().isEmpty() 
				 || this.first_searchUser.getSearchedLastname().isBlank());
		
		// Act 2
		try {
			this.first_searchUser.setSearchedFirstname(null);
			this.first_searchUser = this.first_searchUser.setSearchedFirstname(firstname);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchUser.getSearchedFirstname() == null);
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertSame(this.first_searchUser.getSearchedFirstname(),firstname);
		assertTrue(this.first_searchUser.getSearchedLastname() == null
				 || this.first_searchUser.getSearchedLastname().isEmpty() 
				 || this.first_searchUser.getSearchedLastname().isBlank());
		
		// Act 3
		try {
			this.first_searchUser.setSearchedLastname(null);
			this.first_searchUser = this.first_searchUser.setSearchedLastname(lastname);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchUser.getSearchedLastname() == null);
			return;
		}
		
		// Assert 3
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertSame(this.first_searchUser.getSearchedFirstname(),firstname);
		assertSame(this.first_searchUser.getSearchedLastname(),lastname);
		
	}
}
