package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchTicketAssociation {
	
	private int searched_ticket1ID;
	private int searched_ticket2ID;
	
	public SearchTicketAssociation() {}
	
	public int getSearchedTicket1AssoID() {
		return searched_ticket1ID;
	}
	public SearchTicketAssociation setSearchedTicket1AssoID(int searched_ticket1ID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket1ID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the first ticket of the association must not be negative or equal 0 !");
		else 
			this.searched_ticket1ID = searched_ticket1ID;
		return this;
	}

	public int getSearchedTicket2AssoID() {
		return searched_ticket2ID;
	}

	public SearchTicketAssociation setSearchedTicket2AssoID(int searched_ticket2ID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket2ID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the second ticket of the association must not be negative or equal 0 !");
		else 
			this.searched_ticket2ID = searched_ticket2ID;
		return this;
	}
}
