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


public class SearchCommentTest {

	private SearchComment first_searchComment;
	
	@BeforeEach
	public void init_SearchComment() {
		System.out.println("SearchCommentTest - Appel avant chaque test");
		first_searchComment = new SearchComment();
	}
	
	@AfterEach
	public void end_SearchComment() {
		first_searchComment = null;
		System.out.println("SearchCommentTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_SearchCommentTest() {
		System.out.println("SearchCommentTest - Début du test de classe SearchComment");
	}
	
	@AfterAll 
	public static void end_SearchCommentTest() {
		System.out.println("SearchCommentTest - Fin du test de classe SearchComment");
	}
	
	@ParameterizedTest(name = "Lidentifiant ainsi que le ticket et lauteur recherchés pour un commentaire ({0},{1},{2}) doivent permettre l'agrégation des attributs de recherche !")
	@CsvSource({ "1,2,3", "1,0,2", "-9,9,5", "0,-3,-4", "-4,-2,-1", "0,0,0" })
	@Tag("SearchComment-Aggregation_valid")
	public void validAggregationOf_SearchComment(int commentID, int ticketID, int authorID) {
		// Arrange
		
		// Act 1 
		this.first_searchComment = this.first_searchComment.setSearchedCommentID(commentID);
		
		// Assert 1
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertTrue(this.first_searchComment.getSearchedTicket() == 0);
		assertTrue(this.first_searchComment.getSearchedAuthor() == 0);
		
		// Act 2
		this.first_searchComment = this.first_searchComment.setSearchedTicket(ticketID);
		
		// Assert 2 
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertSame(this.first_searchComment.getSearchedTicket(),ticketID);
		assertTrue(this.first_searchComment.getSearchedAuthor() == 0);
		
		// Act 3
		this.first_searchComment = this.first_searchComment.setSearchedAuthor(authorID);
		
		// Assert 3
		assertSame(this.first_searchComment.getSearchedCommentID(),commentID);
		assertSame(this.first_searchComment.getSearchedTicket(),ticketID);
		assertSame(this.first_searchComment.getSearchedAuthor(),authorID);
	}

}
