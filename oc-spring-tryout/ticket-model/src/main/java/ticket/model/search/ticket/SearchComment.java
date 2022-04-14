package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchComment {
	
	private int searched_commentID;
	private String searched_desc;
	private int searched_ticket;
	private int searched_author;
	
	public SearchComment() {}

	public int getSearchedCommentID() {
		return searched_commentID;
	}

	public SearchComment setSearchedCommentID(int searched_commentID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_commentID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a comment must not be negative or equal 0 !");
		else 
			this.searched_commentID = searched_commentID;
		return this;
	}

	public String getSearchedDescription() {
		return searched_desc;
	}

	public SearchComment setSearchedDescription(String searched_desc) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_desc)) 
			throw new InvalidAttributeLengthException("The reaserched index identifying the description of a comment must not be empty or blank !");
		else if(GenericValidator.minLength(searched_desc,1000)) 
			throw new InvalidAttributeLengthException("The reaserched index identifying the description of a comment must not contains more than 1000 characters !");
		else this.searched_desc = searched_desc;
		return this;
	}

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchComment setSearchedTicket(int searched_ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the ticket of a comment must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchComment setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the author of a comment must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
}
