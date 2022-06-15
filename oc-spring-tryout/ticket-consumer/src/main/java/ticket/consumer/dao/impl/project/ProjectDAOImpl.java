package ticket.consumer.dao.impl.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.project.ProjectDAO;
import ticket.model.bean.project.Project;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.project.SearchProject;

public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO {

	public ProjectDAOImpl() {
		super();
	}
	

	@Override
	public List<Project> getAllProjects() throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Project> projects = null;
		
		String query = "SELECT * FROM Project";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The project table in the database is empty : no rows found !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager")
			));
			results.next();
		}
		return projects;
	}

	
	@Override
	public Project getProjectByID(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Project projectbyID = null;
		if(search_project == null || !(search_project.getSearchedProjectID() > 0))
			throw new SQLException("The research object and its id must not be undefined");
		
		int pid = search_project.getSearchedProjectID();
		String query = "SELECT * FROM Project WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, pid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched project doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched project exists in multiples copies in the database !");
		else projectbyID = new Project(pid,
									   results.getString("title"),
									   results.getString("creationDate"),
									   results.getBoolean("closed"),
									   results.getInt("manager"));
		return projectbyID;
	}
	
	
	@Override
	public List<Project> getProjectsLike_title(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Project> projects = null;
		if(search_project == null || search_project.getSearchedTitle() == null)
			throw new SQLException("The research object or its title must not be undefined");
		
		String ptitle = search_project.getSearchedTitle();
		String query = "SELECT * FROM Project WHERE title LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%"+ptitle+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from title doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager"))
			);
			results.next();
		}
		return projects;
	}


	@Override
	public List<Project> getProjectsFrom_creationDate(SearchProject search_project, boolean over) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Project> projects = null;
		if(search_project == null || search_project.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String pcdate = search_project.getSearchedCreationDate();
		String query = "SELECT * FROM Project WHERE creationDate > ?";
		if(!over) 
			query = query.replace(">", "<");
		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, pcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from creation date doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager"))
			);
			results.next();
		}
		return projects;
	}
	

	@Override
	public List<Project> getProjectsFrom_creationDate(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Project> projects = null;
		if(search_project == null || search_project.getSearchedCreationDate() == null)
			throw new SQLException("The research object or its creation date must not be undefined");
		
		String pcdate = search_project.getSearchedCreationDate();
		String query = "SELECT * FROM Project WHERE creationDate=?";		
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, pcdate);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from creation date doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager"))
			);
			results.next();
		}
		return projects;
	}


	@Override
	public List<Project> getProjectsFrom_state(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Project> projects = null;
		if(search_project == null || search_project.getSearchedState() == 0)
			throw new SQLException("The research object and its state must not be undefined");
		
		int pstate = search_project.getSearchedState();
		String query = "SELECT * FROM Project WHERE closed=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		if(pstate > 0) 
			statement.setBoolean(1, true);
		else
			statement.setBoolean(1, false);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from state doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager"))
			);
			results.next();
		}
		return projects;
	}

	
	@Override
	public List<Project> getProjectsFrom_manager(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<Project> projects = null;
		if(search_project == null || !(search_project.getSearchedManager() > 0))
			throw new SQLException("The research object and its manager must not be undefined");
		
		int pmanager = search_project.getSearchedManager();
		String query = "SELECT * FROM Project WHERE manager=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, pmanager);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from manager doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 pmanager)
			);
			results.next();
		}
		return projects;
	}
	

	@Override
	public List<Project> getProjects_builder(SearchProject search_project) throws SQLException, Exception {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_project == null)
			throw new SQLException("The research object of the history of status query builder must not be undefined");
		
		List<Project> projects = null;
		String title = null;
		String cdate = null;
		int state = -1;
		int manager = 0;
		String query = "SELECT * FROM Project WHERE ";
		int nbParam = 0;
		
		if(search_project.getSearchedTitle() != null) {
			title = search_project.getSearchedTitle();
			query += "title LIKE ?";
			nbParam++;
		}
		
		if(search_project.getSearchedCreationDate() != null) {
			cdate = search_project.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate LIKE ?";
			nbParam++;
		}
				
		if(search_project.getSearchedState() >= 0) {
			state = search_project.getSearchedState();
			if(nbParam > 0) query += " AND ";
			query += "closed=?";
			nbParam++;
		}
		
		if(search_project.getSearchedManager() > 0) {
			manager = search_project.getSearchedManager();
			if(nbParam > 0) query += " AND ";
			query += "manager=?";
			nbParam++;
		}
		
		if(nbParam == 0) 
			query = query.replace(" WHERE ", "");
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(title != null)
			statement.setString(1, "%"+title+"%");
		
		if(cdate != null)
			if(title != null)
				statement.setString(2, "%"+cdate+"%");
			else
				statement.setString(1, "%"+cdate+"%");
		
		boolean bstate = false;
		if(state >= 0)
			if(state > 0) bstate = true;
			if(title != null && cdate != null)
				statement.setBoolean(3, bstate);
			else if(title != null || cdate != null)
				statement.setBoolean(2, bstate);
			else
				statement.setBoolean(1, bstate);
		
		if(manager > 0)
			if(nbParam == 1)
				statement.setInt(1, manager);
			else if(nbParam == 2)
				statement.setInt(2, manager);
			else if(nbParam == 3)
				statement.setInt(3, manager);
			else if(nbParam == 4)
				statement.setInt(4, manager);
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched projects from query builder doesn't match to any element in the database !");
		
		projects = new ArrayList<Project>();
		while(!results.isAfterLast()) {
			projects.add(new Project(results.getInt("id"),
									 results.getString("title"),
									 results.getString("creationDate"),
									 results.getBoolean("closed"),
									 results.getInt("manager"))
			);
			results.next();
		}
		return projects;
	}
	

	@Override
	public int createProject(Project new_project) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_project == null) 
			throw new SQLException("The new project object to be created must be undefined !");
		
		String title = new_project.getProject_title();
		boolean state = new_project.getProject_isClosed();
		int manager = new_project.getProject_managerID();
		int pid = 0;		
		if(new_project.getProjectID() > 0) pid = new_project.getProjectID();
		String cdate = null;
		if(new_project.getProject_creationDate() != null) cdate = new_project.getProject_creationDate().toString();
		
		String query = "INSERT INTO Project";
		if(pid > 0 && cdate != null) query += " VALUES (?,?,?,?,?)";
		else if(pid > 0) query += "(id,title,closed,manager) VALUES (?,?,?,?)";
		else if(cdate != null) query += "(title,creationDate,closed,manager) VALUES (?,?,?,?)";
		else query += "(title,closed,manager) VALUES (?,?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(pid > 0) {
			statement.setInt(1, pid);
			statement.setString(2, title);
			if(cdate != null) {
				statement.setString(3, cdate);
				statement.setBoolean(4, state);
				statement.setInt(5, manager);				
			} else {
				statement.setBoolean(3, state);
				statement.setInt(4, manager);
			}
		} else {
			statement.setString(1, title);
			if(cdate != null) {
				statement.setString(2, cdate);
				statement.setBoolean(3, state);
				statement.setInt(4, manager);				
			} else {
				statement.setBoolean(2, state);
				statement.setInt(3, manager);
			}
		}
		
		statement.setQueryTimeout(1);
		int row = statement.executeUpdate();
		if(row == 1) {
			if(pid > 0) result = pid;
			else result = this.getLastInsertID();
		}
		return result;
	}
	
	
	@Override
	public int updateProject(SearchProject search_project) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_project == null || !(search_project.getSearchedProjectID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int pid = search_project.getSearchedProjectID();
		String title = null;
		String cdate = null;
		int state = -1;
		int manager = 0;
		String query = "UPDATE Project SET ";
		int nbParam = 0;
		
		if(search_project.getSearchedTitle() != null) {
			title = search_project.getSearchedTitle();
			query += "title=?";
			nbParam++;
		}
		
		if(search_project.getSearchedCreationDate() != null) {
			cdate = search_project.getSearchedCreationDate();
			if(nbParam > 0) query += " AND ";
			query += "creationDate=?";
			nbParam++;
		}
				
		if(search_project.getSearchedState() >= 0) {
			state = search_project.getSearchedState();
			if(nbParam > 0) query += " AND ";
			query += "closed=?";
			nbParam++;
		}
		
		if(search_project.getSearchedManager() > 0) {
			manager = search_project.getSearchedManager();
			if(nbParam > 0) query += " AND ";
			query += "manager=?";
			nbParam++;
		}
		
		if(nbParam == 0) 
			throw new SQLException("The fields of the project to be updated must not be all undefined");
		
		query += " WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(title != null)
			statement.setString(1, title);
		
		if(cdate != null)
			if(title != null)
				statement.setString(2, cdate);
			else
				statement.setString(1, cdate);
		
		boolean bstate = false;
		if(state >= 0)
			if(state > 0) bstate = true;
			if(title != null && cdate != null)
				statement.setBoolean(3, bstate);
			else if(title != null || cdate != null)
				statement.setBoolean(2, bstate);
			else
				statement.setBoolean(1, bstate);
		
		if(manager > 0)
			if(nbParam == 1)
				statement.setInt(1, manager);
			else if(nbParam == 2)
				statement.setInt(2, manager);
			else if(nbParam == 3)
				statement.setInt(3, manager);
			else if(nbParam == 4)
				statement.setInt(4, manager);
		
		if(nbParam == 1)
			statement.setInt(2, pid);
		else if(nbParam == 2)
			statement.setInt(3, pid);
		else if(nbParam == 3)
			statement.setInt(4, pid);
		else if(nbParam == 4)
			statement.setInt(5, pid);
		
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = pid;
		return result;
	}

	
	@Override
	public int deleteProject(SearchProject search_project) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_project == null || !(search_project.getSearchedProjectID() > 0))
			throw new SQLException("The research object or its ticket id must not be undefined");
	
		int pid = search_project.getSearchedProjectID();
		String query = "DELETE FROM Project WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, pid);
		statement.setQueryTimeout(1);
		int rows = statement.executeUpdate();
		if(rows == 1) result = pid;
		else 
			throw new SQLException("The researched project could not be deleted as it doesn't exist in the database !");
		return result;
	}

	
	@Override
	public int getLastInsertID() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		int lastid = -1;
		String query = "SELECT id FROM Project ORDER BY id DESC LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		if(results.next()) lastid = results.getInt("id");
		return lastid;
	}
	
}
