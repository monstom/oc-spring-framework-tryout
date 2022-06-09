package ticket.model.bean.bug;

import org.apache.commons.validator.GenericValidator;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Bug {
	
	//private static final Logger logger = LoggerFactory.getLogger(Bug.class);
	
	private int ticket_id;
	private int severity_id;

	protected Bug() {}
	
	public Bug(int ticket, int severity) {
		try {
			this.setBug_ticketID(ticket);
			this.setBug_severity(severity);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("bug bean successfully created/retrieved with id : "+ticket+" !");
	}
	
	public Bug(int ticket) {
		try {
			this.setBug_ticketID(ticket);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("bug bean successfully created/retrieved by its id : "+ticket+" !");
	}

	public int getBug_ticketID() {
		return this.ticket_id;
	}

	public void setBug_ticketID(int ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the ticket of a bug must not be negative or equal 0 !");
		else this.ticket_id = ticket;
	}

	public int getBug_severity() {
		return this.severity_id;
	}

	public void setBug_severity(int severity) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(severity,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the severity of a bug must not be negative or equal 0 !");
		else this.severity_id = severity;
	}
	
	public String toString() {
		return super.toString().replace("Ticket","Bug") +"severity_id :  "+ this.getBug_severity() +"\n";
	}
}
