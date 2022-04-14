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
	
	
	@ParameterizedTest(name = "The researched primary and secondary fullname keys of the user ({0},{1}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,Thomas Boullé", "1, ", "-9,Tom", "0,Thomas", "-4,''" })
	@Tag("SearchUser-Aggregation_valid")
	public void validAggregationOf_SearchUser(int userID, String fname) {
		// Arrange
		
		// Assert 0 
		assertTrue(this.first_searchUser.getSearchedUserID() == 0
				|| this.first_searchUser.getSearchedUserID() == (Integer)null);
		
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
		assertTrue(this.first_searchUser.getSearchedFullname() == null
				 || this.first_searchUser.getSearchedFullname().isEmpty() 
				 || this.first_searchUser.getSearchedFullname().isBlank());
		
		// Act 2
		try {
			this.first_searchUser = this.first_searchUser.setSearchedFullname(fname);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchUser.getSearchedFullname() == null);
			return;
		}
		
		// Assert 2
		assertSame(this.first_searchUser.getSearchedUserID(),userID);
		assertSame(this.first_searchUser.getSearchedFullname(),fname);
	}
}
