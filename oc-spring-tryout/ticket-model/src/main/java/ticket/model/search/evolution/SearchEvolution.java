package ticket.model.search.evolution;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;
import ticket.model.search.ticket.SearchTicket;

public class SearchEvolution extends SearchTicket {

	private int searched_ticket;
	private int searched_priority;
	
	public SearchEvolution() {}

	public int getSearchedTicketID() {
		return searched_ticket;
	}

	public SearchEvolution setSearchedTicketID(int searched_ticketID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticketID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the ticket of an evolution must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticketID;
		return this;
	}
	
	public int getSearchedPriority() {
		return searched_priority;
	}

	public SearchEvolution setSearchedPriority(int searched_priority) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_priority,0)) 
			throw new InvalidAttributeNumericValueException("The researched index identifying the priority of an evolution must not be negative or equal 0 !");
		else 
			this.searched_priority = searched_priority;
		return this;
	}

}
