package ticket.model.search.project;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeNumericValueException;

public class SearchProject {
	
	private int searched_projectID;
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

	public int getSearchedManager() {
		return searched_manager;
	}

	public SearchProject setSearchedManager(int searched_manager) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(searched_manager,0)) 
			throw new InvalidAttributeNumericValueException("The researched key identifying a project's manager must not be negative or equal 0 !");
		else 
			this.searched_manager = searched_manager;
		return this;
	}
}


