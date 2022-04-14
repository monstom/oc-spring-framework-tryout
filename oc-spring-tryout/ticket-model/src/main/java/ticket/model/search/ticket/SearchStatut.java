package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchStatut {
	
	private int searched_statutID;
	private String searched_slabel;
	
	public SearchStatut() {}
	
	public int getSearchedStatutID() {
		return searched_statutID;
	}

	public SearchStatut setSearchedStatutID(int searched_statutID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_statutID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a status must not be negative or equal 0 !");
		else 
			this.searched_statutID = searched_statutID;
		return this;
	}

	public String getSearchedLabel() {
		return searched_slabel;
	}

	public SearchStatut setSearchedLabel(String searched_slabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_slabel)) 
			throw new InvalidAttributeLengthException("The researched index identifying the label of a status must not be empty or blank !");
		else if(GenericValidator.minLength(searched_slabel,100)) 
			throw new InvalidAttributeLengthException("The researched index identifying the label of a status must not contains more than 100 characters !");
		else
			this.searched_slabel = searched_slabel;
		return this;
	}
}
