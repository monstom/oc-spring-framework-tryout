package ticket.model.exception;

public class InvalidAttributeLength extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeLength() {}

	public InvalidAttributeLength(String message) {
		super(message);
	}

	public InvalidAttributeLength(String message, Throwable cause) {
		super(message, cause);
	}

}
