package ticket.consumer.dao.impl.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.user.UserDAO;
import ticket.model.bean.user.User;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.user.SearchUser;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
	
	public UserDAOImpl() {
		super();
	}
	
	
	@Override
	public List<User> getAllUsers() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<User> users = null;
		
		String query = "SELECT * FROM Employee";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The user table in the database is empty : no rows found !");
		
		users = new ArrayList<User>();
		while(!results.isAfterLast()) {
			users.add(new User(results.getInt("id"),results.getString("firstname"),results.getString("lastname")));
			results.next();
		}
		return users;
	}

	
	@Override
	public User getUserByID(SearchUser searched_user) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		User userbyID = null;
		if(!(searched_user.getSearchedUserID() > 0) || searched_user == null)
			throw new SQLException("The research object or its id must not be undefined");
		
		int searched_id = searched_user.getSearchedUserID();
		String query = "SELECT * FROM Employee WHERE id=? ";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, searched_id);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched user doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched user exists in multiples copies in the database !");
		else userbyID = new User(searched_id,results.getString("firstname"),results.getString("lastname"));
		
		return userbyID;
	}
	
	
	@Override
	public List<User> getUsersFromNames(SearchUser searched_user) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		List<User> usersbyNames = null;
		if(searched_user == null || (searched_user.getSearchedFirstname() == null && searched_user.getSearchedLastname() == null))
			throw new SQLException("The research object or both its names must not be undefined");
		
		String firstname = null;
		String lastname = null;
		int nbParam = 0;
		String query = "SELECT * FROM Employee WHERE ";
		
		if(searched_user.getSearchedFirstname() != null) {
			firstname = searched_user.getSearchedFirstname();
			query += "firstname LIKE ?";
			nbParam++;
		}
		
		if(searched_user.getSearchedLastname() != null) {
			lastname = searched_user.getSearchedLastname();
			if(query.contains("firstname")) query += " AND ";
			query += "lastname LIKE ?";
			nbParam++;
		}

		PreparedStatement statement = this.getConnection().prepareStatement(query);
		if(nbParam == 1)			
			if(query.contains("firstname")) 
				statement.setString(1, "%"+firstname+"%");
			else
				statement.setString(1, "%"+lastname+"%");
		
		if(nbParam == 2) {
			statement.setString(1, "%"+firstname+"%");
			statement.setString(2, "%"+lastname+"%");
		}
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		if(!results.next())
			throw new ElementNotFoundException("The researched users from both names doesn't match to any element in the database !");
		
		usersbyNames = new ArrayList<User>();
		while(!results.isAfterLast()) {
			usersbyNames.add(new User(results.getInt("id"),results.getString("firstname"),results.getString("lastname")));
			results.next();
		}
		return usersbyNames;
	}
	

	@Override
	public int createUser(User new_user) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_user == null) 
			throw new SQLException("The new user object to be created must be undefined !");
		
		String fname = new_user.getUser_firstname();
		String lname = new_user.getUser_lastname();
		int uid = 0;		
		if(new_user.getUserID() > 0) uid = new_user.getUserID();
		
		String query = "INSERT INTO Employee";
		if(uid > 0) query += " VALUES (?,?,?)";
		else query += "(firstname,lastname) VALUES (?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(uid > 0) {
			statement.setInt(1, uid);
			statement.setString(2, fname);
			statement.setString(3, lname);
		} else {
			statement.setString(1, fname);
			statement.setString(2, lname);
		}
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		if(uid > 0) result = uid;
		else result = this.getLastInsertID();
		return result;
	}
	

	@Override
	public int updateUser(SearchUser search_user) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_user == null || !(search_user.getSearchedUserID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int uid = search_user.getSearchedUserID();
		String firstname = null;
		String lastname = null;
		int nbParam = 0;
		String query = "UPDATE Employee SET ";
		
		if(search_user.getSearchedFirstname() != null) { 
			firstname = search_user.getSearchedFirstname();
			query += "firstname=?";
			nbParam++;
		}
		if(search_user.getSearchedLastname() != null) {
			lastname = search_user.getSearchedLastname();
			if(nbParam > 0) query += " AND ";
			query += "lastname=?";
			nbParam++;
		}
		
		if(nbParam == 0)
			throw new SQLException("The fields of the user to be updated must not be all undefined");			
		
		query += " WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(nbParam == 1)			
			if(query.contains("firstname")) 
				statement.setString(1, "%"+firstname+"%");
			else
				statement.setString(1, "%"+lastname+"%");
			statement.setInt(2, uid);
		
		if(nbParam == 2) {
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setInt(3, uid);
		}
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = uid;
		return result;
	}

	@Override
	public int deleteUser(SearchUser search_user) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_user == null || !(search_user.getSearchedUserID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int uid = search_user.getSearchedUserID();
		String query = "DELETE Employee WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, uid);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = uid;
		return result;
	}


	@Override
	public int getLastInsertID() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		int lastid = -1;
		String query = "SELECT id FROM Employee ORDER BY DESC id LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		lastid = results.getInt("id");
		return lastid;
	}

}
