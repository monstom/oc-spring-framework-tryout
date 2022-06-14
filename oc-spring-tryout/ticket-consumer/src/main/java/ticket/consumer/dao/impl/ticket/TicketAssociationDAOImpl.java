package ticket.consumer.dao.impl.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.ticket.TicketAssociationDAO;
import ticket.model.bean.ticket.TicketAssociation;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.ticket.SearchTicketAssociation;

public class TicketAssociationDAOImpl extends AbstractDAO implements TicketAssociationDAO {

	public TicketAssociationDAOImpl() {
		super();
	}

	
	@Override
	public List<TicketAssociation> getAllTicketAssos() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<TicketAssociation> ticketAssos = null;
		
		String query = "SELECT * FROM TicketAssociation";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The ticket association table in the database is empty : no rows found !");
		
		ticketAssos = new ArrayList<TicketAssociation>();
		while(!results.isAfterLast()) {
			ticketAssos.add(new TicketAssociation(results.getInt("ticket1_id"),results.getInt("ticket2_id")));
			results.next();
		}
		return ticketAssos;
	}
	
	
	@Override
	public TicketAssociation getTicketAssociationByID(SearchTicketAssociation search_ticketAsso) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		TicketAssociation ticketAssobyID = null;
		if(search_ticketAsso == null || !(search_ticketAsso.getSearchedTicket1AssoID() > 0))
			throw new SQLException("The research object and its main ticket must not be undefined");
		else if(search_ticketAsso == null || !(search_ticketAsso.getSearchedTicket2AssoID() > 0))
			throw new SQLException("The research object and its associated ticket must not be undefined");
		
		int ticket1 = search_ticketAsso.getSearchedTicket1AssoID();
		int ticket2 = search_ticketAsso.getSearchedTicket2AssoID();
		String query = "SELECT * FROM TicketAssociation WHERE ticket1_id=? AND ticket2_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, ticket1);
		statement.setInt(2, ticket2);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched ticket association doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched ticket association exists in multiples copies in the database !");
		else ticketAssobyID = new TicketAssociation(ticket1,ticket2);
		
		return ticketAssobyID;
	}
	
	
	@Override
	public List<TicketAssociation> getAssociatedTicketOf_ticketID(SearchTicketAssociation search_ticketAsso) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<TicketAssociation> ticketAssosFromFirstTicket = null;
		if(search_ticketAsso == null || !(search_ticketAsso.getSearchedTicket1AssoID() > 0))
			throw new SQLException("The research object and its main ticket must not be undefined");
		
		int ticket1 = search_ticketAsso.getSearchedTicket1AssoID();
		String query = "SELECT DISTINCT * FROM TicketAssociation WHERE ticket1_id=? or ticket2_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, ticket1);
		statement.setInt(2, ticket1);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched ticket associations from main ticket doesn't match to any element in the database !");
		
		ticketAssosFromFirstTicket = new ArrayList<TicketAssociation>();
		while(!results.isAfterLast()) {
			ticketAssosFromFirstTicket.add(new TicketAssociation(ticket1,results.getInt("ticket2_id")));
			results.next();
		}
		return ticketAssosFromFirstTicket;
	}

	
	@Override
	public int createTicketAssociation(TicketAssociation new_ticketAsso) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_ticketAsso == null || !(new_ticketAsso.getAssociation_mainTicketID() > 0) || !(new_ticketAsso.getAssociation_associatedTicketID() > 0))
			throw new SQLException("The new ticket association object to be created must be undefined !");
		
		int main = new_ticketAsso.getAssociation_mainTicketID();
		int associated = new_ticketAsso.getAssociation_associatedTicketID();
		String query = "INSERT INTO TicketAssociation VALUES (?,?)";		
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, main);
		statement.setInt(2, associated);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		result = 1;
		return result;
	}
	
	
	@Override
	public int deleteTicketAssociation(SearchTicketAssociation search_ticketAsso) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_ticketAsso == null || !(search_ticketAsso.getSearchedTicket1AssoID() > 0) || !(search_ticketAsso.getSearchedTicket2AssoID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int main = search_ticketAsso.getSearchedTicket1AssoID();
		int associated = search_ticketAsso.getSearchedTicket2AssoID();
		String query = "DELETE TicketAssociation WHERE ticket1_id=? AND ticket2_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, main);
		statement.setInt(2, associated);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = 1;
		return result;
	}

}
