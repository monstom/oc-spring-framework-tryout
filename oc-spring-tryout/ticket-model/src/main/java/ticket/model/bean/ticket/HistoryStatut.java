package ticket.model.bean.ticket;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class HistoryStatut {

	//private static final Logger logger = LoggerFactory.getLogger(HistoryStatut.class);
	
	private int id_ticket;
	private int id_statut;
	
	private Date creation_date;
	private int id_user;
	private int id_comment;
	
	protected HistoryStatut() {}
	
	public HistoryStatut(int ticket, int statut, String cdate, int user, int comment) {
		this(ticket,statut);
		try {
			this.setHistory_creationDate(cdate);
			this.setHistory_userID(user);
			this.setHistory_commentID(comment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("history of status bean successfully created/retrieved with ids : "+ticket+","+status+"  !");
	}
	
	public HistoryStatut(int ticket, int statut) {
		try {
			this.setHistory_ticketID(ticket);
			this.setHistory_statutID(statut);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("history of status bean successfully created/retrieved by its ids : "+ticket+","+status+" !");
	}
	
	public int getHistory_ticketID() {
		return this.id_ticket;
	}

	public void setHistory_ticketID(int ticket_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the ticket of an history of status must not be negative or equal 0 !");
		else this.id_ticket = ticket_id;
	}

	public int getHistory_statutID() {
		return this.id_statut;
	}

	public void setHistory_statutID(int statut_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(statut_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the status of an history of status must not be negative or equal 0 !");
		else this.id_statut = statut_id;
	}

	public Date getHistory_creationDate() {
		return this.creation_date;
	}

	public void setHistory_creationDate(String cdate) throws InvalidAttributeDateException {
		if(!TimeValidator.getInstance().isValid(cdate, "yyyy-mm-dd HH:mm:ss")) 
			throw new InvalidAttributeDateException("The creation date of an history of status must be in the correct format !");
		else this.creation_date = Timestamp.valueOf(cdate);
	}

	public int getHistory_userID() {
		return this.id_user;
	}

	public void setHistory_userID(int user_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(user_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the author of an history of status must not be negative or equal 0 !");
		else this.id_user = user_id;
	}

	public int getHistory_commentID() {
		return this.id_comment;
	}

	public void setHistory_commentID(int comment_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(comment_id,-1)) 
			throw new InvalidAttributeNumericValueException("The key identifying the comment of an history of status must not be negative or equal 0 !");
		else this.id_comment = comment_id;
	}
	
	public String toString() {
		return "--- HistoryStatut Object ---\n ticket_id : "+ this.id_ticket
				+"\n statut_id : "+ this.id_statut
				+"\n creation_date : "+ this.creation_date.toString()
				+"\n comment_id : "+ this.id_comment
				+"\n author_id : "+ this.id_user+"\n";
	}
}
