package ticket.consumer.dao.contract.bug;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.bug.Severity;
import ticket.model.search.bug.SearchSeverity;

public interface SeverityDAO {
	
	public List<Severity> getAllSeverities() throws SQLException;
	
	public List<Severity> getSeveritiesFrom_level(SearchSeverity search_severity) throws SQLException;

	public List<Severity> getSeveritiesLike_label(SearchSeverity search_severity) throws SQLException;

	public List<Severity> getSeverities_builder(SearchSeverity search_severity) throws SQLException;
	
	public Severity getSeverityByID(SearchSeverity search_severity) throws SQLException;
	
	public int createSeverity(Severity new_severity) throws SQLException;
	
	public int updateSeverity(SearchSeverity search_severity) throws SQLException;
	
	public int deleteSeverity(SearchSeverity search_severity) throws SQLException;
	
	public int getLastInsertID() throws SQLException;
	
}
