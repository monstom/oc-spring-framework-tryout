package ticket.consumer.dao.contract.project;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.project.Version;
import ticket.model.search.project.SearchVersion;

public interface VersionDAO {
	
	public List<Version> getAllVersions() throws SQLException;	
	
	public List<Version> getVersionsFrom_projectID(SearchVersion search_version) throws SQLException;
	
	public List<Version> getVersionsFrom_label(SearchVersion search_version) throws SQLException;
	
	public Version getVersionByID(SearchVersion search_version) throws SQLException;
	
	public int createVersion(Version new_version) throws SQLException;
		
	public int deleteVersion(SearchVersion search_version) throws SQLException;
}
