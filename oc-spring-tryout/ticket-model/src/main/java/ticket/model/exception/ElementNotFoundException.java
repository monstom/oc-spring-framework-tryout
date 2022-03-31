package ticket.model.exception;

import java.sql.SQLException;

public class ElementNotFoundException extends SQLException {
	
	private static final long serialVersionUID = 1L;
	
	public ElementNotFoundException() {}

	public ElementNotFoundException(String message) {
		super(message);
	}
	
	public ElementNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public ElementNotFoundException(String message, String statement, int error_code) {
		super(message,statement,error_code);
	}
}
