package ticket.model.search.ticket;

public class SearchComment {
	
	private int searched_commentID;
	private int searched_ticket;
	private int searched_author;
	
	public SearchComment() {}

	public int getSearchedCommentID() {
		return searched_commentID;
	}

	public SearchComment setSearchedCommentID(int searched_commentID) {
		this.searched_commentID = searched_commentID;
		return this;
	}

	public int getSearchedTicket() {
		return searched_ticket;
	}

	public SearchComment setSearchedTicket(int searched_ticket) {
		this.searched_ticket = searched_ticket;
		return this;
	}

	public int getSearchedAuthor() {
		return searched_author;
	}

	public SearchComment setSearchedAuthor(int searched_author) {
		this.searched_author = searched_author;
		return this;
	}
	
}
