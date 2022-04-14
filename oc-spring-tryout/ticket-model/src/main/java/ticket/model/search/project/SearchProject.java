package ticket.model.search.project;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.TimeValidator;

import ticket.model.exception.InvalidAttributeDateException;
import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchProject {
	
	private int searched_projectID;
	private String searched_title;
	private String searched_cdate;
	private boolean searched_state;
	private int searched_manager;
	
	public SearchProject() {}
	
	public SearchProject(int id) {
		try {
			this.setSearchedProjectID(id);
		} catch(InvalidAttributeNumericValueException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getSearchedProjectID() {
		return this.searched_projectID;
	}

	public SearchProject setSearchedProjectID(int searched_projectID) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_projectID,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a project must not be negative or equal 0 !");
		else 
			this.searched_projectID = searched_projectID;
		return this;
	}

	public String getSearchedTitle() {
		return searched_title;
	}

	public SearchProject setSearchedTitle(String searched_title) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(searched_title))
			throw new InvalidAttributeLengthException("The researched index identifying the title of a project must not be empty or blank !");			
		else if(GenericValidator.minLength(searched_title,100)) 
			throw new InvalidAttributeLengthException("The researched index identifying the title of a project must not contains more than 300 characters !");
		else this.searched_title = searched_title;
		return this;
	}

	public String getSearchedCreationDate() {
		return searched_cdate;
	}

	public SearchProject setSearchedCreationDate(String searched_cdate) throws InvalidAttributeDateException {
		if(!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd") &&
				!TimeValidator.getInstance().isValid(searched_cdate, "yyyy-mm-dd HH:mm:ss")) 
				throw new InvalidAttributeDateException("The researched index identifying the creation date of a project must be in the correct format !");
			else this.searched_cdate = searched_cdate;
		return this;
	}

	public boolean getSearchedState() {
		return searched_state;
	}

	public SearchProject setSearchedState(boolean searched_state) {
		this.searched_state = searched_state;
		return this;
	}

	public int getSearchedManager() {
		return searched_manager;
	}

	public SearchProject setSearchedManager(int searched_manager) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_manager,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying the manager of a project must not be negative or equal 0 !");
		else 
			this.searched_manager = searched_manager;
		return this;
	}
}


