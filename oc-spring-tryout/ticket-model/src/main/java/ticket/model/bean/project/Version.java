package ticket.model.bean.project;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Version {
	
	//private static final Logger logger = LoggerFactory.getLogger(Version.class);

	private int id_project;
	private String version_label;
	
	protected Version() {}
	
	public Version(int project_id, String vlabel) 
			throws InvalidAttributeNumericValueException, InvalidAttributeLengthException {
		this.setVersion_projectID(project_id);
		this.setVersion_label(vlabel);
		//logger.info("version bean successfully created/retrieved !");
	}

	public int getVersion_projectID() {
		return this.id_project;
	}

	public void setVersion_projectID(int project_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(project_id,0)) 
			throw new InvalidAttributeNumericValueException("The identifying key the project of a version must not be negative or equal 0 !");
		else this.id_project = project_id;
	}

	public String getVersion_label() {
		return this.version_label;
	}

	public void setVersion_label(String vlabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(vlabel)) 
			throw new InvalidAttributeLengthException("The index identifying the label of a version must not be empty or blank !");
		else if(GenericValidator.minLength(vlabel,30)) 
			throw new InvalidAttributeLengthException("The index identifying the label of a version must not contains more than 100 characters !");
		else this.version_label = vlabel;
	}
	
	public String toString() {
		return "--- Version Object ---\n project_id : "+ this.id_project +"\n version_label : "+ this.version_label +"\n";
	}
	
}
