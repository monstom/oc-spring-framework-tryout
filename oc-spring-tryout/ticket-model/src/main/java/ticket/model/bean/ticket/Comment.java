package ticket.model.bean.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Comment {

	private int id_comment;
	
	private String description;
	private int id_user;
	private int id_ticket;
	
	Comment() {}
	
	public Comment(int id, String desc, int user, int ticket) {
		this(desc,user,ticket);
		try {
			this.setCommentID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Comment(String desc, int user, int ticket) {
		this(desc);
		try {
			this.setComment_userID(user);
			this.setComment_ticketID(ticket);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Comment(int id, String desc) {
		this(desc);
		try {
			this.setCommentID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Comment(String desc) {
		try {
			this.setCommentID(0);
			this.setComment_description(desc);
			this.setComment_userID(0);
			this.setComment_ticketID(0);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getCommentID() {
		return this.id_comment;
	}
	
	public void setCommentID(int comment_id) throws InvalidAttributeNumericValueException{
		if(GenericValidator.maxValue(comment_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a comment must not be negative or equal 0 !");
		else this.id_comment = comment_id;
	}
	
	public String getComment_description() {
		return this.description;
	}
	
	public void setComment_description(String desc) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(desc)) 
			throw new InvalidAttributeLengthException("The description of a comment must not be empty or blank !");
		else if(GenericValidator.minLength(desc,1000)) 
			throw new InvalidAttributeLengthException("The description of a comment must not contains more than 100 characters !");
		else this.description = desc;
	}

	public int getComment_userID() {
		return this.id_user;
	}

	public void setComment_userID(int user_id) throws InvalidAttributeNumericValueException{
		if(GenericValidator.maxValue(user_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the comment's author must not be negative or equal 0 !");
		else this.id_user = user_id;
	}

	public int getComment_ticketID() {
		return this.id_ticket;
	}

	public void setComment_ticketID(int ticket_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the comment's ticket must not be negative or equal 0 !");
		else this.id_ticket = ticket_id;
	}	
}
