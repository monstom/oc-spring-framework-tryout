package ticket.model.bean.user;

public class User {
	
	private int id_user;
	
	private String firstname;
	private String lastname;
	
	User() {}
	
	public User(int id, String fname, String lname) {
		this(fname,lname);
		this.setUserID(id);
	}
	
	public User(String fname, String lname) {
		this.setUserID(0);
		this.setUser_firstname(fname);
		this.setUser_lastname(lname);
	}

	public int getUserID() {
		return this.id_user;
	}

	public void setUserID(int id_user) {
		this.id_user = id_user;
	}

	public String getUser_firstname() {
		return this.firstname;
	}

	public void setUser_firstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUser_lastname() {
		return this.lastname;
	}

	public void setUser_lastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String toString() {
		return this.getUser_firstname() + this.getUser_lastname();
	}

}
