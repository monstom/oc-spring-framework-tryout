package ticket.consumer.dao.impl.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.project.VersionDAO;
import ticket.model.bean.project.Version;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.project.SearchVersion;


public class VersionDAOImpl extends AbstractDAO implements VersionDAO {

	public VersionDAOImpl() {
		super();
	}

	
	@Override
	public List<Version> getAllVersions() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Version> versions = null;
		
		String query = "SELECT * FROM Version";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The version table in the database is empty : no rows found !");
		
		versions = new ArrayList<Version>();
		while(!results.isAfterLast()) {
			versions.add(new Version(results.getInt("project_id"),results.getString("label")));
			results.next();
		}
		return versions;
	}

	
	@Override
	public Version getVersionByID(SearchVersion search_version) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Version versionbyID = null;
		if(search_version == null || search_version.getSearchedLabel() == null)
			throw new SQLException("The research object and its label must not be undefined");
		else if(search_version == null || !(search_version.getSearchedVersionID() > 0))
			throw new SQLException("The research object and its project must not be undefined");
		
		int vid = search_version.getSearchedVersionID();
		String vlabel = search_version.getSearchedLabel();
		String query = "SELECT * FROM Version WHERE project_id=? AND label=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, vid);
		statement.setString(2, vlabel);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched version doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched version exists in multiples copies in the database !");
		else versionbyID = new Version(vid,vlabel);
		
		return versionbyID;
	}

	
	@Override
	public List<Version> getVersionsFrom_projectID(SearchVersion search_version) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Version> versions = null;
		if(!(search_version.getSearchedVersionID() > 0))
			throw new SQLException("The research object and its project must not be undefined");
		
		int vid = search_version.getSearchedVersionID();
		String query = "SELECT * FROM Version WHERE project_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, vid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched versions from project doesn't match to any element in the database  !");
		
		versions = new ArrayList<Version>();
		while(!results.isAfterLast()) {
			versions.add(new Version(vid,results.getString("label")));
			results.next();
		}
		return versions;
	}

	
	@Override
	public List<Version> getVersionsFrom_label(SearchVersion search_version) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Version> versions = null;
		if(search_version == null || search_version.getSearchedLabel() == null)
			throw new SQLException("The research object and its label must not be undefined");
		
		String label = search_version.getSearchedLabel();
		String query = "SELECT * FROM Version WHERE label LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, label);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched versions from label doesn't match to any element in the database !");
		
		versions = new ArrayList<Version>();
		while(!results.isAfterLast()) {
			versions.add(new Version(results.getInt("project_id"),results.getString("label")));
			results.next();
		}
		return versions;
	}

	
	@Override
	public int createVersion(Version new_version) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_version == null || !(new_version.getVersion_projectID() > 0) || new_version.getVersion_label() == null)
			throw new SQLException("The new version object to be created must be undefined !");
		
		int project = new_version.getVersion_projectID();
		String label = new_version.getVersion_label();
		String query = "INSERT INTO Version VALUES (?,?)";		
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, project);
		statement.setString(2, label);
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}


	@Override
	public int deleteVersion(SearchVersion search_version) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_version == null || !(search_version.getSearchedVersionID() > 0) || search_version.getSearchedLabel() == null)
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int vid = search_version.getSearchedVersionID();
		String vlabel = search_version.getSearchedLabel();
		String query = "DELETE FROM Version WHERE project_id=? AND label=?";
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, vid);
		statement.setString(2, vlabel);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		else 
			throw new SQLException("The researched version could not be deleted as it doesn't exist in the database !");
		return result;
	}

}
