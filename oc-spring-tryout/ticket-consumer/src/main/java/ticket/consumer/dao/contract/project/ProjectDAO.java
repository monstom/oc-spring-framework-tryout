package ticket.consumer.dao.contract.project;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.project.Project;
import ticket.model.search.project.SearchProject;

public interface ProjectDAO {
	
	public List<Project> getAllProjects() throws SQLException;
	
	public List<Project> getProjectsLike_title(SearchProject search_project) throws SQLException;
	
	public List<Project> getProjectsFrom_creationDate(SearchProject search_project, boolean over) throws SQLException;
	
	public List<Project> getProjectsFrom_creationDate(SearchProject search_project) throws SQLException;
	
	public List<Project> getProjectsFrom_state(SearchProject search_project) throws SQLException;
	
	public List<Project> getProjectsFrom_manager(SearchProject search_project) throws SQLException;
	
	public List<Project> getProjects_builder(SearchProject search_project) throws SQLException;	
	
	public Project getProjectByID(SearchProject search_project) throws SQLException;
	
	public int createProject(Project new_project) throws SQLException;

	public int updateProject(SearchProject search_project) throws SQLException;
	
	public int deleteProject(SearchProject search_project) throws SQLException;
	
	public int getLastInsertID() throws SQLException;

}
