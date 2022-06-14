package ticket.consumer.dao;

import java.sql.Connection;

public abstract class AbstractDAO {
	
	private static Connection db_con;
	
	protected AbstractDAO() {}
	
	public static void setConnection(Connection db) {
		db_con = db;
	}
	
	public Connection getConnection() {
		return db_con; 
	}	
}
