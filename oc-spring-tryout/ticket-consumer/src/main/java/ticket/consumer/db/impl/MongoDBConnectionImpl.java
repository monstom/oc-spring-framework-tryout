package ticket.consumer.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ticket.consumer.db.contract.DatabaseConnection;

public class MongoDBConnectionImpl extends AbstractDatabaseConnectionImpl implements DatabaseConnection {
	
	
	private final String driver = "jdbc:mongodb://localhost/";


	public MongoDBConnectionImpl(String name, String user, String password) {
		super(name, user, password);
	}
	
	
	public static MongoDBConnectionImpl getInstance(String dbname, String uname, String upass) {
		return new MongoDBConnectionImpl(dbname,uname,upass);
	}

	
	@Override
	public void createConnection() {
		String db_link = this.driver + getDatabase_db();
		try {
			Class.forName("com.mongodb.internal.build.MongoDriverVersion");
			db_con = DriverManager.getConnection(db_link, getDatabase_username(), getDatabase_password());
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
