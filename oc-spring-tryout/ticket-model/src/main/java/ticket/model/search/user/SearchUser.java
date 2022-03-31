package ticket.model.search.user;

public class SearchUser {
	
	private int searched_userID;
	private String searched_fullname;
	
	
	public SearchUser() {}
	
	public SearchUser(int id_user) {
		this.setSearchedUserID(id_user);
	}
	
	public SearchUser(String fname) {
		this.setSearchedFullname(fname);
	}
	
	public int getSearchedUserID() {
		return searched_userID;
	}

	public SearchUser setSearchedUserID(int searched_UID) {
		this.searched_userID = searched_UID;
		return this;
	}

	public String getSearchedFullname() {
		return searched_fullname;
	}

	public SearchUser setSearchedFullname(String searched_fname) {
		this.searched_fullname = searched_fname;
		return this;
	}
}
