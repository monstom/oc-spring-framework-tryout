package ticket.consumer.dao.impl.bug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.bug.SeverityDAO;
import ticket.model.bean.bug.Severity;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.bug.SearchSeverity;

public class SeverityDAOImpl extends AbstractDAO implements SeverityDAO {

	public SeverityDAOImpl() {
		super();
	}	

	
	@Override
	public List<Severity> getAllSeverities() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Severity> severities = null;
		
		String query = "SELECT * FROM Severity";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The severity table in the database is empty : no rows found !");
		
		severities = new ArrayList<Severity>();
		while(!results.isAfterLast()) {
			severities.add(new Severity(results.getInt("id"),results.getInt("level"),results.getString("label")));
			results.next();
		}
		return severities;
	}
	

	@Override
	public Severity getSeverityByID(SearchSeverity search_severity) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Severity severitybyID = null;
		if(search_severity == null || !(search_severity.getSearchedSeverityID() > 0))
			throw new SQLException("The research object or its id must not be undefined");
		
		int sid = search_severity.getSearchedSeverityID();
		String query = "SELECT * FROM Severity WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, sid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched severity doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched severity exists in multiples copies in the database !");
		else severitybyID = new Severity(results.getInt("id"),results.getInt("level"),results.getString("label"));
		
		return severitybyID;
	}
	

	@Override
	public List<Severity> getSeveritiesFrom_level(SearchSeverity search_severity) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Severity> severities = null;
		if(search_severity == null || !(search_severity.getSearchedLevel() > 0))
			throw new SQLException("The research object or its level must not be undefined");
		
		int slevel = search_severity.getSearchedLevel();
		String query = "SELECT * FROM Severity WHERE level=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, slevel);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched severities from level doesn't match to any element in the database !");
		
		severities = new ArrayList<Severity>();
		while(!results.isAfterLast()) {
			severities.add(new Severity(results.getInt("id"),results.getInt("level"),results.getString("label")));
			results.next();
		}
		return severities;
	}
	

	@Override
	public List<Severity> getSeveritiesLike_label(SearchSeverity search_severity) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Severity> severities = null;
		if(search_severity == null || search_severity.getSearchedLabel() == null)
			throw new SQLException("The research object or its label must not be undefined");
		
		String slabel = search_severity.getSearchedLabel();
		String query = "SELECT * FROM Severity WHERE label LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%"+slabel+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched severities from label doesn't match to any element in the database !");
		
		severities = new ArrayList<Severity>();
		while(!results.isAfterLast()) {
			severities.add(new Severity(results.getInt("id"),results.getInt("level"),results.getString("label")));
			results.next();
		}
		return severities;
	}

	
	@Override
	public List<Severity> getSeverities_builder(SearchSeverity search_severity) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_severity == null)
			throw new SQLException("The research object of the severity query builder must not be undefined");
		
		List<Severity> severities = null;
		int level = 0;
		String label = null;
		int nbParam = 0;
		String query = "SELECT * FROM Severity WHERE ";
		
		if(search_severity.getSearchedLevel() > 0) {
			level = search_severity.getSearchedLevel();
			query += "level=?";
			nbParam++;
		}
		
		if(search_severity.getSearchedLabel() != null) {
			label = search_severity.getSearchedLabel();
			if(nbParam > 0) query += " AND ";
			query += "label LIKE ?";
			nbParam++;
		}
		
		if(nbParam == 0) 
			query = query.replace(" WHERE ", "");
		System.out.println(query);
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(nbParam == 1) 
			if(level > 0)
				statement.setInt(1, level);
			else 
				statement.setString(1, "%"+label+"%");
		
		if(nbParam == 2) {
			statement.setInt(1, level);
			statement.setString(2, "%"+label+"%");
		}
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched severities from query builder doesn't match to any element in the database !");
		
		severities = new ArrayList<Severity>();
		while(!results.isAfterLast()) {
			severities.add(new Severity(results.getInt("id"),results.getInt("level"),results.getString("label")));
			results.next();
		}
		
		return severities;
	}
	

	@Override
	public int createSeverity(Severity new_severity) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_severity == null) 
			throw new SQLException("The new severity object to be created must be undefined !");
		
		int level = new_severity.getSeverity_level();
		String label = new_severity.getSeverity_label();
		int sid = 0;		
		if(new_severity.getSeverityID() > 0) sid = new_severity.getSeverityID();
		
		String query = "INSERT INTO Severity";
		if(sid > 0) query += " VALUES (?,?,?)";
		else query += "(level,label) VALUES (?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(sid > 0) {
			statement.setInt(1, sid);
			statement.setInt(2, level);
			statement.setString(3, label);
		} else {
			statement.setInt(1, level);
			statement.setString(2, label);
		}
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		if(sid > 0) result = sid;
		else result = this.getLastInsertID();
		return result;
	}
	
	
	@Override
	public int updateSeverity(SearchSeverity search_severity) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_severity == null || !(search_severity.getSearchedSeverityID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int sid = search_severity.getSearchedSeverityID();
		int slevel = 0;
		String slabel = null;
		int nbParam = 0;
		String query = "UPDATE Severity SET ";
		
		if(search_severity.getSearchedLevel() > 0) {
			slevel = search_severity.getSearchedLevel();
			query += "level=?" ;
			nbParam++;
		}
		
		if(search_severity.getSearchedLabel() != null) {
			slabel = search_severity.getSearchedLabel();
			if(nbParam > 0) query += " AND ";
			query += "label=?";
			nbParam++;
		}
		
		if(nbParam == 0)
			throw new SQLException("The fields of the severity to be updated must not be all undefined");
			
		query += " WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(nbParam == 2) {
			statement.setInt(1, slevel);
			statement.setString(2, slabel);
			statement.setInt(3, sid);
		}
		
		if(nbParam == 1)
			if(query.contains("level")) 
				statement.setInt(1, slevel);
			else
				statement.setString(1, slabel);
			statement.setInt(2, sid);
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = sid;
		return result;
	}
	

	@Override
	public int deleteSeverity(SearchSeverity search_severity) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_severity == null || !(search_severity.getSearchedSeverityID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int sid = search_severity.getSearchedSeverityID();
		String query = "DELETE Severity WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, sid);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = sid;
		return result;
	}

	
	@Override
	public int getLastInsertID() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		int lastid = -1;
		String query = "SELECT id FROM Severity ORDER BY id DESC LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		lastid = results.getInt("id");
		return lastid;
	}

}
