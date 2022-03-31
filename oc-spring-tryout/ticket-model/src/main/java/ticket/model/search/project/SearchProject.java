package ticket.model.search.project;

public class SearchProject {
	
	private int searched_projectID;
	private int searched_manager;
	
	public SearchProject() {}
	
	public SearchProject(int id) {
		this.setSearchedProjectID(id);
	}

	public int getSearchedProjectID() {
		return this.searched_projectID;
	}

	public SearchProject setSearchedProjectID(int searched_PID) {
		this.searched_projectID = searched_PID;
		return this;
	}

	public int getSearchedManager() {
		return searched_manager;
	}

	public SearchProject setSearchedManager(int searched_managerID) {
		this.searched_manager = searched_managerID;
		return this;
	}
}


