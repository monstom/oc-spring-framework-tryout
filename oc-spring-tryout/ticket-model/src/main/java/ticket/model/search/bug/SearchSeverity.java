package ticket.model.search.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchSeverity {

	private int searched_severityID;
	private int searched_level;
	private String searched_label;
	
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
			throw new InvalidAttributeNumericValueException("The researched index identifying the level of a severity must not be negative or equal 0 !");
		else 
			this.searched_level = searched_level;
		return this;
	}

	public SearchSeverity setSearchedLabel(String searched_label) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_label)) 
			throw new InvalidAttributeLengthException("The researched index identifying the label of a severity must not be empty or blank !");
		else if(GenericValidator.minLength(searched_label,100)) 
			throw new InvalidAttributeLengthException("The researched index identifying the label of a severity must not contains more than 100 characters !");
		else
			this.searched_label = searched_label;
		return this;
	}

	public String getSearchedLabel() {
		return searched_label;
	}

}
