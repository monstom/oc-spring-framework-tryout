package ticket.model.bean.ticket;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Tag("TicketAssociationClass_UniteTesting")
public class TicketAssociationTest {
	
	private TicketAssociation first_ticketAsso;
	
	
	@BeforeEach
	public void init_TicketAssociation() {
		System.out.println("TicketAssociationTest - Initialization before each test ");
		first_ticketAsso = new TicketAssociation();
	}
	
	@AfterEach
	public void end_TicketAssociation() {
		first_ticketAsso = null;
		System.out.println("TicketAssociationTest - Clean after each test ");
	}
	
	@BeforeAll 
	public static void init_TicketAssociationTest() {
		System.out.println("TicketAssociationTest - Start of unite testing for class TicketAssociation");
	}
	
	@AfterAll 
	public static void end_TicketAssociationTest() {
		System.out.println("TicketAssociationTest - End of unite testing for class TicketAssociation");
	}
	
	@ParameterizedTest(name = "Both primary and foreign ticket keys of the association ({0},{1}) must not be negative or equal 0 !")
	@CsvSource({ "10, 5", "0, 6", "-3, 10", "7, -9", "15, 0", "-1, 0", "0, -9", "-7, -14", "0, 0" })
	@Tag("TicketAssociation-primaryKeys_bothTickets_invalidValue")
	public void invalidValueOf_PrimaryKeys_FirstTicketID_SecondTicketID(int arg1, int arg2) {
		// Arrange
		
		// Act
		try {
			first_ticketAsso.setAssociation_mainTicketID(arg1);
			first_ticketAsso.setAssociation_associatedTicketID(arg2);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			assertTrue(first_ticketAsso.getAssociation_mainTicketID() <= 0
					|| first_ticketAsso.getAssociation_associatedTicketID() <= 0);
			return;
		}
		
		// Assert
		assertTrue(first_ticketAsso.getAssociation_mainTicketID() > 0
				&& first_ticketAsso.getAssociation_associatedTicketID() > 0);
	}
	
}
