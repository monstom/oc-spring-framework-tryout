package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchStatutHistory {
	
	private int searched_ticket;
	private int searched_statut;
	private int searched_comment;
	private int searched_author;
	
	public SearchStatutHistory() {}

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchStatutHistory setSearchedTicket(int searched_ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticket,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a status history must not be negative or equal 0 !");
		else 
			this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedStatut() {
		return searched_statut;
	}
	
	public SearchStatutHistory setSearchedStatut(int searched_statut) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_statut,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a status history's statut must not be negative or equal 0 !");
		else 
			this.searched_statut = searched_statut;
		return this;
	}

	public int getSearchedComment() {
		return searched_comment;
	}

	public SearchStatutHistory setSearchedComment(int searched_comment) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_comment,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a status history's comment must not be negative or equal 0 !");
		else 
			this.searched_comment = searched_comment;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchStatutHistory setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a status history's author must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
	
}
