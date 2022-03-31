package ticket.model.search.ticket;

public class SearchTicket {

	private int searched_ticketID;
	private int searched_statut;
	private int searched_project;
	private int searched_author;
	
	public SearchTicket() {}
	
	public SearchTicket(int searched_TID) {
		this.setSearchedTicketID(searched_TID);
	}

	public int getSearchedTicketID() {
		return searched_ticketID;
	}

	public SearchTicket setSearchedTicketID(int searched_ticketID) {
		this.searched_ticketID = searched_ticketID;
		return this;
	}

	public int getSearchedStatut() {
		return searched_statut;
	}

	public SearchTicket setSearchedStatut(int searched_statut) {
		this.searched_statut = searched_statut;
		return this;
	}

	public int getSearchedProject() {
		return searched_project;
	}

	public SearchTicket setSearchedProject(int searched_project) {
		this.searched_project = searched_project;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchTicket setSearchedAuthor(int searched_author) {
		this.searched_author = searched_author;
		return this;
	}
	
}
