package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchTicket {

	private int searched_ticketID;
	private int searched_statut;
	private int searched_project;
	private int searched_author;
	
	public SearchTicket() {}
	
	public SearchTicket(int searched_TID) {
		try {
			this.setSearchedTicketID(searched_TID);
		} catch(InvalidAttributeNumericValueException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getSearchedTicketID() {
		return searched_ticketID;
	}

	public SearchTicket setSearchedTicketID(int searched_ticketID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_ticketID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a ticket must not be negative or equal 0 !");
		else 
			this.searched_ticketID = searched_ticketID;
		return this;
	}

	public int getSearchedStatut() {
		return searched_statut;
	}

	public SearchTicket setSearchedStatut(int searched_statut) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_statut,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a ticket's status must not be negative or equal 0 !");
		else 
			this.searched_statut = searched_statut;
		return this;
	}

	public int getSearchedProject() {
		return searched_project;
	}

	public SearchTicket setSearchedProject(int searched_project) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_project,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a ticket's project must not be negative or equal 0 !");
		else 
			this.searched_project = searched_project;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchTicket setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a ticket's author must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
}
