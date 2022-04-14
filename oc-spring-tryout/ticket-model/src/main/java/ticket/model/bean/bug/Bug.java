package ticket.model.bean.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.bean.ticket.Ticket;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Bug extends Ticket {
	
	private int severity;

	protected Bug() {
		super();
	}

	public Bug(int id, String tname, String cdate, String desc, int statut, int author, int project, int level) {
		super(id, tname, cdate, desc, statut, author, project);
		try {
			this.setBug_severity(level);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getBug_severity() {
		return this.severity;
	}

	public void setBug_severity(int severity) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(severity,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the severity of a bug must not be negative or equal 0 !");
		else this.severity = severity;
	}
	
	public String toString() {
		return super.toString().replace("Ticket","Bug") +"severity_id :  "+ this.getBug_severity() +"\n";
	}
}
