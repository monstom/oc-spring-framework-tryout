package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchHistoryStatut {
	
	private int searched_ticket;
	private int searched_statut;
	private String searched_date;
	private int searched_comment;
	private int searched_author;
	
	public SearchHistoryStatut() {}

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchHistoryStatut setSearchedTicket(int searched_ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the ticket of an history of status must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedStatut() {
		return searched_statut;
	}
	
	public SearchHistoryStatut setSearchedStatut(int searched_statut) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_statut,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the status of an history of status must not be negative or equal 0 !");
		else 
			this.searched_statut = searched_statut;
		return this;
	}

	public String getSearchedCreationDate() {
		return searched_date;
	}

	public SearchHistoryStatut setSearchedCreationDate(String searched_cdate) throws InvalidAttributeDateException {
		if(!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd") &&
			!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd HH:mm:ss")) 
			throw new InvalidAttributeDateException("The researched index identifying the creation date of an history of status must be in the correct format !");
		else this.searched_date = searched_cdate;
		return this;
	}

	public int getSearchedComment() {
		return searched_comment;
	}

	public SearchHistoryStatut setSearchedComment(int searched_comment) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_comment,-1)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the comment of an history of status must not be negative or equal 0 !");
		else 
			this.searched_comment = searched_comment;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchHistoryStatut setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the author of an history of status must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
	
}
