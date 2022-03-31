package ticket.model.bean.ticket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Timestamp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


@Tag("TicketClass_UniteTesting")
public class TicketTest {
	
	private final int ticket_title_length = 300;
	private final int ticket_description_length = 1000;
	
	private Ticket first_ticket;
	
	
	@BeforeEach
	public void init_Ticket() {
		System.out.println("TicketTest - Appel avant chaque test");
		first_ticket = new Ticket();
	}
	
	@AfterEach
	public void end_Ticket() {
		first_ticket = null;
		System.out.println("TicketTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_TicketTest() {
		System.out.println("TicketTest - Début du test de classe Ticket");
	}
	
	@AfterAll 
	public static void end_TicketTest() {
		System.out.println("TicketTest - Fin du test de classe Ticket");
	}
	
	
	@ParameterizedTest(name = "La clé primaire du ticket : id ({0}) ne doit pas être négative ou nulle  !")
	@ValueSource(ints = { 10, -6, 0 })
	@Tag("Ticket-primaryKey_invalidValue")
	public void invalidValueOf_PrimaryKey_TicketID(int arg1) {
		// Arrange
		
		// Act
		first_ticket.setTicketID(arg1);
		
		// Assert
		assertTrue(first_ticket.getTicketID() > 0);
	}
	
	
	@ParameterizedTest(name = "Le titre du ticket ({0}) ne doit pas dépasser 300 caractères !")
	@ValueSource(strings = { "Soulevement du problème récurrent", 
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui",
							 "abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe"
							+"bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui"
							+"abfiubavucvvbebvubezbvuezbvezbvezbovibezbvoiebvbeuebrbrebrbrbrebrbrbrbrbrebrebberhrtbeevbevbuezbvuebvubevbe"
						  })
	@Tag("Ticket-title_invalidLength")
	public void invalidLengthOf_Title(String arg1) {
		// Arrange
		
		// Act
		first_ticket.setTicket_title(arg1);
		
		// Assert
		assertTrue(first_ticket.getTicket_title().length() < this.ticket_title_length);
	}
	
	@ParameterizedTest(name = "Le titre du ticket ({0}) ne doit pas être vide ou nul !")
	@ValueSource(strings = { "Soulevement du problème récurrent", "" })
	@Tag("Ticket-title_emptyness")
	public void isEmpty_Title(String arg1) {
		// Arrange
		
		// Act
		first_ticket.setTicket_title(arg1);
		
		// Assert
		assertFalse( first_ticket.getTicket_title().isEmpty() 
				  || first_ticket.getTicket_title().isBlank() );
	}
	
	
	@ParameterizedTest(name = "La date de création du ticket ({0}) doit être non nulle et correctement définie !")
	@ValueSource(strings = { "2022-03-17 17:15:23", "2022-03-17", "-8", " " })
	@Tag("Ticket-creationDate_invalidValue")
	public void isEmpty_CreationDate(String arg1) {
		// Arrange
		
		// Act
		try { 
			first_ticket.setTicket_creationDate(Timestamp.valueOf(arg1));
		} catch(IllegalArgumentException e) {
			fail(e.getMessage());
		}
				
		// Assert
		assertTrue(first_ticket.getTicket_creationDate().getTime() > 0);
	}
	
	
	@ParameterizedTest(name = "La description du ticket ({0}) ne doit pas dépasser 1000 caractères !")
	@ValueSource(strings = { "Soulevement du problème récurrent", 
							 "bzeuivbziueviubeziuvbuezbviuebviubeziuvbethetjegvbntrfbnsvdbzfesgtrjkuyljtykrhsffvsdbziubviuezbivbezivbiuezbvui",
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
	@Tag("Ticket-description_invalidLength")
	public void invalidLengthOf_Description(String arg1) {
		// Arrange
		
		// Act
		first_ticket.setTicket_description(arg1);
		
		// Assert
		assertTrue(first_ticket.getTicket_description().length() < this.ticket_description_length);
	}
	
	
	@Tag("Ticket-foreignKeys_StatutAuteurProject_invalidValue")
	@ParameterizedTest(name = "Les 3 clés étrangères du ticket : statutID, auteurID et projectID ({0},{1},{2}) ne doivent pas être négatifs ou nuls !")
	@CsvSource({ "1, 2, 3", "-1, -2, -3", "0, 0, 0",
				 "1, 0, 3", "10, 5, 0", "0, 5, 8", 
				 "-1, 0, 8", "1, -5, 0", "0, 5, -8", "1, 0, -8", "-1, 5, 0", "0, -5, 8",
				 "1, 0, 0", "0, 5, 0", "0, 0, 8", "-1, 0, 0", "0, -5, 0", "0, 0, -8",
				 "-1, 2, 3", "5, 2, -3", "5, -2, 3", 
				 "-1, -2, 3", "-5, 2, -3", "5, -2, -3",
				 "-1, -2, 0", "-5, 0, -3", "0, -2, -3"
	})
	public void invalidValueOf_ForeignKeys_StatutID_AuthorID_ProjectID(int arg1, int arg2, int arg3) {
		// Arrange
		
		// Act
		first_ticket.setTicket_statutID(arg1);
		first_ticket.setTicket_authorID(arg2);
		first_ticket.setTicket_projectID(arg3);
		
		// Assert
		assertTrue(first_ticket.getTicket_statutID() > 0
				&& first_ticket.getTicket_authorID() > 0 
				&& first_ticket.getTicket_projectID() > 0);
	}
	
}
