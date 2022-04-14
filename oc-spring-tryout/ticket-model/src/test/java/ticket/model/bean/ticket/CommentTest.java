package ticket.model.bean.ticket;

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


@Tag("CommentClass_UniteTesting")
public class CommentTest {

	private final int comment_description_length = 1000;
	
	private Comment first_comment;
	
	
	@BeforeEach
	public void init_Comment() {
		System.out.println("CommentTest - Initialization before each test ");
		first_comment = new Comment();
	}
	
	@AfterEach
	public void end_Comment() {
		first_comment = null;
		System.out.println("CommentTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_CommentTest() {
		System.out.println("CommentTest - Start of unite testing for class Comment");
	}
	
	@AfterAll 
	public static void end_CommentTest() {
		System.out.println("CommentTest - End of unite testing for class Comment");
	}
	

	@ParameterizedTest(name = "The primary key of the comment ({0}) must not be negative or equal 0 !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Comment-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_CommentID(int arg1) {
		// Arrange
		
		// Act
		try {
			first_comment.setCommentID(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_comment.getCommentID() <= 0);
			return;
		}
		
		// Assert
		assertTrue(first_comment.getCommentID() > 0);
	}
	

	@ParameterizedTest(name = "The description of the comment ({0}) must not contains more than 1000 characters !")
	@ValueSource(strings = { "Description", "statut non défini pour le ticket actuel",
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui" 
						   })
	@Tag("Comment-label_invalidLength")
	public void invalidLengthOf_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_comment.setComment_description(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_comment.getComment_description() == null);
			return;
		}
		
		// Assert
		assertTrue(first_comment.getComment_description().length() < this.comment_description_length);
	}
	

	@ParameterizedTest(name = "The description of the comment ({0}) must not be empty or blank !")
	@ValueSource(strings = { "Description", "statut non défini pour le ticket actuel", " " })
	@Tag("Comment-label_isEmpty")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		try {
			first_comment.setComment_description(arg1);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_comment.getComment_description() == null);
			return;
		}
		
		// Assert
		assertFalse( first_comment.getComment_description().isEmpty() 
				  || first_comment.getComment_description().isBlank() );
	}
	
	
	@ParameterizedTest(name = "The foreign user and ticket keys of the comment ({0},{1}) must not be negative or equal 0 !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("Comment-foreignKeys_UserTicket_invalidValue")
	public void invalidValueOf_ForeignKeys_UserID_TicketID(int arg1, int arg2) {
		// Arrange
		
		// Act
		try {
			first_comment.setComment_userID(arg1);
			first_comment.setComment_ticketID(arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_comment.getComment_userID() <= 0 
					|| first_comment.getComment_ticketID() <= 0);
			return;
		}
		
		// Assert
		assertTrue(first_comment.getComment_userID() > 0 
				&& first_comment.getComment_ticketID() > 0);
	}
}
