package ticket.model.search.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchBugVersionAssociation {
	
	private int searched_bug;
	private int searched_VID;
	private String searched_vlabel;

	public SearchBugVersionAssociation() {}

	public int getSearchedBugID() {
		return searched_bug;
	}

	public SearchBugVersionAssociation setSearchedBugID(int searched_bug) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_bug,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a bug must not be negative or equal 0 !");
		else 
			this.searched_bug = searched_bug;
		return this;
	}

	public int getSearchedVersionID() {
		return searched_VID;
	}

	public SearchBugVersionAssociation setSearchedVersionID(int searched_version) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_version,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a version must not be negative or equal 0 !");
		else 
			this.searched_VID = searched_version;
		return this;
	}

	public String getSearchedVersionLabel() {
		return searched_vlabel;
	}

	public SearchBugVersionAssociation setSearchedVersionLabel(String searched_vlabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_vlabel)) 
			throw new InvalidAttributeLengthException("The researched index identifying a version's label must not be empty or blank !");
		else if(GenericValidator.minLength(searched_vlabel,30)) 
			throw new InvalidAttributeLengthException("The researched index identifying a version's label must not contains more than 100 characters !");
		else 
			this.searched_vlabel = searched_vlabel;
		return this;
	}
}
