package ticket.model.search.user;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchUser {
	
	private int searched_userID;
	private String searched_firstname;
	private String searched_lastname;
	
	
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

	public String getSearchedFirstname() {
		return searched_firstname;
	}
	
	public SearchUser setSearchedFirstname(String searched_fname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_fname)) 
			throw new InvalidAttributeLengthException("The researched index identifying the firstname of a user must not be empty or blank !");
		else if(GenericValidator.minLength(searched_fname,100)) 
			throw new InvalidAttributeLengthException("The researched index identifying the firstname of a user must not contains more than 100 characters !");
		else
			this.searched_firstname = searched_fname;
		return this;
	}
	
	public String getSearchedLastname() {
		return searched_lastname;
	}
	
	public SearchUser setSearchedLastname(String searched_lname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_lname)) 
			throw new InvalidAttributeLengthException("The researched index identifying the lastname of a user must not be empty or blank !");
		else if(GenericValidator.minLength(searched_lname,100)) 
			throw new InvalidAttributeLengthException("The researched index identifying the lastname of a user must not contains more than 100 characters !");
		else
			this.searched_lastname = searched_lname;
		return this;
	}
	
	public SearchUser setSearchedNames(String searched_fname, String searched_lname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_fname) && GenericValidator.isBlankOrNull(searched_lname)) 
			throw new InvalidAttributeLengthException("The researched indexes identifying the names of a user must not be both undefined !");

		if(!GenericValidator.isBlankOrNull(searched_fname))
			this.setSearchedFirstname(searched_fname);
		
		if(!GenericValidator.isBlankOrNull(searched_lname))
			this.setSearchedLastname(searched_lname);
		
		return this;
	}
}
