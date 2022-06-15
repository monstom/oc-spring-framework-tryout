package ticket.consumer.dao.contract.user;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.user.User;
import ticket.model.search.user.SearchUser;

public interface UserDAO {

	public List<User> getAllUsers() throws SQLException, Exception;
		
	public List<User> getUsersFromNames(SearchUser search_user) throws SQLException, Exception;
	
	public User getUserByID(SearchUser search_user) throws SQLException, Exception;

	public int createUser(User new_user) throws SQLException;
	
	public int updateUser(SearchUser search_user) throws SQLException;
	
//	public int desactivateUser(SearchUser search_user) throws SQLException;
	
	public int deleteUser(SearchUser search_user) throws SQLException;
	
	public int getLastInsertID() throws SQLException;
	
}
