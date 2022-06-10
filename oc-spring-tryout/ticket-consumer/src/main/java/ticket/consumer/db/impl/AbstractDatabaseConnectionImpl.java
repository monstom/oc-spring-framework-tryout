package ticket.consumer.db.impl;

import java.sql.Connection;

public abstract class AbstractDatabaseConnectionImpl {	
	
	protected Connection db_con;
	private String db_name;
	private String db_username;
	private String db_password;
	
	
	protected AbstractDatabaseConnectionImpl() {
		db_con = null;
		db_name = null;
		db_username = null;
		db_password = null;
	}
	
	
	public AbstractDatabaseConnectionImpl(String name, String user, String password) {
		db_con = null;
		setDatabase_db(name);
		setDatabase_username(user);
		setDatabase_password(password);
	}
	
	
	public boolean isOpen() {
		return db_con != null ? true : false;
	}	
	
	
	protected String getDatabase_db() {
		return db_name;
	}


	protected void setDatabase_db(String dbname) {
		db_name = dbname;
	}


	protected String getDatabase_username() {
		return db_username;
	}


	protected void setDatabase_username(String username) {
		db_username = username;
	}


	protected String getDatabase_password() {
		return db_password;
	}


	protected void setDatabase_password(String password) {
		db_password = password;
	}
}
