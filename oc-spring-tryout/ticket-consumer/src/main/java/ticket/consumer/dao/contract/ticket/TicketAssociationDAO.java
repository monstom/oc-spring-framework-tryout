package ticket.consumer.dao.contract.ticket;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.ticket.TicketAssociation;
import ticket.model.search.ticket.SearchTicketAssociation;

public interface TicketAssociationDAO {

	public List<TicketAssociation> getAllTicketAssos() throws SQLException, Exception;
	
	public List<TicketAssociation> getAssociatedTicketOf_ticketID(SearchTicketAssociation search_ticketAsso) throws SQLException, Exception;
	
	public TicketAssociation getTicketAssociationByID(SearchTicketAssociation search_ticketAsso) throws SQLException, Exception;

	public int createTicketAssociation(TicketAssociation new_ticketAsso) throws SQLException;
		
	public int deleteTicketAssociation(SearchTicketAssociation search_ticketAsso) throws SQLException;
}
