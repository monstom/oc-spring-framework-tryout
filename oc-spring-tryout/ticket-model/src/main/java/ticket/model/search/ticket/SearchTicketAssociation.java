package ticket.model.search.ticket;

public class SearchTicketAssociation {
	
	private int searched_ticket1ID;
	private int searched_ticket2ID;
	
	public SearchTicketAssociation() {}
	
	public int getSearchedTicket1AssoID() {
		return searched_ticket1ID;
	}
	public SearchTicketAssociation setSearchedTicket1AssoID(int searched_ticket1ID) {
		this.searched_ticket1ID = searched_ticket1ID;
		return this;
	}

	public int getSearchedTicket2AssoID() {
		return searched_ticket2ID;
	}

	public SearchTicketAssociation setSearchedTicket2AssoID(int searched_ticket2ID) {
		this.searched_ticket2ID = searched_ticket2ID;
		return this;
	}
}
