package ticket.model.exception;

public class InvalidAttributeNumericValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeNumericValueException() {}

	public InvalidAttributeNumericValueException(String message) {
		super(message);
	}

	public InvalidAttributeNumericValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
