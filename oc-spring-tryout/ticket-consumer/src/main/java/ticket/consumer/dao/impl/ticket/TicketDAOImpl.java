package ticket.consumer.dao.impl.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.ticket.TicketDAO;
import ticket.model.bean.ticket.Ticket;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.ticket.SearchTicket;

public class TicketDAOImpl extends AbstractDAO implements TicketDAO {

	public TicketDAOImpl() {
		super();
	}
	
	
	@Override
	public List<Ticket> getAllTickets() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		String query = "SELECT * FROM Ticket";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The ticket table in the database is empty : no rows found !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public Ticket getTicketByID(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Ticket ticketbyID = null;
		if(search_ticket == null || !(search_ticket.getSearchedTicketID() > 0))
			throw new SQLException("The research object and its id must not be undefined");
		
		int tid = search_ticket.getSearchedTicketID();
		String query = "SELECT * FROM Ticket WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, tid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched ticket doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched ticket exists in multiples copies in the database !");
		else ticketbyID = new Ticket(tid,
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getString("description"),
									 results.getInt("statut_id"),
									 results.getInt("author_id"),
									 results.getInt("project_id"));
		
		return ticketbyID;
	}

	
	@Override
	public List<Ticket> getTicketsLike_title(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || search_ticket.getSearchedTitle() == null)
			throw new SQLException("The research object or its title must not be undefined");
		
		String ttitle = search_ticket.getSearchedTitle();
		String query = "SELECT * FROM Ticket WHERE title LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%"+ttitle+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from title doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public List<Ticket> getTicketsFrom_creationDate(SearchTicket search_ticket, boolean over) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || search_ticket.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String tcdate = search_ticket.getSearchedCreationDate();
		String query = "SELECT * FROM Ticket WHERE creationDate > ?";
		if(!over) 
			query = query.replace(">", "<");
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, tcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from creation date doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}


	@Override
	public List<Ticket> getTicketsFrom_creationDate(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || search_ticket.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String tcdate = search_ticket.getSearchedCreationDate();
		String query = "SELECT * FROM Ticket WHERE creationDate=?";		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, tcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from creation date doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}
	
	
	@Override
	public List<Ticket> getTicketsLike_description(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || search_ticket.getSearchedDescription() == null)
			throw new SQLException("The research object or its description must not be undefined");
		
		String tdesc = search_ticket.getSearchedDescription();
		String query = "SELECT * FROM Ticket WHERE description LIKE ?";
		if(tdesc.isBlank() || tdesc.isEmpty())
			query = query.replace("LIKE ?", "IS NULL");
		System.out.println(query);
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		if(query.contains("LIKE"))
			statement.setString(1, "%"+tdesc+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from description doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public List<Ticket> getTicketsFrom_statut(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || !(search_ticket.getSearchedStatut() > 0))
			throw new SQLException("The research object or its status must not be undefined");
		
		int tstatut = search_ticket.getSearchedStatut();
		String query = "SELECT * FROM Ticket WHERE statut_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, tstatut);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from status doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   tstatut,
								   results.getInt("author_id"),								   
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public List<Ticket> getTicketsFrom_author(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || !(search_ticket.getSearchedAuthor() > 0))
			throw new SQLException("The research object or its author must not be undefined");
		
		int tauthor = search_ticket.getSearchedAuthor();
		String query = "SELECT * FROM Ticket WHERE author_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, tauthor);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from author doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),
								   tauthor,
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public List<Ticket> getTicketsFrom_project(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Ticket> tickets = null;
		if(search_ticket == null || !(search_ticket.getSearchedProject() > 0))
			throw new SQLException("The research object or its project must not be undefined");
		
		int tproject = search_ticket.getSearchedProject();
		String query = "SELECT * FROM Ticket WHERE project_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, tproject);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from project doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),								   
								   results.getInt("author_id"),
								   tproject)
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public List<Ticket> getTickets_builder(SearchTicket search_ticket) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_ticket == null)
			throw new SQLException("The research object of the ticket query builder must not be undefined");
		
		List<Ticket> tickets = null;
		String title = null;
		String cdate = null;
		String desc = null;
		int status = 0;
		int author = 0;
		int project = 0;
		String query = "SELECT * FROM TICKET WHERE ";
		int nbParam = 0;
		
		if(search_ticket.getSearchedTitle() != null) {
			title = search_ticket.getSearchedTitle();
			query += "title LIKE ?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedCreationDate() != null) {
			cdate = search_ticket.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate LIKE ?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedDescription() != null) {
			if(search_ticket.getSearchedDescription().isBlank() || search_ticket.getSearchedDescription().isEmpty()) {
				if(nbParam > 0) query += " AND ";
				query += "description IS NULL";
			} else {
				desc = search_ticket.getSearchedDescription();
				if(nbParam > 0) query += " AND ";
				query += "description LIKE ?";
				nbParam++;
			}
			
		}
		
		if(search_ticket.getSearchedStatut() > 0) {
			status = search_ticket.getSearchedStatut();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "statut_id=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedAuthor() > 0) {
			author = search_ticket.getSearchedAuthor();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "author_id=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedProject() > 0) {
			project = search_ticket.getSearchedProject();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "project_id=?";
			nbParam++;
		}
		
		if(nbParam == 0 && !query.contains("description")) 
			query = query.replace(" WHERE ", "");
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(title != null)
			statement.setString(1, "%"+title+"%");
		
		if(cdate != null)
			if(title != null)
				statement.setString(2, "%"+cdate+"%");
			else 
				statement.setString(1, "%"+cdate+"%");
		
		if(desc != null)
			if(title != null && cdate != null)
				statement.setString(3, "%"+desc+"%");
			else if(title != null || cdate != null)
				statement.setString(2, "%"+desc+"%");
			else
				statement.setString(1, "%"+desc+"%");
		
		if(status > 0) 
			if(title != null && cdate != null && desc != null)
				statement.setInt(4, status);
			else if(title == null && cdate == null && desc == null)
				statement.setInt(1, status);
			else if(title != null || cdate != null || desc != null)
				statement.setInt(2, status);
			else 
				statement.setInt(3, status);
		
		if(author > 0)
			if(title != null && cdate != null && desc != null && status != 0)
				statement.setInt(5, author);
			else if(title == null && cdate == null && desc == null && status == 0)
				statement.setInt(1, author);
			else if(status != 0)
				if(title != null && cdate != null && desc != null)
					statement.setInt(4, author);
				else if(title == null && cdate == null && desc == null)
					statement.setInt(2, author);
				else 
					statement.setInt(3, author);
		
		if(project > 0)
			if(nbParam == 1)
				statement.setInt(1, project);
			else if(nbParam == 2)
				statement.setInt(2, project);
			else if(nbParam == 3)
				statement.setInt(3, project);
			else if(nbParam == 4)
				statement.setInt(4, project);
			else if(nbParam == 5)
				statement.setInt(5, project);
			else if(nbParam == 6)
				statement.setInt(6, project);
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched tickets from project doesn't match to any element in the database !");
		
		tickets = new ArrayList<Ticket>();
		while(!results.isAfterLast()) {
			tickets.add(new Ticket(results.getInt("id"),
								   results.getString("title"),
								   results.getString("creationDate"),
								   results.getString("description"),
								   results.getInt("statut_id"),								   
								   results.getInt("author_id"),
								   results.getInt("project_id"))
			);
			results.next();
		}
		return tickets;
	}

	
	@Override
	public int createTicket(Ticket new_ticket) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_ticket == null) 
			throw new SQLException("The new ticket object to be created must be undefined !");
		
		String title = new_ticket.getTicket_title();
		String desc = new_ticket.getTicket_description();
		int status = new_ticket.getTicket_statutID();
		int project = new_ticket.getTicket_projectID();
		int author = new_ticket.getTicket_authorID();
		int tid = 0;		
		if(new_ticket.getTicketID() > 0) tid = new_ticket.getTicketID();
		String cdate = null;
		if(new_ticket.getTicket_creationDate() != null) cdate = new_ticket.getTicket_creationDate().toString();
		
		String query = "INSERT INTO Ticket";
		if(tid > 0 && cdate != null) query += " VALUES (?,?,?,?,?,?,?)";
		else if(tid > 0) query += "(id,title,description,statut_id,author_id,project_id) VALUES (?,?,?,?,?,?)";
		else if(cdate != null) query += "(title,creationDate,description,statut_id,author_id,project_id) VALUES (?,?,?,?,?,?)";
		else query += "(title,description,statut_id,author_id,project_id) VALUES (?,?,?,?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(tid > 0) {
			statement.setInt(1, tid);
			statement.setString(2, title);
			if(cdate != null) {
				statement.setString(3, cdate);
				statement.setString(4, desc);
				statement.setInt(5, status);
				statement.setInt(6, project);
				statement.setInt(7, author);
			} else {
				statement.setString(3, desc);
				statement.setInt(4, status);
				statement.setInt(5, project);
				statement.setInt(6, author);				
			}
			
		} else {
			statement.setString(1, title);
			if(cdate != null) {
				statement.setString(2, cdate);
				statement.setString(3, desc);
				statement.setInt(4, status);
				statement.setInt(5, project);
				statement.setInt(6, author);
			} else {
				statement.setString(2, desc);
				statement.setInt(3, status);
				statement.setInt(4, project);
				statement.setInt(5, author);
			}
		}
		
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) {
			if(tid > 0) result = tid;
			else result = this.getLastInsertID();
		}
		return result;
	}
	
	
	@Override
	public int updateTicket(SearchTicket search_ticket) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_ticket == null || !(search_ticket.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int tid = search_ticket.getSearchedTicketID();
		String title = null;
		String cdate = null;
		String desc = null;
		int status = 0;
		int author = 0;
		int project = 0;
		String query = "UPDATE TICKET SET ";
		int nbParam = 0;
		
		if(search_ticket.getSearchedTitle() != null) {
			title = search_ticket.getSearchedTitle();
			query += "title=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedCreationDate() != null) {
			cdate = search_ticket.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedDescription() != null) {
			if(search_ticket.getSearchedDescription().isBlank() || search_ticket.getSearchedDescription().isEmpty()) {
				if(nbParam > 0) query += " AND ";
				query += "description=NULL";
			} else {
				desc = search_ticket.getSearchedDescription();
				if(nbParam > 0) query += " AND ";
				query += "description=?";
				nbParam++;
			}			
		}
		
		if(search_ticket.getSearchedStatut() > 0) {
			status = search_ticket.getSearchedStatut();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "statut_id=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedAuthor() > 0) {
			author = search_ticket.getSearchedAuthor();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "author_id=?";
			nbParam++;
		}
		
		if(search_ticket.getSearchedProject() > 0) {
			project = search_ticket.getSearchedProject();
			if(nbParam > 0 || query.contains("description")) query += " AND ";
			query += "project_id=?";
			nbParam++;
		}
		
		if(nbParam == 0 && !query.contains("description")) 
			throw new SQLException("The fields of the ticket to be updated must not be all undefined");
			
		query += " WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(title != null)
			statement.setString(1, title);
		
		if(cdate != null)
			if(title != null)
				statement.setString(2, cdate);
			else 
				statement.setString(1, cdate);
		
		if(desc != null)
			if(title != null && cdate != null)
				statement.setString(3, desc);
			else if(title != null || cdate != null)
				statement.setString(2, desc);
			else
				statement.setString(1, desc);
		
		if(status > 0) 
			if(title != null && cdate != null && desc != null)
				statement.setInt(4, status);
			else if(title == null && cdate == null && desc == null)
				statement.setInt(1, status);
			else if(title != null || cdate != null || desc != null)
				statement.setInt(2, status);
			else 
				statement.setInt(3, status);
		
		if(author > 0)
			if(title != null && cdate != null && desc != null && status != 0)
				statement.setInt(5, author);
			else if(title == null && cdate == null && desc == null && status == 0)
				statement.setInt(1, author);
			else if(status != 0)
				if(title != null && cdate != null && desc != null)
					statement.setInt(4, author);
				else if(title == null && cdate == null && desc == null)
					statement.setInt(2, author);
				else 
					statement.setInt(3, author);
		
		if(project > 0)
			if(nbParam == 1)
				statement.setInt(1, project);
			else if(nbParam == 2)
				statement.setInt(2, project);
			else if(nbParam == 3)
				statement.setInt(3, project);
			else if(nbParam == 4)
				statement.setInt(4, project);
			else if(nbParam == 5)
				statement.setInt(5, project);
			else if(nbParam == 6)
				statement.setInt(6, project);

		if(nbParam == 1)
			statement.setInt(2, tid);
		else if(nbParam == 2)
			statement.setInt(3, tid);
		else if(nbParam == 3)
			statement.setInt(4, tid);
		else if(nbParam == 4)
			statement.setInt(5, tid);
		else if(nbParam == 5)
			statement.setInt(6, tid);
		else if(nbParam == 6)
			statement.setInt(7, tid);
		
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = tid;
		return result;
	}

	
	@Override
	public int deleteTicket(SearchTicket search_ticket) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_ticket == null || !(search_ticket.getSearchedTicketID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int tid = search_ticket.getSearchedTicketID();
		String query = "DELETE FROM Ticket WHERE id=?";
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, tid);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = tid;
		else 
			throw new SQLException("The researched ticket could not be deleted as it doesn't exist in the database !");
		return result;
	}


	@Override
	public int getLastInsertID() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		int lastid = -1;
		String query = "SELECT id FROM Ticket ORDER BY id DESC LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		if(results.next()) lastid = results.getInt("id");
		return lastid;
	}

}