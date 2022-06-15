package ticket.consumer.dao.impl.evolution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.evolution.EvolutionDAO;
import ticket.model.bean.evolution.Evolution;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.evolution.SearchEvolution;

public class EvolutionDAOImpl extends AbstractDAO implements EvolutionDAO {

	public EvolutionDAOImpl() {
		super();
	}

	
	@Override
	public List<Evolution> getAllEvolutions() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Evolution> evolutions = null;
		String query = "SELECT * FROM Evolution";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The evolution table in the database is empty : no rows found !");
		
		evolutions = new ArrayList<Evolution>();
		while(!results.isAfterLast()) {
			evolutions.add(new Evolution(results.getInt("ticket_id"),results.getInt("priority")));
			results.next();
		}
		return evolutions;
	}
	

	@Override
	public Evolution getEvolutionByID(SearchEvolution search_evolution) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Evolution evolution = null;
		if(search_evolution == null || !(search_evolution.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its id must not be undefined");
		
		int eid = search_evolution.getSearchedTicketID();
		String query = "SELECT * FROM Evolution WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, eid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched evolution doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched evolution exists in multiples copies in the database !");
		else evolution = new Evolution(results.getInt("ticket_id"),results.getInt("priority"));
		return evolution;
	}

	
	@Override
	public List<Evolution> getEvolutionsFrom_priority(SearchEvolution search_evolution) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Evolution> evolutions = null;
		if(search_evolution == null || !(search_evolution.getSearchedPriority() > 0))
			throw new SQLException("The research object or its priority must not be undefined");
		
		int epriority = search_evolution.getSearchedPriority();
		String query = "SELECT * FROM Evolution WHERE priority=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, epriority);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched evolutions from priority doesn't match to any element in the database !");
		
		evolutions = new ArrayList<Evolution>();
		while(!results.isAfterLast()) {
			evolutions.add(new Evolution(results.getInt("ticket_id"),results.getInt("priority")));
			results.next();
		}
		return evolutions;
	}

	
	@Override
	public int createEvolution(Evolution new_evolution) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_evolution == null) 
			throw new SQLException("The new evolution object to be created must be undefined !");
		
		int priority = new_evolution.getEvolution_priority();
		int ticket_id = new_evolution.getEvolution_ticketID();;		
		String query = "INSERT INTO Evolution VALUES (?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		statement.setInt(1, ticket_id);
		statement.setInt(2, priority);
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}
	
	
	@Override
	public int updateEvolution(SearchEvolution search_evolution) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_evolution == null || !(search_evolution.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its ticket id must not be undefined");
		
		int eticket = search_evolution.getSearchedTicketID();
		int epriority = -1;
		if(search_evolution.getSearchedPriority() > 0) 
			epriority = search_evolution.getSearchedPriority();
		else
			throw new SQLException("The fields of the evolution to be updated must not be all undefined");
		
		String query = "UPDATE Evolution SET severity_id=? WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, epriority);
		statement.setInt(2, eticket);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = eticket;
		return result;
	}

	
	@Override
	public int deleteEvolution(SearchEvolution search_evolution) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_evolution == null || !(search_evolution.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int eticket = search_evolution.getSearchedTicketID();
		String query = "DELETE FROM Evolution WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, eticket);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = eticket;
		else 
			throw new SQLException("The researched evolution could not be deleted as it doesn't exist in the database !");
		return result;
	}

}
