package ticket.model.bean.project;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Version {

	private int id_project;
	private String version_label;
	
	Version() {}
	
	public Version(int project_id, String vlabel) {
		this(vlabel);
		try {
			this.setVersion_projectID(project_id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Version(String vlabel) {
		try {
			this.setVersion_projectID(0);
			this.setVersion_label(vlabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getVersion_projectID() {
		return this.id_project;
	}

	public void setVersion_projectID(int project_id) throws InvalidAttributeNumericValueException {
		if(!GenericValidator.maxValue(project_id,0)) 
			throw new InvalidAttributeNumericValueException("The identifying key of a version must not be negative or equal 0 !");
		else this.id_project = project_id;
	}

	public String getVersion_label() {
		return this.version_label;
	}

	public void setVersion_label(String vlabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(vlabel)) 
			throw new InvalidAttributeLengthException("The label of a version must not be empty or blank !");
		else if(GenericValidator.minLength(vlabel,30)) 
			throw new InvalidAttributeLengthException("The label of a version must not contains more than 100 characters !");
		else this.version_label = vlabel;
	}
	
}
