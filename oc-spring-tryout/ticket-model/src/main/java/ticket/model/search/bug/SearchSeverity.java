package ticket.model.search.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchSeverity {

	private int searched_severityID;
	private int searched_level;
	
	public SearchSeverity() {}

	public int getSearchedSeverityID() {
		return searched_severityID;
	}

	public SearchSeverity setSearchedSeverityID(int searched_SID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_SID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a severity must not be negative or equal 0 !");
		else 
			this.searched_severityID = searched_SID;
		return this;
	}

	public int getSearchedLevel() {
		return searched_level;
	}

	public SearchSeverity setSearchedLevel(int searched_level) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_level,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a severity's level must not be negative or equal 0 !");
		else 
			this.searched_level = searched_level;
		return this;
	}

}
