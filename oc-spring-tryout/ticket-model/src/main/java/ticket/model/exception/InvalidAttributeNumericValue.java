package ticket.model.exception;

public class InvalidAttributeNumericValue extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeNumericValue() {}

	public InvalidAttributeNumericValue(String message) {
		super(message);
	}

	public InvalidAttributeNumericValue(String message, Throwable cause) {
		super(message, cause);
	}
}
