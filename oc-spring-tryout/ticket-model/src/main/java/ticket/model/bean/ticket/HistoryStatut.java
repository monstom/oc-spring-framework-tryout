package ticket.model.bean.ticket;

import java.util.Date;

public class HistoryStatut {
	
	private int id_ticket;
	private int id_statut;
	
	private Date creation_date;
	private int id_user;
	private int id_comment;
	
	HistoryStatut() {}
	
	public HistoryStatut(int ticket, int statut, Date cdate, int user, int comment) {
		this(ticket,statut,cdate,user);
		this.setHistory_commentID(comment);
	}
	
	public HistoryStatut(int ticket, int statut, Date cdate, int user) {
		this(ticket,statut,cdate);
		this.setHistory_userID(user);
	}
	
	public HistoryStatut(int ticket, int statut, Date cdate) {
		this(cdate);
		this.setHistory_ticketID(ticket);
		this.setHistory_statutID(statut);
	}
	
	public HistoryStatut(Date cdate) {
		this.setHistory_ticketID(0);
		this.setHistory_statutID(0);
		this.setHistory_creationDate(cdate);
		this.setHistory_commentID(0);
		this.setHistory_userID(0);
	}
	
	public int getHistory_ticketID() {
		return this.id_ticket;
	}

	public void setHistory_ticketID(int ticket_id) {
		this.id_ticket = ticket_id;
	}

	public int getHistory_statutID() {
		return this.id_statut;
	}

	public void setHistory_statutID(int statut_id) {
		this.id_statut = statut_id;
	}

	public Date getHistory_creationDate() {
		return this.creation_date;
	}

	public void setHistory_creationDate(Date cdate) {
		this.creation_date = cdate;
	}

	public int getHistory_userID() {
		return this.id_user;
	}

	public void setHistory_userID(int user_id) {
		this.id_user = user_id;
	}

	public int getHistory_commentID() {
		return this.id_comment;
	}

	public void setHistory_commentID(int comment_id) {
		this.id_comment = comment_id;
	}
}
