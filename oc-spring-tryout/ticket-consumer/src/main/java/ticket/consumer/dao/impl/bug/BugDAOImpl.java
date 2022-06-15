package ticket.consumer.dao.impl.bug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.bug.BugDAO;
import ticket.model.bean.bug.Bug;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.bug.SearchBug;

public class BugDAOImpl extends AbstractDAO implements BugDAO {

	public BugDAOImpl() {
		super();
	}	
	

	@Override
	public List<Bug> getAllBugs() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Bug> bugs = null;
		
		String query = "SELECT * FROM Bug INNER JOIN Ticket ON ticket_id=id";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The bug table in the database is empty : no rows found !");
		
		bugs = new ArrayList<Bug>();
		while(!results.isAfterLast()) {
			bugs.add(new Bug(results.getInt("ticket_id"),results.getInt("severity_id")));
			results.next();
		}
		return bugs;
	}

	
	@Override
	public Bug getBugByID(SearchBug search_bug) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Bug bug = null;
		if(search_bug == null || !(search_bug.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its id must not be undefined");
		
		int bid = search_bug.getSearchedTicketID();
		String query = "SELECT * FROM Bug WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched bug doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched bug exists in multiples copies in the database !");
		else bug = new Bug(results.getInt("ticket_id"),results.getInt("severity_id"));
		return bug;
	}
	

	@Override
	public List<Bug> getBugsFrom_severity(SearchBug search_bug) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Bug> bugs = null;
		if(search_bug == null || !(search_bug.getSearchedSeverity() > 0))
			throw new SQLException("The research object or its severity must not be undefined");
		
		int bseverity = search_bug.getSearchedSeverity();
		String query = "SELECT * FROM Bug WHERE severity_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bseverity);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched bug from severity doesn't match to any element in the database !");
		
		bugs = new ArrayList<Bug>();
		while(!results.isAfterLast()) {
			bugs.add(new Bug(results.getInt("ticket_id"),results.getInt("severity_id")));
			results.next();
		}
		return bugs;
	}
	
	
	@Override
	public int createBug(Bug new_bug) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_bug == null) 
			throw new SQLException("The new bug object to be created must be undefined !");
		
		int severity = new_bug.getBug_severity();
		int ticket_id = new_bug.getBug_ticketID();;		
		String query = "INSERT INTO Bug VALUES (?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		statement.setInt(1, ticket_id);
		statement.setInt(2, severity);
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}
	

	@Override
	public int updateBug(SearchBug search_bug) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_bug == null || !(search_bug.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int bticket = search_bug.getSearchedTicketID();
		int bseverity = -1; 
		if(search_bug.getSearchedSeverity() > 0) 
			bseverity = search_bug.getSearchedSeverity();
		else
			throw new SQLException("The fields of the bug to be updated must not be all undefined");
		
		String query = "UPDATE Bug SET severity_id=? WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bseverity);
		statement.setInt(2, bticket);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = bticket;
		return result;
	}

	
	@Override
	public int deleteBug(SearchBug search_bug) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_bug == null || !(search_bug.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int bticket = search_bug.getSearchedTicketID();
		String query = "DELETE FROM Bug WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bticket);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = bticket;
		else 
			throw new SQLException("The researched bug could not be deleted as it doesn't exist in the database !");
		return result;
	}

}
