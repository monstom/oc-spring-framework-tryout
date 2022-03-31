package ticket.model.search.bug;

import ticket.model.search.ticket.SearchTicket;

public class SearchBug extends SearchTicket {
	
	private int searched_severity;

	public SearchBug() {}

	public int getSearchedSeverity() {
		return searched_severity;
	}

	public SearchBug setSearchedSeverity(int searched_severity) {
		this.searched_severity = searched_severity;
		return this;
	}

}
