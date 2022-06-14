package ticket.consumer.dao.impl.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.ticket.StatutDAO;
import ticket.model.bean.ticket.Statut;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.ticket.SearchStatut;

public class StatutDAOImpl extends AbstractDAO implements StatutDAO {

	public StatutDAOImpl() {
		super();
	}
	
	
	@Override
	public List<Statut> getAllStatuts() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Statut> statuts = null;
		
		String query = "SELECT * FROM Statut";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The status table in the database is empty : no rows found !");
		
		statuts = new ArrayList<Statut>();
		while(!results.isAfterLast()) {
			statuts.add(new Statut(results.getInt("id"),results.getString("label")));
			results.next();
		}
		return statuts;
	}
	

	@Override
	public Statut getStatutByID(SearchStatut searched_statut) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Statut statutbyID = null;
		if(!(searched_statut.getSearchedStatutID() > 0) || searched_statut == null)
			throw new SQLException("The research object or its id must not be undefined");
		
		int searched_id = searched_statut.getSearchedStatutID();
		String query = "SELECT * FROM Statut WHERE id=? ";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setLong(1, searched_id);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched status doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched status exists in multiples copies in the database !");
		else statutbyID = new Statut(searched_id,results.getString("label"));
		
		return statutbyID;
	}

	
	@Override
	public List<Statut> getStatutsLike_label(SearchStatut searched_statut) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Statut> statuts = null;
		if(searched_statut == null || searched_statut.getSearchedLabel() == null)
			throw new SQLException("The research object or its label must not be undefined");
		
		String slabel = searched_statut.getSearchedLabel();
		String query = "SELECT * FROM Statut WHERE label LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%"+slabel+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched status from label doesn't match to any element in the database !");
		
		statuts = new ArrayList<Statut>();
		while(!results.isAfterLast()) {
			statuts.add(new Statut(results.getInt("id"),results.getString("label")));
			results.next();
		}
		return statuts;
	}
	

	@Override
	public int createStatut(Statut new_statut) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_statut == null) 
			throw new SQLException("The new status object to be created must be undefined !");
		
		String label = new_statut.getStatut_label();
		int sid = 0;		
		if(new_statut.getStatutID() > 0) sid = new_statut.getStatutID();
		
		String query = "INSERT INTO Statut";
		if(sid > 0) query += " VALUES (?,?)";
		else query += "(label) VALUES (?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(sid > 0) {
			statement.setInt(1, sid);
			statement.setString(2, label);
		} else statement.setString(1, label);
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		if(sid > 0) result = sid;
		else result = this.getLastInsertID();
		return result;
	}
	
	
	@Override
	public int updateStatut(SearchStatut search_statut) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_statut == null || !(search_statut.getSearchedStatutID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
				
		int sid = search_statut.getSearchedStatutID();
		String slabel = null;
		if(search_statut.getSearchedLabel() != null) 
			slabel = search_statut.getSearchedLabel();
		else 
			throw new SQLException("The fields of the status to be updated must not be all undefined");
			
		String query = "UPDATE Statut SET label=? WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, slabel);
		statement.setInt(2, sid);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = sid;
		return result;
	}

	
	@Override
	public int deleteStatut(SearchStatut search_statut) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_statut == null || !(search_statut.getSearchedStatutID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int sid = search_statut.getSearchedStatutID();
		String query = "DELETE Statut WHERE id=?";
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
		String query = "SELECT id FROM Statut ORDER BY DESC id LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		lastid = results.getInt("id");
		return lastid;
	}

}
