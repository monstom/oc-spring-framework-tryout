package ticket.model.beans.tickets;

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
		System.out.println("CommentTest - Appel avant chaque test");
		first_comment = new Comment();
	}
	
	@AfterEach
	public void end_Comment() {
		first_comment = null;
		System.out.println("CommentTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_CommentTest() {
		System.out.println("CommentTest - Début du test de classe Comment");
	}
	
	@AfterAll 
	public static void end_CommentTest() {
		System.out.println("CommentTest - Fin du test de classe Comment");
	}
	
	
	@ParameterizedTest(name = "La clé primaire du commentaire : id ({0}) ne doit pas être négative ou nulle !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Comment-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_CommentID(int arg1) {
		// Arrange
		
		// Act
		first_comment.setCommentID(arg1);
		
		// Assert
		assertTrue(first_comment.getCommentID() > 0);
	}
	
	
	@ParameterizedTest(name = "La description du commentaire ({0}) ne doit pas dépasser 1000 caractères !")
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
		first_comment.setComment_description(arg1);
		
		// Assert
		assertTrue( first_comment.getComment_description().length() < this.comment_description_length );
	}
	
	
	@ParameterizedTest(name = "La description du commentaire ({0}) ne doit pas être vide ou nulle !")
	@ValueSource(strings = { "Description", "statut non défini pour le ticket actuel", " " })
	@Tag("Comment-label_isEmpty_ness")
	public void isEmpty_Label(String arg1) {
		// Arrange
		
		// Act
		first_comment.setComment_description(arg1);
		
		// Assert
		assertFalse( first_comment.getComment_description().isEmpty() 
				  || first_comment.getComment_description().isBlank() );
	}
	
	
	@ParameterizedTest(name = "Les clés étrangères du commentaire : userID et ticketID ({0},{1}) ne doivent pas être négatives ou nulles !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("Comment-foreignKeys_UserTicket_invalidValue")
	public void invalidValueOf_ForeignKeys_UserID_TicketID(int arg1, int arg2) {
		// Arrange
		
		// Act
		first_comment.setComment_userID(arg1);
		first_comment.setComment_ticketID(arg2);
		
		// Assert
		assertTrue(first_comment.getComment_userID() > 0 
				&& first_comment.getComment_ticketID() > 0);
	}
}
