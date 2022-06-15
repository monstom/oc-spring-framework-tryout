package ticket.consumer.dao.contract.ticket;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.ticket.HistoryStatut;
import ticket.model.search.ticket.SearchHistoryStatut;

public interface HistoryStatutDAO {
	
	public List<HistoryStatut> getAllStatutsInHistory() throws SQLException, Exception;
	
	public List<HistoryStatut> getStatutsInHistoryFrom_creationDate(SearchHistoryStatut search_history, boolean over) throws SQLException, Exception;

	public List<HistoryStatut> getStatutsInHistoryFrom_creationDate(SearchHistoryStatut search_history) throws SQLException, Exception;

	public List<HistoryStatut> getStatutsInHistoryFrom_comment(SearchHistoryStatut search_history) throws SQLException, Exception;

	public List<HistoryStatut> getStatutsInHistoryFrom_author(SearchHistoryStatut search_history) throws SQLException, Exception;

	public List<HistoryStatut> getStatutsInHistory_builder(SearchHistoryStatut search_history) throws SQLException, Exception;
	
	public HistoryStatut getStatutInHistoryByID(SearchHistoryStatut search_history) throws SQLException, Exception;
	
	public int createHistoryStatut(HistoryStatut new_history) throws SQLException;
	
	public int updateHistoryStatut(SearchHistoryStatut search_history) throws SQLException;
	
//	public int archiveHistoryStatut(SearchHistoryStatut search_history) throws SQLException;
	
	public int deleteHistoryStatut(SearchHistoryStatut search_history) throws SQLException;
}
