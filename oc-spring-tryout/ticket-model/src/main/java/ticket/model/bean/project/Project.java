package ticket.model.bean.project;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Project {
	
	private int id_project;
	
	private String title;
	private Date date_creation;
	private boolean ongoing; 
	private int id_manager;
	
	protected Project() {}
	
	public Project(int id, String pname, String cdate, boolean done, int managerID) {
		try {
			this.setProjectID(id);
			this.setProject_title(pname);
			this.setProject_creationDate(cdate);
			this.setProject_isClosed(done);
			this.setProject_managerID(managerID); 
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getProjectID() {
		return this.id_project;
	}
	
	public void setProjectID(int project_ID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(project_ID,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a project must not be negative or equal 0 !");
		else this.id_project = project_ID;
	}
	
	public String getProject_title() {
		return this.title;
	}
	
	public void setProject_title(String project_name) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(project_name)) 
			throw new InvalidAttributeLengthException("The title of a project must not be empty or blank !");
		else if(GenericValidator.minLength(project_name,100)) 
			throw new InvalidAttributeLengthException("The title of a project must not contains more than 100 characters !");
		else this.title = project_name;
	}
	
	public Date getProject_creationDate() {
		return this.date_creation;
	}
	
	public void setProject_creationDate(String creation_time) throws InvalidAttributeDateException {		
		if(!TimeValidator.getInstance().isValid(creation_time, "yyyy-mm-dd HH:mm:ss")) 
			throw new InvalidAttributeDateException("The creation date of a project must be in the correct format !");
		else this.date_creation = Timestamp.valueOf(creation_time);
	}
	
	public boolean getProject_isClosed() {
		return this.ongoing;
	}
	
	public void setProject_isClosed(boolean ongoing) {
		this.ongoing = ongoing;
	}

	public int getProject_managerID() {
		return this.id_manager;
	}

	public void setProject_managerID(int manager_ID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(manager_ID,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying the manager of a project must not be negative or equal 0 !");
		else this.id_manager = manager_ID;
	}
	
	public String toString() {
		return "--- Project Object ---\n id : "+ this.id_project
				+"\n title : "+ this.title
				+"\n creation_date : "+ this.date_creation.toString()
				+"\n closed : " + this.ongoing
				+"\n manager_id : "+ this.id_manager+"\n"; 
	}
	
}
	
