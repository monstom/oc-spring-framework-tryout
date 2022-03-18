package ticket.model.beans.tickets;

import java.util.Date;

public class Ticket {
	
	private int id_ticket;
	
	private String title;
	private Date creation_date;
	private String description;
	private int id_statut;
	private int id_author;
	private int id_project;
	
	protected Ticket() {}
	
	public Ticket(int id, String tname, Date cdate, String desc, int statut, int author, int project) {
		this(tname,cdate,desc,statut,author,project);
		this.setTicketID(id);		
	}
	
	public Ticket(int id, String tname, Date cdate, int statut, int author, int project) {
		this(tname,cdate,"",statut,author,project);
		this.setTicketID(id);		
	}
	
	public Ticket(String tname, Date cdate, String desc, int statut, int author, int project) {
		this(tname,cdate,desc);
		this.setTicket_statutID(statut);
		this.setTicket_authorID(author);
		this.setTicket_projectID(project);			
	}
	
	public Ticket(String tname, Date cdate, String desc) {
		this.setTicketID(0);
		this.setTicket_title(tname);
		this.setTicket_creationDate(cdate);
		this.setTicket_description(desc);
		this.setTicket_statutID(0);
		this.setTicket_authorID(0);
		this.setTicket_projectID(0);
	}

	public int getTicketID() {
		return this.id_ticket;
	}

	public void setTicketID(int ticket_id) {
		this.id_ticket = ticket_id;
	}

	public String getTicket_title() {
		return this.title;
	}

	public void setTicket_title(String title) {
		this.title = title;
	}

	public Date getTicket_creationDate() {
		return this.creation_date;
	}

	public void setTicket_creationDate(Date creation_time) {
		this.creation_date = creation_time;
	}

	public String getTicket_description() {
		return this.description;
	}

	public void setTicket_description(String desc) {
		this.description = desc;
	}

	public int getTicket_statutID() {
		return this.id_statut;
	}

	public void setTicket_statutID(int statut_id) {
		this.id_statut = statut_id;
	}

	public int getTicket_authorID() {
		return this.id_author;
	}

	public void setTicket_authorID(int author_id) {
		this.id_author = author_id;
	}

	public int getTicket_projectID() {
		return this.id_project;
	}

	public void setTicket_projectID(int project_id) {
		this.id_project = project_id;
	}
	
}