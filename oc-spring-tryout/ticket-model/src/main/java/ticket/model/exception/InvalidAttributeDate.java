package ticket.model.exception;

public class InvalidAttributeDate extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeDate() {}

	public InvalidAttributeDate(String message) {
		super(message);
	}

	public InvalidAttributeDate(String message, Throwable cause) {
		super(message, cause);
	}
}
