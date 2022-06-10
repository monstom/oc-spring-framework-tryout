package ticket.consumer.db.contract;

import java.sql.Connection;


public interface DatabaseConnection {
	
	
	public static DatabaseConnection getInstance(String dbname, String uname, String upass) {
		return null;
	}
		
	void createConnection();
	
	public Connection getConnection();
	
	public Connection closeConnection();

}
