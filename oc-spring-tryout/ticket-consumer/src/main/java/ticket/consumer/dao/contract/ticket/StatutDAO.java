package ticket.consumer.dao.contract.ticket;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.ticket.Statut;
import ticket.model.search.ticket.SearchStatut;

public interface StatutDAO {
	
	public List<Statut> getAllStatuts() throws SQLException;
	
	public List<Statut> getStatutsLike_label(SearchStatut search_statut) throws SQLException;
	
	public Statut getStatutByID(SearchStatut search_statut) throws SQLException;
	
	public int createStatut(Statut new_statut) throws SQLException;
	
	public int updateStatut(SearchStatut search_statut) throws SQLException;
	
	public int deleteStatut(SearchStatut search_statut) throws SQLException;

	public int getLastInsertID() throws SQLException;
	
}
