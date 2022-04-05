package ticket.model.bean.ticket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("TicketAssociationClass_UniteTesting")
public class TicketAssociationTest {
	
	private TicketAssociation first_ticket_association;
	
	
	@BeforeEach
	public void init_TicketAssociation() {
		System.out.println("TicketAssociationTest - Appel avant chaque test");
		first_ticket_association = new TicketAssociation();
	}
	
	@AfterEach
	public void end_TicketAssociation() {
		first_ticket_association = null;
		System.out.println("TicketAssociationTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_TicketAssociationTest() {
		System.out.println("TicketAssociationTest - Début du test de classe TicketAssociation");
	}
	
	@AfterAll 
	public static void end_TicketAssociationTest() {
		System.out.println("TicketAssociationTest - Fin du test de classe TicketAssociation");
	}
	
	@ParameterizedTest(name = "Les clés primaires de lassociation : ticketID_1 et ticketID_2 ({0},{1}) ne doivent pas être négatives ou nulles  !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "70, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("TicketAssociation-primaryKeys_bothTickets_invalidValue")
	public void invalidValueOf_PrimaryKeys_FirstTicketID_SecondTicketID(int arg1, int arg2) {
		// Arrange
		
		// Act
		try {
			first_ticket_association = new TicketAssociation(arg1,arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Assert
		assertTrue(first_ticket_association.getAssociation_mainTicketID() > 0
				&& first_ticket_association.getAssociation_associatedTicketID() > 0);
	}
	
}
