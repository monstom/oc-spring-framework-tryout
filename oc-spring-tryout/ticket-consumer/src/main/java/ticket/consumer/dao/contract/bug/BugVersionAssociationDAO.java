package ticket.consumer.dao.contract.bug;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.bug.BugVersionAssociation;
import ticket.model.search.bug.SearchBugVersionAssociation;

public interface BugVersionAssociationDAO {

	public List<BugVersionAssociation> getAllBugVersionAssociations() throws SQLException, Exception;
	
	public List<BugVersionAssociation> getAssociatedVersionsFrom_bugID(SearchBugVersionAssociation search_asso) throws SQLException, Exception;
	
	public List<BugVersionAssociation> getAssociatedBugsFrom_versionID(SearchBugVersionAssociation search_asso) throws SQLException, Exception;
	
	public BugVersionAssociation getBugVersionAssociationByID(SearchBugVersionAssociation search_asso) throws SQLException, Exception;
	
	public int createBugVersionAssociation(BugVersionAssociation new_bugverAsso) throws SQLException;
	
	public int deleteBugVersionAssociation(SearchBugVersionAssociation search_asso) throws SQLException;
}
