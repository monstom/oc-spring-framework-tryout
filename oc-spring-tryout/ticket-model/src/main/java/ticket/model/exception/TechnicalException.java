package ticket.model.exception;

public class TechnicalException extends Exception {

	private static final long serialVersionUID = 1L;

	public TechnicalException() {}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

}
