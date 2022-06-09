package ticket.model.bean.bug;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class BugVersionAssociation {

	//private static final Logger logger = LoggerFactory.getLogger(BugVersionAssociation.class);
	
	private int id_bug;
	private int id_version;
	private String versionLabel;
	
	protected BugVersionAssociation() {}
	
	public BugVersionAssociation(int bugID, int versionID, String vlabel) {
		try {
			this.setBugVersionAssociation_bugID(bugID);
			this.setBugVersionAssociation_versionID(versionID);
			this.setBugVersionAssociation_versionLabel(vlabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("bug-version association bean successfully created/retrieved !");
	}

	public int getBugVersionAssociation_bugID() {
		return this.id_bug;
	}

	public void setBugVersionAssociation_bugID(int bugID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(bugID,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the bug of the association must not be negative or equal 0 !");
		else this.id_bug = bugID;
	}

	public int getBugVersionAssociation_versionID() {
		return this.id_version;
	}

	public void setBugVersionAssociation_versionID(int versionID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(versionID,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the project's id of the association must not be negative or equal 0 !");
		else this.id_version = versionID;
	}	
	
	public String getBugVersionAssociation_versionLabel() {
		return this.versionLabel;
	}

	public void setBugVersionAssociation_versionLabel(String vlabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(vlabel)) 
			throw new InvalidAttributeLengthException("The index identifying the version's label of the association must not be empty or blank !");
		else if(GenericValidator.minLength(vlabel,30)) 
			throw new InvalidAttributeLengthException("The index identifying the version's label of the association must not contains more than 100 characters !");
		else this.versionLabel = vlabel;
	}
	
	public String toString() {
		return "--- Bug-Version Association Object ---\n bug_id : "+ this.id_bug
				+"\n project_id : "+ this.id_version
				+"\n version_label : "+ this.versionLabel+"\n";
	}
}
