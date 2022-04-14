package ticket.model.search.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;
import ticket.model.search.ticket.SearchTicket;

public class SearchBug extends SearchTicket {
	
	private int searched_severity;

	public SearchBug() {}

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
