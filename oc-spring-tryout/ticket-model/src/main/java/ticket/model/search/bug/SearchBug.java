package ticket.model.search.bug;

import org.apache.commons.validator.GenericValidator;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchBug {
	
	private int searched_ticket;
	private int searched_severity;

	public SearchBug() {}
	
	public int getSearchedTicketID() {
		return searched_ticket;
	}

	public SearchBug setSearchedTicketID(int searched_ticketID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticketID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the ticket of a bug must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticketID;
		return this;
	}

	public int getSearchedSeverity() {
		return searched_severity;
	}

	public SearchBug setSearchedSeverity(int searched_severity) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_severity,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the severity of a bug must not be negative or equal 0 !");
		else 
			this.searched_severity = searched_severity;
		return this;
	}

}
