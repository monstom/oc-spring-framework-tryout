package ticket.model.search.evolution;

import ticket.model.search.ticket.SearchTicket;

public class SearchEvolution extends SearchTicket {

	private int searched_priority;
	
	public SearchEvolution() {}

	public int getSearchedPriority() {
		return searched_priority;
	}

	public SearchEvolution setSearchedPriority(int searched_priority) {
		this.searched_priority = searched_priority;
		return this;
	}

}
