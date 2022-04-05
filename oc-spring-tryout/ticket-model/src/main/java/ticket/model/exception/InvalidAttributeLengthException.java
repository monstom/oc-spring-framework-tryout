package ticket.model.exception;

public class InvalidAttributeLengthException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeLengthException() {}

	public InvalidAttributeLengthException(String message) {
		super(message);
	}

	public InvalidAttributeLengthException(String message, Throwable cause) {
		super(message, cause);
	}

}
