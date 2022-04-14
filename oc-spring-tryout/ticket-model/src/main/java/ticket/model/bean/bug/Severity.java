package ticket.model.bean.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Severity {
	
	private int id_severity;
	
	private int level;
	private String label;
	
	protected Severity() {}
	
	public Severity(int id, int slevel, String slabel) {
		try {
			this.setSeverityID(id);
			this.setSeverity_level(slevel);
			this.setSeverity_label(slabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getSeverityID() {
		return this.id_severity;
	}

	public void setSeverityID(int severity_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(severity_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a severity must not be negative or equal 0 !");
		else this.id_severity = severity_id;
	}

	public int getSeverity_level() {
		return this.level;
	}

	public void setSeverity_level(int slevel) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(slevel,0)) 
			throw new InvalidAttributeNumericValueException("The level of a severity must not be negative or equal 0 !");
		else if(GenericValidator.minValue(slevel,6)) 
			throw new InvalidAttributeNumericValueException("The level of a severity must not be greater than 5 !");
		else this.level = slevel;
	}

	public String getSeverity_label() {
		return this.label;
	}

	public void setSeverity_label(String slabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(slabel)) 
			throw new InvalidAttributeLengthException("The title of a severity must not be empty or blank !");
		else if(GenericValidator.minLength(slabel,100)) 
			throw new InvalidAttributeLengthException("The title of a severity must not contains more than 100 characters !");
		else this.label = slabel;
	}
	
	public String toString() {
		return "--- Severity Object ---\n id : "+ this.id_severity
				+"\n level : "+ this.level
				+"\n label : "+ this.label+"\n";
	}
}
