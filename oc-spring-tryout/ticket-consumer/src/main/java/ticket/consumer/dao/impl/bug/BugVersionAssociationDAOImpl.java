package ticket.consumer.dao.impl.bug;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.bug.BugVersionAssociationDAO;
import ticket.model.bean.bug.BugVersionAssociation;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.bug.SearchBugVersionAssociation;

public class BugVersionAssociationDAOImpl extends AbstractDAO implements BugVersionAssociationDAO {

	public BugVersionAssociationDAOImpl() {
		super();
	}
	

	@Override
	public List<BugVersionAssociation> getAllBugVersionAssociations() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<BugVersionAssociation> bvAssos = null;
		
		String query = "SELECT * FROM BugVersionAssociation";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The bug-version association table in the database is empty : no rows found !");
		
		bvAssos = new ArrayList<BugVersionAssociation>();
		while(!results.isAfterLast()) {
			bvAssos.add(new BugVersionAssociation(results.getInt("bug_id"),results.getInt("version_id"),results.getString("version_label")));
			results.next();
		}
		return bvAssos;
	}

	
	@Override
	public BugVersionAssociation getBugVersionAssociationByID(SearchBugVersionAssociation search_asso) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		BugVersionAssociation assobyID = null;
		if(search_asso == null || !(search_asso.getSearchedBugID() > 0) || !(search_asso.getSearchedVersionID() > 0) || search_asso.getSearchedVersionLabel() == null)
			throw new SQLException("The research object or its bug and version must not be undefined");
		
		int bid = search_asso.getSearchedBugID();
		int vid = search_asso.getSearchedVersionID();
		String vlabel = search_asso.getSearchedVersionLabel();
		String query = "SELECT * FROM BugVersionAssociation WHERE bug_id=? AND version_id=? AND version_label=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bid);
		statement.setInt(2, vid);
		statement.setString(3, vlabel);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched bug-version association doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched bug-version association exists in multiples copies in the database !");
		else assobyID = new BugVersionAssociation(bid,vid,vlabel);
		
		return assobyID;
	}

	
	@Override
	public List<BugVersionAssociation> getAssociatedVersionsFrom_bugID(SearchBugVersionAssociation search_asso)	throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<BugVersionAssociation> bvAssos = null;
		if(search_asso == null || !(search_asso.getSearchedBugID() > 0))
			throw new SQLException("The research object or its bug must not be undefined");
		
		int bid = search_asso.getSearchedBugID();
		String query = "SELECT * FROM BugVersionAssociation WHERE bug_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched bug-version association from bug doesn't match to any element in the database !");
		
		bvAssos = new ArrayList<BugVersionAssociation>();
		while(!results.isAfterLast()) {
			bvAssos.add(new BugVersionAssociation(bid,results.getInt("version_id"),results.getString("version_label")));
			results.next();
		}
		return bvAssos;
	}

	
	@Override
	public List<BugVersionAssociation> getAssociatedBugsFrom_versionID(SearchBugVersionAssociation search_asso) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<BugVersionAssociation> bvAssos = null;
		int vid = 0;
		String vlabel = "";
		if(search_asso != null && search_asso.getSearchedVersionID() > 0 && search_asso.getSearchedVersionLabel() != null) {
			vid = search_asso.getSearchedVersionID();
			vlabel = search_asso.getSearchedVersionLabel();
		} 
		else if(search_asso != null && search_asso.getSearchedVersionID() > 0)
			vid = search_asso.getSearchedVersionID();
		else if(search_asso != null && search_asso.getSearchedVersionLabel() != null)
			vlabel = search_asso.getSearchedVersionLabel();
		else
			throw new SQLException("The research object or its version must not be undefined");
		
		String query = "SELECT * FROM BugVersionAssociation WHERE ";
		if(vid > 0 && !vlabel.isEmpty() && !vlabel.isBlank())
			query += "version_id=? AND version_label=?"; 
		else if(vid > 0) 
			query += "version_id=?";
		else if(!vlabel.isEmpty() && !vlabel.isBlank()) 
			query += "version_label=?";
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		if(query.contains("version_id")) {
			statement.setInt(1, vid);
			if(query.contains("version_label")) 
				statement.setString(2, vlabel);
		} else if(query.contains("version_label"))
			statement.setString(1, vlabel);
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched bug-version association from version doesn't match to any element in the database !");
		
		bvAssos = new ArrayList<BugVersionAssociation>();
		while(!results.isAfterLast()) {
			bvAssos.add(new BugVersionAssociation(results.getInt("bug_id"),results.getInt("version_id"),results.getString("version_label")));
			results.next();
		}
		return bvAssos;
	}

	
	@Override
	public int createBugVersionAssociation(BugVersionAssociation new_bugverAsso) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_bugverAsso == null || !(new_bugverAsso.getBugVersionAssociation_bugID() > 0) || !(new_bugverAsso.getBugVersionAssociation_versionID() > 0) || new_bugverAsso.getBugVersionAssociation_versionLabel() == null)
			throw new SQLException("The new bug-version association object to be created must be undefined !");
		
		int bug_id = new_bugverAsso.getBugVersionAssociation_bugID();
		int version_id = new_bugverAsso.getBugVersionAssociation_versionID();
		String version_label = new_bugverAsso.getBugVersionAssociation_versionLabel();
		String query = "INSERT INTO BugVersionAssociation VALUES (?,?,?)";		
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bug_id);
		statement.setInt(2, version_id);
		statement.setString(3, version_label);
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		return result;
	}
	

	@Override
	public int deleteBugVersionAssociation(SearchBugVersionAssociation search_asso) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_asso == null || !(search_asso.getSearchedBugID() > 0) || !(search_asso.getSearchedVersionID() > 0) || search_asso.getSearchedVersionLabel() == null)
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int bvasso_bug = search_asso.getSearchedBugID();
		int bvasso_vid = search_asso.getSearchedVersionID();
		String bvasso_vlbl = search_asso.getSearchedVersionLabel();
		String query = "DELETE FROM BugVersionAssociation WHERE bug_id=? AND version_id=? AND version_label=?";
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, bvasso_bug);
		statement.setInt(2, bvasso_vid);
		statement.setString(3, bvasso_vlbl);
		statement.setQueryTimeout(1);
		
		int rows = statement.executeUpdate();
		if(rows == 1) result = rows;
		else 
			throw new SQLException("The researched association of bug and version could not be deleted as it doesn't exist in the database !");
		return result;
	}
	
}
