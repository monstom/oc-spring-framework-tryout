package ticket.model.search.project;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchVersion {
	
	private int searched_versionID;
	private String searched_label;
	
	public SearchVersion() {}

	public int getSearchedVersionID() {
		return searched_versionID;
	}

	public SearchVersion setSearchedVersionID(int searched_versionID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_versionID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a version must not be negative or equal 0 !");
		else 
			this.searched_versionID = searched_versionID;
		return this;
	}

	public String getSearchedLabel() {
		return searched_label;
	}

	public SearchVersion setSearchedLabel(String searched_label) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_label)) 
			throw new InvalidAttributeLengthException("The researched key identifying a version's label must not be empty or blank !");
		else if(GenericValidator.minLength(searched_label,30)) 
			throw new InvalidAttributeLengthException("The researched key identifying a version's label must not contains more than 100 characters !");
		else
			this.searched_label = searched_label;
		return this;
	}

}
