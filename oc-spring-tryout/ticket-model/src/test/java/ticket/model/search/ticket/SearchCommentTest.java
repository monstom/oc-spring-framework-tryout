package ticket.model.search.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("SearchCommentClass_UniteTesting")
public class SearchCommentTest {

	private SearchComment first_searchComment;
	
	@BeforeEach
	public void init_SearchComment() {
		System.out.println("SearchCommentTest - Initialization before each test ");
		first_searchComment = new SearchComment();
	}
	
	@AfterEach
	public void end_SearchComment() {
		first_searchComment = null;
		System.out.println("SearchCommentTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_SearchCommentTest() {
		System.out.println("SearchCommentTest - Start of unite testing for class SearchComment");
	}
	
	@AfterAll 
	public static void end_SearchCommentTest() {
		System.out.println("SearchCommentTest - End of unite testing for class SearchComment");
	}
	
	@ParameterizedTest(name = "The researched primary and foreign author/ticket keys of the comment ({0},{1},{2}) must allow the aggregation of the search attributes !")
	@CsvSource({ "1,2,3", "1,0,2", "9,9,-5", "0,-3,-4", "-4,-2,-1", "0,0,0" })
	@Tag("SearchComment-Aggregation_valid")
	public void validAggregationOf_SearchComment(int commentID, int ticketID, int authorID) {
		// Arrange		

		// Assert 0 
		assertTrue(this.first_searchComment.getSearchedCommentID() == 0
				|| this.first_searchComment.getSearchedCommentID() == (Integer)null);
		assertTrue(this.first_searchComment.getSearchedTicket() == 0
				|| this.first_searchComment.getSearchedTicket() == (Integer)null);
		assertTrue(this.first_searchComment.getSearchedAuthor() == 0
				|| this.first_searchComment.getSearchedAuthor() == (Integer)null);
		
		// Act 1 
		try {
			this.first_searchComment = this.first_searchComment.setSearchedCommentID(commentID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchComment.getSearchedCommentID() == 0);
			return;
		}
		
		// Assert 1
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertTrue(this.first_searchComment.getSearchedTicket() == 0
				|| this.first_searchComment.getSearchedTicket() == (Integer)null);
		assertTrue(this.first_searchComment.getSearchedAuthor() == 0
				|| this.first_searchComment.getSearchedAuthor() == (Integer)null);
		
		// Act 2
		try {
			this.first_searchComment = this.first_searchComment.setSearchedTicket(ticketID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchComment.getSearchedTicket() == 0);
			return;
		}
		
		// Assert 2 
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertSame(this.first_searchComment.getSearchedTicket(),ticketID);
		assertTrue(this.first_searchComment.getSearchedAuthor() == 0
				|| this.first_searchComment.getSearchedAuthor() == (Integer)null);
		
		// Act 3
		try {
			this.first_searchComment = this.first_searchComment.setSearchedAuthor(authorID);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(this.first_searchComment.getSearchedAuthor() == 0);
			return;
		}
		
		// Assert 3
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertSame(this.first_searchComment.getSearchedTicket(),ticketID);
		assertSame(this.first_searchComment.getSearchedAuthor(),authorID);
	}

}
