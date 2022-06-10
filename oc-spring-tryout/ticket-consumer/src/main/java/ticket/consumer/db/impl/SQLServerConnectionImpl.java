package ticket.consumer.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ticket.consumer.db.contract.DatabaseConnection;


public class SQLServerConnectionImpl extends AbstractDatabaseConnectionImpl implements DatabaseConnection {
	
	
	private final String driver = "jdbc:sqlserver://localhost/";
	
	
	public SQLServerConnectionImpl(String name, String user, String password) {
		super(name,user,password);
	}
	

	public static SQLServerConnectionImpl getInstance(String dbname, String uname, String upass) {
		return new SQLServerConnectionImpl(dbname,uname,upass);
	}

	
	@Override
	public void createConnection() {
		String db_link = this.driver + getDatabase_db();
		try {
			db_con = DriverManager.getConnection(db_link, getDatabase_username(), getDatabase_password());
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	
	@Override
	public Connection getConnection() {
		this.createConnection();
		return db_con;
	}

	
	@Override
	public Connection closeConnection() {
		if(isOpen()) {
			try {
				db_con.close();
				db_con = null;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return db_con;
	}

}
