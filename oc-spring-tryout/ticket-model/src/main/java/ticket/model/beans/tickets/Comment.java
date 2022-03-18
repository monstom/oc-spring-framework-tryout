package ticket.model.beans.tickets;

public class Comment {

	private int id_comment;
	
	private String description;
	private int id_user;
	private int id_ticket;
	
	Comment() {}
	
	public Comment(int id, String desc, int user, int ticket) {
		this(desc,user,ticket);
		this.setCommentID(id);
	}
	
	public Comment(String desc, int user, int ticket) {
		this(desc);
		this.setComment_userID(user);
		this.setComment_ticketID(ticket);
	}
	
	public Comment(int id, String desc) {
		this(desc);
		this.setCommentID(id);
	}
	
	public Comment(String desc) {
		this.setCommentID(0);
		this.setComment_description(desc);
		this.setComment_userID(0);
		this.setComment_ticketID(0);
	}
	
	public int getCommentID() {
		return this.id_comment;
	}
	
	public void setCommentID(int comment_id) {
		this.id_comment = comment_id;
	}
	
	public String getComment_description() {
		return this.description;
	}
	
	public void setComment_description(String desc) {
		this.description = desc;
	}

	public int getComment_userID() {
		return this.id_user;
	}

	public void setComment_userID(int user_id) {
		this.id_user = user_id;
	}

	public int getComment_ticketID() {
		return this.id_ticket;
	}

	public void setComment_ticketID(int ticket_id) {
		this.id_ticket = ticket_id;
	}	
}
