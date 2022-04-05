package ticket.model.bean.evolution;

import org.apache.commons.validator.GenericValidator;

import ticket.model.bean.ticket.Ticket;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Evolution extends Ticket {
	
	private int priority;
	
	protected Evolution() {
		super();
	}

	public Evolution(int id, String tname, String cdate, String desc, int statut, int author, int project, int priority) {
		super(id, tname, cdate, desc, statut, author, project);
		try {
			this.setEvolution_priority(priority);
		} catch(InvalidAttributeNumericValueException e) {
			System.out.println(e.getMessage());
		}
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
}
