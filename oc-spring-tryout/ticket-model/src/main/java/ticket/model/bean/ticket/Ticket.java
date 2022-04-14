package ticket.model.bean.ticket;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Ticket {
	
	private int id_ticket;
	
	private String title;
	private Date creation_date;
	private String description;
	private int id_statut;
	private int id_author;
	private int id_project;
	
	protected Ticket() {}
	
	public Ticket(int id, String tname, String cdate, String desc, int statut, int author, int project) {
		this(tname,cdate,desc,statut,author,project);
		try {
			this.setTicketID(id);	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Ticket(int id, String tname, String cdate, int statut, int author, int project) {
		this(tname,cdate,null,statut,author,project);
		try {
			this.setTicketID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Ticket(String tname, String cdate, String desc, int statut, int author, int project) {
		this(tname,cdate,desc);
		try {
			this.setTicket_statutID(statut);
			this.setTicket_authorID(author);
			this.setTicket_projectID(project);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	protected Ticket(String tname, String cdate, String desc) {
		try {
			this.setTicket_title(tname);
			this.setTicket_creationDate(cdate);
			this.setTicket_description(desc);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getTicketID() {
		return this.id_ticket;
	}

	public void setTicketID(int ticket_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a ticket must not be negative or equal 0 !");
		else this.id_ticket = ticket_id;
	}

	public String getTicket_title() {
		return this.title;
	}

	public void setTicket_title(String title) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(title)) 
			throw new InvalidAttributeLengthException("The title of a ticket must not be empty or blank !");
		else if(GenericValidator.minLength(title,300)) 
			throw new InvalidAttributeLengthException("The title of a ticket must not contains more than 100 characters !");
		else this.title = title;
	}

	public Date getTicket_creationDate() {
		return this.creation_date;
	}

	public void setTicket_creationDate(String creation_time) throws InvalidAttributeDateException {
		if(!TimeValidator.getInstance().isValid(creation_time, "yyyy-mm-dd HH:mm:ss")) 
			throw new InvalidAttributeDateException("The creation date of a ticket must be in the correct format !");
		else this.creation_date = Timestamp.valueOf(creation_time);
	}

	public String getTicket_description() {
		return this.description;
	}

	public void setTicket_description(String desc) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(desc)) 
			throw new InvalidAttributeLengthException("The description of a ticket must not be empty or blank !");
		else if(GenericValidator.minLength(desc,1000)) 
			throw new InvalidAttributeLengthException("The description of a ticket must not contains more than 100 characters !");
		else this.description = desc;
	}

	public int getTicket_statutID() {
		return this.id_statut;
	}

	public void setTicket_statutID(int statut_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(statut_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the status of a ticket must not be negative or equal 0 !");
		else this.id_statut = statut_id;
	}

	public int getTicket_authorID() {
		return this.id_author;
	}

	public void setTicket_authorID(int author_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(author_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the author of a ticket must not be negative or equal 0 !");
		else this.id_author = author_id;
	}

	public int getTicket_projectID() {
		return this.id_project;
	}

	public void setTicket_projectID(int project_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(project_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the project of a ticket must not be negative or equal 0 !");
		else this.id_project = project_id;
	}
	
	
	public String toString() {
		return "--- Ticket Object ---\n id : "+ this.id_ticket 
				+"\n title ; "+ this.title
				+"\n creation date : "+ this.creation_date.toString()
				+"\n description ; "+ this.description
				+"\n statut_id ; "+ this.id_statut
				+"\n author_id ; "+ this.id_author
				+"\n project_id ; "+ this.id_project +"\n";
	}
	
}