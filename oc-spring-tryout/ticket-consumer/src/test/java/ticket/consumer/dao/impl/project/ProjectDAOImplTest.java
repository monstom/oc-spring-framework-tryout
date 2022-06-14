package ticket.consumer.dao.impl.project;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.db.DatabaseConnectionTypes;
import ticket.consumer.db.contract.DatabaseConnection;
import ticket.consumer.db.impl.DatabaseConnectionFactoryImpl;
import ticket.model.bean.project.Project;
import ticket.model.search.project.SearchProject;


@Tag("ProjectDAOImplClass_UniteTesting")
public class ProjectDAOImplTest {

	private ProjectDAOImpl projectDAO;
	private static int newProjectid;
	
	@BeforeEach
	public void init_ProjectDAO() {
		System.out.println("ProjectDAOImplTest -  Initialization before each test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		projectDAO = new ProjectDAOImpl();
	}
	
	@AfterEach
	public void end_ProjectDAO() {
		projectDAO = null;
		System.out.println("ProjectDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_ProjectDAOTest() {
		newProjectid = -1;
		System.out.println("ProjectDAOImplTest - Start of unite testing for class ProjectDAOImpl");
	}
	
	@AfterAll 
	public static void end_ProjectDAOTest() {
		newProjectid = -1;
		System.out.println("ProjectDAOImplTest - End of unite testing for class ProjectDAOImpl");
	}
	
	
	@Test
	@Tag("ProjectDAOImpl-instance_valid")
	public void validInstanceOf_ProjectDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(projectDAO instanceof ProjectDAOImpl && projectDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("ProjectDAOImpl-find_allProjects")
	public void validBehaviorOf_getAllProjects() {
		// Arrange
		System.out.println("FindAllProjects");
		List<Project> projects = null;
		
		// Act
		try {
			projects = projectDAO.getAllProjects();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(projects == null);
			return;
		}
		System.out.println(projects);
		
		// Assert
		assertTrue(!projects.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a project by its id must match only one or no records in the database !")
	@ValueSource(ints = { 2, 1, -6, 0, 10 })
	@Tag("BugDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getProjectbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Project project = null;
		SearchProject searched_project = new SearchProject();
		
		// Act
		try {
			searched_project = searched_project.setSearchedProjectID(arg1);
			project = projectDAO.getProjectByID(searched_project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(project == null);
			return;
		}
		System.out.println(project);
		
		// Assert
		assertTrue(project != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research projects from its title must be able to return any macthing records in the database !")
	@ValueSource(strings = { "developpement", "iPhone", "R&D", "Marketing","" })
	@Tag("ProjectDAOImpl-findLike_Title")
	public void validBehaviorOf_getProjectsLikeTitle(String arg1) {
		// Arrange
		System.out.println("FindLikeTitle");
		System.out.println("title : ["+arg1+"]");
		List<Project> projects = null;
		SearchProject searched_project = new SearchProject();
		
		// Act
		try {
			searched_project = searched_project.setSearchedTitle(arg1);
			projects = projectDAO.getProjectsLike_title(searched_project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(projects == null);
			return;
		}
		System.out.println(projects);
		
		// Assert
		assertTrue(projects != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research projects from its creation date must be able to return any macthing records in the database !")
	@CsvSource({ "'2022-03-25 14:53:37', ", "'2022-03-25', true", "'2022-04-01', false", "'-8', false", ", true" })
	@Tag("ProjectDAOImpl-findFrom_CreationDate")
	public void validBehaviorOf_getProjectsFromCreationDate(String arg1, String arg2) {
		// Arrange
		System.out.println("FindFromCreationDate");
		System.out.println("creation_date : ["+arg1+"]");
		System.out.println("over : ["+arg2+"]");
		List<Project> projects = null;
		SearchProject searched_project = new SearchProject();
		
		// Act
		try {
			searched_project = searched_project.setSearchedCreationDate(arg1);
			if(arg2 == null || arg2.isEmpty() || arg2.isBlank())
				projects = projectDAO.getProjectsFrom_creationDate(searched_project);
			else 
				projects = projectDAO.getProjectsFrom_creationDate(searched_project,Boolean.valueOf(arg2));	
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(projects == null);
			return;
		}
		System.out.println(projects);
		
		// Assert
		assertTrue(projects != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research projects from its state must be able to return any macthing records in the database !")
	@ValueSource(ints = { 1,0,-1 })
	@Tag("ProjectDAOImpl-findFrom_State")
	public void validBehaviorOf_getProjectsFromState(int arg1) {
		// Arrange
		System.out.println("FindFromState");
		System.out.println("state : ["+arg1+"]");
		List<Project> projects = null;
		SearchProject searched_project = new SearchProject();
		
		// Act
		try {			
			searched_project = searched_project.setSearchedState(arg1);
			projects = projectDAO.getProjectsFrom_state(searched_project);			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(projects == null);
			return;
		}
		System.out.println(projects);
		
		// Assert
		assertTrue(projects != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research projects from its manager id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("ProjectDAOImpl-findFrom_Manager")
	public void validBehaviorOf_getProjectsFromManager(int arg1) {
		// Arrange
		System.out.println("FindFromDAO");
		System.out.println("manager : ["+arg1+"]");
		List<Project> projects = null;
		SearchProject searched_project = new SearchProject();
		
		// Act
		try {
			searched_project = searched_project.setSearchedManager(arg1);
			projects = projectDAO.getProjectsFrom_manager(searched_project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(projects == null);
			return;
		}
		System.out.println(projects);
		
		// Assert
		assertTrue(projects != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a project by its id must successfully add a new record with the highest id in the database !")
	@CsvSource({ "1,R&D,2022-03-25 14:53:37,1,5", "0,'',2022-03-25 14:53:37,1,5", "0,R&D,'',1,5", "0,R&D,2022-03-25 14:53:37,-1,5", "0,R&D,2022-03-25 14:53:37,1,-5", "0,'test1','2022-03-25 14:53:37',1,5" })
	@Order(1)
	@Tag("ProjectDAOImpl-createProject")
	public void validBehaviorOf_createProject(int arg1, String arg2, String arg3, int arg4, int arg5) {
		// Arrange
		System.out.println("createProject(id,title,creationdate,state,manager)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("title : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("state : ["+arg4+"]");
		System.out.println("manager : ["+arg5+"]");
		Project project = null;
		int newid = -1;
		
		// Act
		try {
			if(arg4 > 0 && arg1 > 0) project = new Project(arg1,arg2,arg3,true,arg5);
			else if(arg1 > 0) project = new Project(arg1,arg2,arg3,false,arg5);
			else if(arg4 > 0) project = new Project(arg2,arg3,true,arg5);
			else project = new Project(arg2,arg3,false,arg5);
			newid = projectDAO.createProject(project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newProjectid = newid;
		
		// Assert
		assertTrue(newProjectid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a project by its id must successfully match only one record in the database !")
	@CsvSource({ "0,'','',-1,-5", "0,'test2',,0,0" })
	@Order(2)
	@Tag("ProjectDAOImpl-updateProject")
	public void validBehaviorOf_updateProject(int arg1, String arg2, String arg3, int arg4, int arg5) {
		// Arrange
		System.out.println("updateProject(id,title,creationdate,state,manager)");
		if(arg1 == 0) arg1 = newProjectid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("title : ["+arg2+"]");
		System.out.println("creationdate : ["+arg3+"]");
		System.out.println("state : ["+arg4+"]");
		System.out.println("manager : ["+arg5+"]");
		SearchProject search_project = new SearchProject();
		int id = -1;
		
		// Act
		try {
			search_project.setSearchedProjectID(arg1);
			if(arg2 != null) search_project.setSearchedTitle(arg2);
			if(arg3 != null) search_project.setSearchedCreationDate(arg3);
			if(arg4 >= 0) search_project.setSearchedState(arg4);
			if(arg5 > 0) search_project.setSearchedManager(arg5);
			id = projectDAO.updateProject(search_project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newProjectid);
	}
	
	
	@ParameterizedTest(name = "The sql query used to delete a project by its id must successfully match only one record in the database !")
	@Order(4)
	@Tag("ProjectDAOImpl-deleteProject")
	public void validBehaviorOf_deleteProject() {
		// Arrange
		System.out.println("deleteProject(id)");
		System.out.println("id : ["+newProjectid+"]");
		SearchProject search_project = new SearchProject();
		int id = -1;
		
		// Act
		try {
			search_project.setSearchedProjectID(newProjectid);
			id = projectDAO.deleteProject(search_project);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newProjectid);
	}

}
