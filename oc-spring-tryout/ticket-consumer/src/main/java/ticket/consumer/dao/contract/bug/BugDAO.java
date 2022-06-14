package ticket.consumer.dao.contract.bug;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.bug.Bug;
import ticket.model.search.bug.SearchBug;

public interface BugDAO {
	
	public List<Bug> getAllBugs() throws SQLException;
	
	public List<Bug> getBugsFrom_severity(SearchBug search_bug) throws SQLException;
	
	public Bug getBugByID(SearchBug search_bug) throws SQLException;
	
	public int createBug(Bug new_bug) throws SQLException;
	
	public int updateBug(SearchBug search_bug) throws SQLException;
	
	public int deleteBug(SearchBug search_bug) throws SQLException;
}
