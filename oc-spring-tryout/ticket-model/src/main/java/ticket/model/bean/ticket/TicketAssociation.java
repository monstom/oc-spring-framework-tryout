package ticket.model.bean.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class TicketAssociation {
	
	private int id_ticket;
	private int id_associatedTicket;
	
	TicketAssociation() {}
	
	TicketAssociation(int first_ticket, int second_ticket) {
		try {
			this.setAssociation_mainTicketID(first_ticket);
			this.setAssociation_associatedTicketID(second_ticket);
		} catch(InvalidAttributeNumericValueException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getAssociation_mainTicketID() {
		return this.id_ticket;
	}

	public void setAssociation_mainTicketID(int main_ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(main_ticket,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the first ticket of the association must not be negative or equal 0 !");
		else this.id_ticket = main_ticket;
	}

	public int getAssociation_associatedTicketID() {
		return id_associatedTicket;
	}

	public void setAssociation_associatedTicketID(int id_associatedTicket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(id_associatedTicket,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the second ticket of the association must not be negative or equal 0 !");
		else this.id_associatedTicket = id_associatedTicket;
	}
}
