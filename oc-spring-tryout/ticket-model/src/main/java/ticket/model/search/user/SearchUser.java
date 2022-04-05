package ticket.model.search.user;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchUser {
	
	private int searched_userID;
	private String searched_fullname;
	
	
	public SearchUser() {}
	
	public int getSearchedUserID() {
		return searched_userID;
	}

	public SearchUser setSearchedUserID(int searched_userID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_userID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a user must not be negative or equal 0 !");
		else 
			this.searched_userID = searched_userID;
		return this;
	}

	public String getSearchedFullname() {
		return searched_fullname;
	}

	public SearchUser setSearchedFullname(String searched_fname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_fname)) 
			throw new InvalidAttributeLengthException("The researched index identifying a user's fullname must not be empty or blank !");
		else if(GenericValidator.minLength(searched_fname,200)) 
			throw new InvalidAttributeLengthException("The researched index identifying a user's fullname must not contains more than 100 characters !");
		else
			this.searched_fullname = searched_fname;
		return this;
	}
}
