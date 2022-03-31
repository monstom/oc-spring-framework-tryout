package ticket.model.search.ticket;

public class SearchStatutHistory {
	
	private int searched_ticket;
	private int searched_statut;
	private int searched_comment;
	private int searched_author;
	
	public SearchStatutHistory() {}

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchStatutHistory setSearchedTicket(int searched_ticket) {
		this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedStatut() {
		return searched_statut;
	}
	
	public SearchStatutHistory setSearchedStatut(int searched_statut) {
		this.searched_statut = searched_statut;
		return this;
	}

	public int getSearchedComment() {
		return searched_comment;
	}

	public SearchStatutHistory setSearchedComment(int searched_comment) {
		this.searched_comment = searched_comment;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchStatutHistory setSearchedAuthor(int searched_author) {
		this.searched_author = searched_author;
		return this;
	}
	
	
}
