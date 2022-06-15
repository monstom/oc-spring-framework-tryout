package ticket.consumer.dao.contract.ticket;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.ticket.Ticket;
import ticket.model.search.ticket.SearchTicket;

public interface TicketDAO {
	
	public List<Ticket> getAllTickets() throws SQLException, Exception;
	
	public List<Ticket> getTicketsLike_title(SearchTicket search_ticket) throws SQLException, Exception;
	
	public List<Ticket> getTicketsFrom_creationDate(SearchTicket search_ticket, boolean over) throws SQLException, Exception;
	
	public List<Ticket> getTicketsFrom_creationDate(SearchTicket search_ticket) throws SQLException, Exception;
	
	public List<Ticket> getTicketsLike_description(SearchTicket search_ticket) throws SQLException, Exception;
	
	public List<Ticket> getTicketsFrom_statut(SearchTicket search_ticket) throws SQLException, Exception;
	
	public List<Ticket> getTicketsFrom_author(SearchTicket search_ticket) throws SQLException, Exception;

	public List<Ticket> getTicketsFrom_project(SearchTicket search_ticket) throws SQLException, Exception;
	
	public List<Ticket> getTickets_builder(SearchTicket search_ticket) throws SQLException, Exception;
	
	public Ticket getTicketByID(SearchTicket search_ticket) throws SQLException, Exception;
	
	public int createTicket(Ticket new_ticket) throws SQLException;
	
	public int updateTicket(SearchTicket search_ticket) throws SQLException;
	
	public int deleteTicket(SearchTicket search_ticket) throws SQLException;

	public int getLastInsertID() throws SQLException;
	
}
