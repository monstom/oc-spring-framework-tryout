package ticket.model.beans.user;

public class User {
	
	private int id_user;
	
	private String firstname;
	private String lastname;
	
	public User() {}
	
	
	public User(String fname, String lname) {
		this.setFirstname(fname);
		this.setLastname(lname);
	}

	public int getID_user() {
		return id_user;
	}

	public void setID_user(int id_user) {
		this.id_user = id_user;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String toString() {
		return this.getFirstname() + this.getLastname();
	}

}
