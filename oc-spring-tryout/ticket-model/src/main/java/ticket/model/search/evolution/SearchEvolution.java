package ticket.model.search.evolution;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;
import ticket.model.search.ticket.SearchTicket;

public class SearchEvolution extends SearchTicket {

	private int searched_priority;
	
	public SearchEvolution() {}

	public int getSearchedPriority() {
		return searched_priority;
	}

	public SearchEvolution setSearchedPriority(int searched_priority) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_priority,0)) 
			throw new InvalidAttributeNumericValueException("The researched index identifying an evolution's priority must not be negative or equal 0 !");
		else 
			this.searched_priority = searched_priority;
		return this;
	}

}
