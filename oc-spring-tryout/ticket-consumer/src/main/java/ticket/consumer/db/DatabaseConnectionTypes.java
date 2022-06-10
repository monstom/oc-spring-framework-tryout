package ticket.consumer.db;

public enum DatabaseConnectionTypes {
	
	MYSQL		("ticket_webapp","ticketDB_manager","59c54!FnK]=&[EuZq5<d"),
	SQLSERVER	("ticket_webapp","ticket_manager","!Nj]uHYTnjXS+AjBi#F$"),
	POSTGRESQL	("ticket_webapp","ticketDB_manager","I$cN}JFTy!fnNe=%He0g"),
	MONGODB 	("ticket_webapp","ticket_manager",":~6K>B[!lgMc}SDM8]iq");

	private String title;
	private String user;
	private String credential;
	
	DatabaseConnectionTypes(String dname, String uname, String cred) {
		this.title = dname;
		this.user = uname;
		this.credential = cred;
	}
	
	public String getDBTitle() { 
		return this.title;
	}
	public String getDBUser() { 
		return this.user;
	}
	public String getDBCredential() { 
		return this.credential;
	}
}