package ticket.model.bean.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Comment {

	//private static final Logger logger = LoggerFactory.getLogger(Comment.class);
	
	private int id_comment;
	
	private String description;
	private int id_user;
	private int id_ticket;
	
	protected Comment() {}
	
	public Comment(int id, String desc, int user, int ticket) {
		this(desc,user,ticket);
		try {
			this.setCommentID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("comment bean successfully created/retrieved with id :"+id+" !");
	}
	
	public Comment(String desc, int user, int ticket) {
		try {
			this.setComment_description(desc);
			this.setComment_userID(user);
			this.setComment_ticketID(ticket);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("anonymous comment bean successfully created/retrieved !");
	}
	
	public Comment(int id) {
		try {
			this.setCommentID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("comment bean successfully created/retrieved by its id :"+id+" !");
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
			throw new InvalidAttributeNumericValueException("The key identifying the author of a comment must not be negative or equal 0 !");
		else this.id_user = user_id;
	}

	public int getComment_ticketID() {
		return this.id_ticket;
	}

	public void setComment_ticketID(int ticket_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the ticket of a comment must not be negative or equal 0 !");
		else this.id_ticket = ticket_id;
	}
		
	public String toString() {
		return "--- Comment Object ---\n id : "+ this.id_comment
				+"\n description : "+ this.description
				+"\n author_id : "+ this.id_user
				+"\n ticket_id : "+ this.id_ticket+"\n";
	}
}
