package ticket.model.search.ticket;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchTicket {

	private int searched_ticketID;
	private String searched_title;
	private String searched_desc;
	private String searched_cdate;
	private int searched_statut;
	private int searched_author;
	private int searched_project;
	
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
		else this.searched_ticketID = searched_ticketID;
		return this;
	}

	public String getSearchedTitle() {
		return searched_title;
	}

	public SearchTicket setSearchedTitle(String searched_title) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_title))
			throw new InvalidAttributeLengthException("The researched index identifying the title of a ticket must not be empty or blank !");			
		else if(GenericValidator.minLength(searched_title,300)) 
			throw new InvalidAttributeLengthException("The researched index identifying the title of a ticket must not contains more than 300 characters !");
		else this.searched_title = searched_title;
		return this;
	}
	
	public String getSearchedDescription() {
		return searched_desc;
	}

	public SearchTicket setSearchedDescription(String searched_desc) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_desc))
			throw new InvalidAttributeLengthException("The researched index identifying the description of a ticket must not be empty or blank !");			
		else if(GenericValidator.minLength(searched_desc,1000)) 
			throw new InvalidAttributeLengthException("The researched index identifying the description of a ticket must not contains more than 1000 characters !");
		else this.searched_desc = searched_desc;
		return this;
	}

	public String getSearchedCreationDate() {
		return searched_cdate;
	}

	public SearchTicket setSearchedCreationDate(String searched_cdate) throws InvalidAttributeDateException {
		if(!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd") &&
			!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd HH:mm:ss")) 
			throw new InvalidAttributeDateException("The researched index identifying the creation date of a ticket must be in the correct format !");
		else this.searched_cdate = searched_cdate;
		return this;
	}
	
	public int getSearchedStatut() {
		return searched_statut;
	}

	public SearchTicket setSearchedStatut(int searched_statut) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_statut,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the status of a ticket must not be negative or equal 0 !");
		else 
			this.searched_statut = searched_statut;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchTicket setSearchedAuthor(int searched_author) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_author,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the author of a ticket must not be negative or equal 0 !");
		else 
			this.searched_author = searched_author;
		return this;
	}
	
	public int getSearchedProject() {
		return searched_project;
	}

	public SearchTicket setSearchedProject(int searched_project) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_project,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the project of a ticket must not be negative or equal 0 !");
		else 
			this.searched_project = searched_project;
		return this;
	}
	
}
