package ticket.consumer.db.contract;

import ticket.consumer.db.DatabaseConnectionTypes;

public interface DatabaseConnectionFactory {
	
	public DatabaseConnection getJDBCConnector(DatabaseConnectionTypes type);

}
