package ticket.model.bean.evolution;

import org.apache.commons.validator.GenericValidator;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Evolution {
	
	//private static final Logger logger = LoggerFactory.getLogger(Evolution.class);
	
	private int ticket_id;
	private int priority;
	
	protected Evolution() {}
	
	public Evolution(int ticket, int epriority) {
		try {
			this.setEvolution_ticketID(ticket);
			this.setEvolution_priority(epriority);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("evolution bean successfully created/retrieved with id : "+ticket+" !");
	}
	
	public Evolution(int ticket) {
		try {
			this.setEvolution_ticketID(ticket);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("evolution bean successfully created/retrieved by its id : "+ticket+" !");
	}

	public int getEvolution_ticketID() {
		return this.ticket_id;
	}

	public void setEvolution_ticketID(int ticket) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(ticket,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the ticket of an evolution must not be negative or equal 0 !");
		else this.ticket_id = ticket;
	}

	public int getEvolution_priority() {
		return this.priority;
	}

	public void setEvolution_priority(int epriority) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(epriority,0)) 
			throw new InvalidAttributeNumericValueException("The priority of an evolution must not be negative or equal 0 !");
		else if(GenericValidator.minValue(epriority,11)) 
			throw new InvalidAttributeNumericValueException("The priority of an evolution must not be greater than 10 !");
		else this.priority = epriority;
	}
	
	public String toString() {
		return super.toString().replace("Ticket","Evolution") +"priority : "+ this.getEvolution_priority() +"\n";
	}
}
