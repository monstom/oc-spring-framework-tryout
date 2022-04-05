package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchComment {
	
	private int searched_commentID;
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

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchComment setSearchedTicket(int searched_ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a comment's ticket must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchComment setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a comment's author must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
}
