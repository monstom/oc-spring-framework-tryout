package ticket.consumer.dao.impl.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.db.DatabaseConnectionTypes;
import ticket.consumer.db.contract.DatabaseConnection;
import ticket.consumer.db.impl.DatabaseConnectionFactoryImpl;
import ticket.model.bean.user.User;
import ticket.model.search.user.SearchUser;


@Tag("UserDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOImplTest {

	private UserDAOImpl userDAO;
	private static int newUserid;
	
	@BeforeEach
	public void init_UserDAO() {
		System.out.println("UserDAOImplTest - Initialization before each test");		
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		userDAO = new UserDAOImpl();
	}
	
	@AfterEach
	public void end_UserDAO() {
		userDAO = null;
		System.out.println("UserDAOImplTest - Clean after each test");
	}
	
	@BeforeAll 
	public static void init_UserDAOTest() {
		newUserid = -1;
		System.out.println("UserDAOImplTest - Start of unite testing for class UserDAOImpl");
	}
	
	@AfterAll 
	public static void end_UserDAOTest() {
		newUserid = -1;
		System.out.println("UserDAOImplTest - End of unite testing for class UserDAOImpl");
	}
	
	
	@Test
	@Tag("UserDAOImpl-instance_valid")
	public void validInstanceOf_UserDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(userDAO instanceof UserDAOImpl && userDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("UserDAOImpl-find_allUsers")
	public void validBehaviorOf_getAllUsers() {
		// Arrange
		System.out.println("FindAllUsers");
		List<User> users = null;
		
		// Act
		try {
			users = userDAO.getAllUsers();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(users == null);
			return;
		}
		System.out.println(users);
		
		// Assert
		assertTrue(!users.isEmpty());
	}
	

	@ParameterizedTest(name = "The sql query used to research a user by its id must match only one or no records in the database !")
	@ValueSource(ints = { 1, 10, -6, 0 })
	@Tag("UserDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getUserbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		User user = null;
		SearchUser searched_user = new SearchUser();
		
		// Act
		try {
			searched_user = searched_user.setSearchedUserID(arg1);
			user = userDAO.getUserByID(searched_user);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(user == null);
			return;
		}
		System.out.println(user);
		
		// Assert
		assertTrue(user != null);
	}	
	
	
	@ParameterizedTest(name = "The sql query used to research users from both its names must be able to return any macthing records in the database !")
	@CsvSource({ "thom,bou", "tev,ob", ",Jo", " , " })
	@Tag("UserDAOImpl-findFrom_Names")
	public void validBehaviorOf_getUsersFromNames(String arg1, String arg2) {
		// Arrange
		System.out.println("FindFromNames");
		System.out.println("firstname : ["+arg1+"]");
		System.out.println("lastname : ["+arg2+"]");
		List<User> users = null;
		SearchUser searched_user = new SearchUser();
		
		// Act
		try {
			searched_user = searched_user.setSearchedNames(arg1,arg2);
			users = userDAO.getUsersFromNames(searched_user);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(users == null);
			return;
		}
		System.out.println(users);
		
		// Assert
		assertTrue(users != null);
	}
	

	@ParameterizedTest(name = "The sql query used to create an user by its id must successfully add a new record with the highest id in the database !")
	@CsvSource({ "1,'Bill','Gates'", "2,'Jesus',", "0,,", "0,'Bill','Gates'" })
	@Order(1)
	@Tag("UserDAOImpl-createUser")
	public void validBehaviorOf_createUser(int arg1, String arg2, String arg3) {
		// Arrange
		System.out.println("createUser(id,firstname,lastname)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("firstname : ["+arg2+"]");
		System.out.println("lastname : ["+arg3+"]");
		User user = null;
		int newid = -1;
		
		// Act
		try {
			if(arg1 > 0) user = new User(arg1,arg2,arg3);
			else user = new User(arg2,arg3);
			newid = userDAO.createUser(user);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newUserid = newid;
		
		// Assert
		assertTrue(newid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update an user by its id must successfully match only one record in the database !")
	@CsvSource({ "-2,'Hillary','Clinton'", "0,,", "0,,'Clinton'" })
	@Order(2)
	@Tag("UserDAOImpl-updateUser")
	public void validBehaviorOf_updateUser(int arg1, String arg2, String arg3) {
		// Arrange
		System.out.println("updateUser(id,firstname,lastname)");
		if(arg1 == 0) arg1 = newUserid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("firstname : ["+arg2+"]");
		System.out.println("lastname : ["+arg3+"]");
		SearchUser search_user = new SearchUser();
		int id = -1;
		
		// Act
		try {
			search_user.setSearchedUserID(arg1);
			if(arg2 != null) search_user.setSearchedFirstname(arg2);
			if(arg3 != null) search_user.setSearchedLastname(arg3);
			id = userDAO.updateUser(search_user);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newUserid);
	}
	
	
	@Test
	@Order(4)
	@Tag("UserDAOImpl-deleteUser")
	public void validBehaviorOf_deleteUser() {
		// Arrange
		System.out.println("deleteUser(id)");
		System.out.println("id : ["+newUserid+"]");
		SearchUser search_user = new SearchUser();
		int id = -1;
		
		// Act
		try {
			search_user.setSearchedUserID(newUserid);
			id = userDAO.deleteUser(search_user);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newUserid);
	}
}
