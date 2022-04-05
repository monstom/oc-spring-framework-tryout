package ticket.model.bean.user;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class User {
	
	private int id_user;
	
	private String firstname;
	private String lastname;
	
	User() {}
	
	public User(int id, String fname, String lname) {
		this(fname,lname);
		try {
			this.setUserID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public User(String fname, String lname) {
		try {
			this.setUserID(0);
			this.setUser_firstname(fname);
			this.setUser_lastname(lname);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
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
		return this.getUserID() +" : "+ this.getUser_firstname() +" "+ this.getUser_lastname();
	}

}
