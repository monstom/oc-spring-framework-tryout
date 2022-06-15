package ticket.consumer.dao.impl.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.ticket.HistoryStatutDAO;
import ticket.model.bean.ticket.HistoryStatut;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.ticket.SearchHistoryStatut;

public class HistoryStatutDAOImpl extends AbstractDAO implements HistoryStatutDAO {

	public HistoryStatutDAOImpl() {
		super();
	}
	
	
	@Override
	public List<HistoryStatut> getAllStatutsInHistory() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<HistoryStatut> historyStatuts = null;
		
		String query = "SELECT * FROM HistoryStatut";		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The history of status table in the database is empty : no rows found !");
		
		historyStatuts = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			historyStatuts.add(new HistoryStatut(results.getInt("ticket_id"),
									 			 results.getInt("statut_id"),
									 			 results.getString("creationDate"),
									 			 results.getInt("employee_id"),
									 			 results.getInt("comment_id"))
			);
			results.next();
		}
		return historyStatuts;
	}
	

	@Override
	public HistoryStatut getStatutInHistoryByID(SearchHistoryStatut search_history) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		HistoryStatut historybyID = null;
		if(search_history == null || !(search_history.getSearchedTicket() > 0))
			throw new SQLException("The research object and its ticket must not be undefined");
		else if(search_history == null || !(search_history.getSearchedStatut() > 0))
			throw new SQLException("The research object and its status must not be undefined");
		
		int hticket = search_history.getSearchedTicket();
		int hstatut = search_history.getSearchedStatut();
		String query = "SELECT * FROM HistoryStatut WHERE ticket_id=? AND statut_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, hticket);
		statement.setInt(2, hstatut);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched history of status doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched history of status exists in multiples copies in the database !");
		else historybyID = new HistoryStatut(hticket,
											 hstatut,
											 results.getString("creationDate"),
											 results.getInt("employee_id"),
											 results.getInt("comment_id"));
		
		return historybyID;
	}

	
	@Override
	public List<HistoryStatut> getStatutsInHistoryFrom_creationDate(SearchHistoryStatut search_history, boolean over) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<HistoryStatut> historyStatuts = null;
		if(search_history == null || search_history.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String pcdate = search_history.getSearchedCreationDate();
		String query = "SELECT * FROM HistoryStatut WHERE creationDate > ?";
		if(!over)
			query = query.replace(">", "<");
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);		
		statement.setString(1, pcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched histories of status from creation date doesn't match to any element in the database !");
		
		historyStatuts = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			historyStatuts.add(new HistoryStatut(results.getInt("ticket_id"),
									 			 results.getInt("statut_id"),
									 			 results.getString("creationDate"),
									 			 results.getInt("employee_id"),
									 			 results.getInt("comment_id"))
			);
			results.next();
		}
		return historyStatuts;
	}

	
	@Override
	public List<HistoryStatut> getStatutsInHistoryFrom_creationDate(SearchHistoryStatut search_history)	throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<HistoryStatut> historyStatuts = null;
		if(search_history == null || search_history.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String pcdate = search_history.getSearchedCreationDate();
		String query = "SELECT * FROM HistoryStatut WHERE creationDate=?";		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, pcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched histories of status from creation date doesn't match to any element in the database !");
		
		historyStatuts = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			historyStatuts.add(new HistoryStatut(results.getInt("ticket_id"),
									 			 results.getInt("statut_id"),
									 			 results.getString("creationDate"),
									 			 results.getInt("employee_id"),
									 			 results.getInt("comment_id"))
			);
			results.next();
		}
		return historyStatuts;
	}
	

	@Override
	public List<HistoryStatut> getStatutsInHistoryFrom_comment(SearchHistoryStatut search_history) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<HistoryStatut> histories = null;
		if(search_history == null || !(search_history.getSearchedComment() >= 0))
			throw new SQLException("The research object or its comment must not be undefined");
		
		int hcomment = search_history.getSearchedComment();
		String query = "SELECT * FROM HistoryStatut WHERE comment_id IS NULL";
		if(hcomment > 0)
			query = query.replace("comment_id IS NULL", "comment_id=?");
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		if(query.contains("=?"))
			statement.setInt(1, hcomment);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched histories of status from comment doesn't match to any element in the database !");
		
		histories = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			histories.add(new HistoryStatut(results.getInt("ticket_id"),
										   results.getInt("statut_id"),
										   results.getString("creationDate"),
										   results.getInt("employee_id"),
										   hcomment)
			);
			results.next();
		}
		return histories;
	}

	
	@Override
	public List<HistoryStatut> getStatutsInHistoryFrom_author(SearchHistoryStatut search_history) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<HistoryStatut> histories = null;
		if(search_history == null || !(search_history.getSearchedAuthor() > 0))
			throw new SQLException("The research object or its author must not be undefined");
		
		int hauthor = search_history.getSearchedAuthor();
		String query = "SELECT * FROM HistoryStatut WHERE employee_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, hauthor);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched histories of status from author doesn't match to any element in the database !");
		
		histories = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			histories.add(new HistoryStatut(results.getInt("ticket_id"),
										   results.getInt("statut_id"),
										   results.getString("creationDate"),
										   hauthor,
										   results.getInt("comment_id"))
			);
			results.next();
		}
		return histories;
	}
	

	@Override
	public List<HistoryStatut> getStatutsInHistory_builder(SearchHistoryStatut search_history) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_history == null)
			throw new SQLException("The research object of the history of status query builder must not be undefined");
		
		List<HistoryStatut> histories = null;
		int status = 0;
		int ticket = 0;
		String cdate = null;
		int comment = -1;
		int author = 0;
		String query = "SELECT * FROM HistoryStatut WHERE ";
		int nbParam = 0;
		
		if(search_history.getSearchedStatut() > 0) {
			status = search_history.getSearchedStatut();
			query += "statut_id=?";
			nbParam++;
		} else {
			if(search_history.getSearchedTicket() > 0) {
				ticket = search_history.getSearchedTicket();
				query += "ticket_id=?";
				nbParam++;
			}
		}
		
		if(search_history.getSearchedCreationDate() != null) {
			cdate = search_history.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate LIKE ?";
			nbParam++;
		}
		
		if(search_history.getSearchedAuthor() > 0) {
			author = search_history.getSearchedAuthor();
			if(nbParam > 0) query += " AND ";
			query += "employee_id=?";
			nbParam++;
		}
		
		if(search_history.getSearchedComment() >= 0) {
			if(search_history.getSearchedComment() == 0) {
				if(nbParam > 0) query += " AND ";
				query += "comment_id IS NULL";
			} else {
				comment = search_history.getSearchedComment();
				if(nbParam > 0) query += " AND ";
				query += "comment_id=?";
				nbParam++;
			}
		}

		if(nbParam == 0 && !query.contains("comment_id")) 
			query = query.replace(" WHERE ", "");
		System.out.println(query);
		PreparedStatement statement = this.getConnection().prepareStatement(query);	
		
		if(status > 0)
			statement.setInt(1, status);
		else
			if(ticket > 0)
				statement.setInt(1, ticket);
		
		if(cdate != null)
			if(ticket > 0 || status > 0)
				statement.setString(2, "%"+cdate+"%");
			else 
				statement.setString(1, "%"+cdate+"%");
		
		if(author > 0)
			if((ticket > 0 || status > 0) && cdate != null)
				statement.setInt(3, author);
			else if((ticket > 0 || status > 0) || cdate != null)
				statement.setInt(2, author);
			else
				statement.setInt(1, author);
		
		if(comment > 0)
			if(nbParam == 1)
				statement.setInt(1, comment);
			else if(nbParam == 2)
				statement.setInt(2, comment);
			else if(nbParam == 3) 
				statement.setInt(3, comment);
			else if(nbParam == 4)
				statement.setInt(4, comment);
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comments from query builder doesn't match to any element in the database !");
		
		histories = new ArrayList<HistoryStatut>();
		while(!results.isAfterLast()) {
			histories.add(new HistoryStatut(results.getInt("ticket_id"),
										   results.getInt("statut_id"),
										   results.getString("creationDate"),
										   results.getInt("employee_id"),
										   results.getInt("comment_id"))
			);
			results.next();
		}
		return histories;
	}
	
	
	@Override
	public int createHistoryStatut(HistoryStatut new_history) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_history == null || !(new_history.getHistory_ticketID() > 0) || !(new_history.getHistory_statutID() > 0))
			throw new SQLException("The new history of status object to be created must be undefined !");
		
		int ticket = new_history.getHistory_ticketID();
		int status = new_history.getHistory_statutID();
		int author = new_history.getHistory_userID();
		int comment = new_history.getHistory_commentID();
		String cdate = null;
		if(new_history.getHistory_creationDate() != null) cdate = new_history.getHistory_creationDate().toString();
		
		String query = "INSERT INTO HistoryStatut";
		if(comment > 0 && cdate != null) query += " VALUES (?,?,?,?,?)";
		else if(cdate != null) query += "(ticket_id,statut_id,creationDate,employee_id) VALUES (?,?,?,?)";
		else if(comment > 0) query += "(ticket_id,statut_id,comment_id,employee_id) VALUES (?,?,?,?)";
		else query += "(ticket_id,statut_id,employee_id) VALUES (?,?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		statement.setInt(1, ticket);
		statement.setInt(2, status);
		if(cdate != null) {
			statement.setString(3, cdate);
			if(comment > 0) { 
				statement.setInt(4, comment);
				statement.setInt(5, author);
			} else statement.setInt(4, author);
		} else {
			if(comment > 0) { 
				statement.setInt(3, comment);
				statement.setInt(4, author);
			} else statement.setInt(3, author);
		}
		
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}
	
	
	@Override
	public int updateHistoryStatut(SearchHistoryStatut search_history) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_history == null || !(search_history.getSearchedTicket() > 0) || !(search_history.getSearchedStatut() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int ticket = search_history.getSearchedTicket();
		int status = search_history.getSearchedStatut();
		String cdate = null;
		int comment = -1;
		int author = 0;
		String query = "UPDATE HistoryStatut SET ";
		int nbParam = 0;
		
		if(search_history.getSearchedCreationDate() != null) {
			cdate = search_history.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate=?";
			nbParam++;
		}
		
		if(search_history.getSearchedAuthor() > 0) {
			author = search_history.getSearchedAuthor();
			if(nbParam > 0) query += " AND ";
			query += "employee_id=?";
			nbParam++;
		}
		
		if(search_history.getSearchedComment() >= 0) {
			if(search_history.getSearchedComment() == 0) {
				if(nbParam > 0) query += " AND ";
				query += "comment_id=NULL";
			} else {
				comment = search_history.getSearchedComment();
				if(nbParam > 0) query += " AND ";
				query += "comment_id=?";
				nbParam++;
			}
		}

		if(nbParam == 0 && !query.contains("comment_id")) 
			throw new SQLException("The fields of the history of status to be updated must not be all undefined");
		
		query += " WHERE ticket_id=? AND statut_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);	
		
		if(cdate != null)
			if(ticket > 0 || status > 0)
				statement.setString(2, cdate);
			else 
				statement.setString(1, cdate);
		
		if(author > 0)
			if((ticket > 0 || status > 0) && cdate != null)
				statement.setInt(3, author);
			else if((ticket > 0 || status > 0) || cdate != null)
				statement.setInt(2, author);
			else
				statement.setInt(1, author);
		
		if(comment > 0)
			if(nbParam == 1)
				statement.setInt(1, comment);
			else if(nbParam == 2)
				statement.setInt(2, comment);
			else if(nbParam == 3) 
				statement.setInt(3, comment);
			else if(nbParam == 4)
				statement.setInt(4, comment);
		
		if(nbParam == 1) {
			statement.setInt(2, ticket);
			statement.setInt(3, status);
		} else if(nbParam == 2) {
			statement.setInt(3, ticket);
			statement.setInt(4, status);
		} else if(nbParam == 3) {
			statement.setInt(4, ticket);
			statement.setInt(5, status);
		}
		
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}
	

	@Override
	public int deleteHistoryStatut(SearchHistoryStatut search_history) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_history == null || !(search_history.getSearchedTicket() > 0) || !(search_history.getSearchedStatut() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int hticket = search_history.getSearchedTicket();
		int hstatut = search_history.getSearchedStatut();
		String query = "DELETE FROM HistoryStatut WHERE ticket_id=? AND statut_id=?";
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, hticket);
		statement.setInt(2, hstatut);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		else 
			throw new SQLException("The researched history of status could not be deleted as it doesn't exist in the database !");
		return result;
	}

}
