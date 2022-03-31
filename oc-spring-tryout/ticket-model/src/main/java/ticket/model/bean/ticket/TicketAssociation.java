package ticket.model.bean.ticket;

public class TicketAssociation {
	
	private int id_ticket;
	private int id_associatedTicket;
	
	TicketAssociation() {}
	
	TicketAssociation(int first_ticket, int second_ticket) {
		this.setAssociation_mainTicketID(first_ticket);
		this.setAssociation_associatedTicketID(second_ticket);
	}

	public int getAssociation_mainTicketID() {
		return this.id_ticket;
	}

	public void setAssociation_mainTicketID(int main_ticket) {
		this.id_ticket = main_ticket;
	}

	public int getAssociation_associatedTicketID() {
		return id_associatedTicket;
	}

	public void setAssociation_associatedTicketID(int id_associatedTicket) {
		this.id_associatedTicket = id_associatedTicket;
	}
}
