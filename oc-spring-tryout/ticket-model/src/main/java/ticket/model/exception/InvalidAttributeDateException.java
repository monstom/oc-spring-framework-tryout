package ticket.model.exception;

public class InvalidAttributeDateException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeDateException() {}

	public InvalidAttributeDateException(String message) {
		super(message);
	}

	public InvalidAttributeDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
