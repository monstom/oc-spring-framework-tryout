package ticket.model.search.bug;

public class SearchSeverity {

	private int searched_severityID;
	private int searched_level;
	
	public SearchSeverity() {}

	public int getSearchedSeverityID() {
		return searched_severityID;
	}

	public SearchSeverity setSearchedSeverityID(int searched_SID) {
		this.searched_severityID = searched_SID;
		return this;
	}

	public int getSearchedLevel() {
		return searched_level;
	}

	public SearchSeverity setSearchedLevel(int searched_level) {
		this.searched_level = searched_level;
		return this;
	}

}
