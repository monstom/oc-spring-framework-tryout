package ticket.consumer.db.impl;

import ticket.consumer.db.DatabaseConnectionTypes;
import ticket.consumer.db.contract.DatabaseConnection;
import ticket.consumer.db.contract.DatabaseConnectionFactory;

public class DatabaseConnectionFactoryImpl implements DatabaseConnectionFactory {
	
	public DatabaseConnection getJDBCConnector(DatabaseConnectionTypes type) {
		DatabaseConnection connector;
		switch(type) {
			case MYSQL : 
				connector = MySQLConnectionImpl.getInstance(
					DatabaseConnectionTypes.MYSQL.getDBTitle(),
					DatabaseConnectionTypes.MYSQL.getDBUser(),
					DatabaseConnectionTypes.MYSQL.getDBCredential()
				);
				break;
			case POSTGRESQL : 
				connector = PostGreSQLConnectionImpl.getInstance(
					DatabaseConnectionTypes.POSTGRESQL.getDBTitle(),
					DatabaseConnectionTypes.POSTGRESQL.getDBUser(),
					DatabaseConnectionTypes.POSTGRESQL.getDBCredential()
				);
				break;
			case SQLSERVER : 
				connector = SQLServerConnectionImpl.getInstance(
					DatabaseConnectionTypes.SQLSERVER.getDBTitle(),
					DatabaseConnectionTypes.SQLSERVER.getDBUser(),
					DatabaseConnectionTypes.SQLSERVER.getDBCredential()
				);
				break;
			case MONGODB : 
				connector = MongoDBConnectionImpl.getInstance(
					DatabaseConnectionTypes.MONGODB.getDBTitle(),
					DatabaseConnectionTypes.MONGODB.getDBUser(),
					DatabaseConnectionTypes.MONGODB.getDBCredential()
				);
				break;
			default : connector = null;
		}
		return connector;
	}
	
	
}
