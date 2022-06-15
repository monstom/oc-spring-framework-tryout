package ticket.model.bean.user;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class User {

	//private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	private int id_user;
	
	private String firstname;
	private String lastname;
	
	protected User() {}
	
	public User(int id, String fname, String lname) throws InvalidAttributeNumericValueException, InvalidAttributeLengthException {
		this(fname,lname);
		this.setUserID(id);
		//logger.info("user bean successfully created/retrieved with id : "+id+" !");
	}
	
	public User(String fname, String lname) throws InvalidAttributeLengthException {
		this.setUser_firstname(fname);
		this.setUser_lastname(lname);
		//logger.info("anonymous user bean successfully created/retrieved !");
	}
	
	public User(int id) throws InvalidAttributeNumericValueException {
		this.setUserID(id);
		//logger.info("user bean successfully created/retrieved by its id : "+id+" !");
	}

	public int getUserID() {
		return this.id_user;
	}

	public void setUserID(int id_user) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(id_user,0)) 
			throw new InvalidAttributeNumericValueException("The identifying key of a user must not be negative or equal 0 !");
		else this.id_user = id_user;
	}

	public String getUser_firstname() {
		return this.firstname;
	}

	public void setUser_firstname(String firstname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(firstname)) 
			throw new InvalidAttributeLengthException("The firstname of a user must not be empty or blank !");
		else if(GenericValidator.minLength(firstname,100)) 
			throw new InvalidAttributeLengthException("The firstname of a user must not contains more than 100 characters !");
		else this.firstname = firstname;
	}

	public String getUser_lastname() {
		return this.lastname;
	}

	public void setUser_lastname(String lastname) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(lastname)) 
			throw new InvalidAttributeLengthException("The lastname of a user must not be empty or blank !");
		else if(GenericValidator.minLength(lastname,100)) 
			throw new InvalidAttributeLengthException("The lastname of a user must not contains more than 100 characters !");
		else this.lastname = lastname;
	}
	
	public String toString() {
		return "--- User Object ---\n id : "+ this.id_user +"\n firstname : "+ this.firstname +"\n lastname : "+ this.lastname +"\n";
	}

}
