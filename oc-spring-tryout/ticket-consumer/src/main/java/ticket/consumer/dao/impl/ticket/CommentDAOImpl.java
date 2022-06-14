package ticket.consumer.dao.impl.ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ticket.consumer.dao.AbstractDAO;
import ticket.consumer.dao.contract.ticket.CommentDAO;
import ticket.model.bean.ticket.Comment;
import ticket.model.exception.ElementNotFoundException;
import ticket.model.search.ticket.SearchComment;

public class CommentDAOImpl extends AbstractDAO implements CommentDAO {

	public CommentDAOImpl() {
		super();
	}	
	
	
	@Override
	public List<Comment> getAllComments() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Comment> comments = null;
		
		String query = "SELECT * FROM Comment";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The comment table in the database is empty : no rows found !");
		
		comments = new ArrayList<Comment>();
		while(!results.isAfterLast()) {
			comments.add(new Comment(results.getInt("id"),
									 results.getString("description"),
									 results.getInt("author_id"),
									 results.getInt("ticket_id")));
			results.next();
		}
		return comments;
	}

	
	@Override
	public Comment getCommentByID(SearchComment search_comment) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		Comment commentbyID = null;
		if(search_comment == null || !(search_comment.getSearchedCommentID() > 0))
			throw new SQLException("The research object and its id must not be undefined");
		
		int cid = search_comment.getSearchedCommentID();
		String query = "SELECT * FROM Comment WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, cid);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comment doesn't exist in the database !");
		else if(!results.isLast())
			throw new ElementNotFoundException("The researched comment exists in multiples copies in the database !");
		else commentbyID = new Comment(cid,results.getString("description"),results.getInt("author_id"),results.getInt("ticket_id") );
		
		return commentbyID;
	}

	
	@Override
	public List<Comment> getCommentsLike_description(SearchComment search_comment) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Comment> comments = null;
		if(search_comment == null || search_comment.getSearchedDescription() == null)
			throw new SQLException("The research object or its description must not be undefined");
		
		String desc = search_comment.getSearchedDescription();
		String query = "SELECT * FROM Comment WHERE description LIKE ?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setString(1, "%"+desc+"%");
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comments from description doesn't match to any element in the database !");
		
		comments = new ArrayList<Comment>();
		while(!results.isAfterLast()) {
			comments.add(new Comment(results.getInt("id"),
									 results.getString("description"),
									 results.getInt("author_id"),
									 results.getInt("ticket_id")));
			results.next();
		}
		return comments;
	}

	
	@Override
	public List<Comment> getCommentsFrom_ticket(SearchComment search_comment) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Comment> comments = null;
		if(search_comment == null || !(search_comment.getSearchedTicket() > 0))
			throw new SQLException("The research object or its ticket must not be undefined");
		
		int ticket = search_comment.getSearchedTicket();
		String query = "SELECT * FROM Comment WHERE ticket_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, ticket);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comments from ticket doesn't match to any element in the database !");
		
		comments = new ArrayList<Comment>();
		while(!results.isAfterLast()) {
			comments.add(new Comment(results.getInt("id"),
									 results.getString("description"),
									 results.getInt("author_id"),
									 ticket));
			results.next();
		}
		return comments;
	}

	
	@Override
	public List<Comment> getCommentsFrom_author(SearchComment search_comment) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		ArrayList<Comment> comments = null;
		if(search_comment == null || !(search_comment.getSearchedAuthor() > 0))
			throw new SQLException("The research object or its author must not be undefined");
		
		int author = search_comment.getSearchedAuthor();
		String query = "SELECT * FROM Comment WHERE author_id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, author);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comments from author doesn't match to any element in the database !");
		
		comments = new ArrayList<Comment>();
		while(!results.isAfterLast()) {
			comments.add(new Comment(results.getInt("id"),
									 results.getString("description"),
									 author,
									 results.getInt("ticket_id")));
			results.next();
		}
		return comments;
	}
	
	
	@Override
	public List<Comment> getComments_builder(SearchComment search_comment) throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_comment == null)
			throw new SQLException("The research object of the comment query builder must not be undefined");
		
		List<Comment> comments = null;
		int ticket = 0;
		int author = 0;
		String desc = null;
		String query = "SELECT * FROM Comment WHERE ";
		int nbParam = 0;
		
		if(search_comment.getSearchedDescription() != null) {
			desc = search_comment.getSearchedDescription();
			query += "description LIKE ?";
			nbParam++;
		}
		
		if(search_comment.getSearchedAuthor() > 0) {
			author = search_comment.getSearchedAuthor();
			if(nbParam > 0) query += " AND ";
			query += "author_id=?";
			nbParam++;
		}
		
		if(search_comment.getSearchedTicket() > 0) {
			ticket = search_comment.getSearchedTicket();
			if(nbParam > 0) query += " AND ";
			query += "ticket_id=?";
			nbParam++;
		}
		
		if(nbParam == 0) 
			query = query.replace(" WHERE ", "");
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(desc != null)
			statement.setString(1, "%"+desc+"%");
		
		if(author > 0)
			if(nbParam == 1) 
				statement.setInt(1, author);
			else if(nbParam == 3)
				statement.setInt(2, author);
			else if(nbParam == 2 && desc == null) 
				statement.setInt(1, author);
			else
				statement.setInt(2, author);
		
		if(ticket > 0)
			if(nbParam == 1)
				statement.setInt(1, ticket);
			else if(nbParam == 2)
				statement.setInt(2, ticket);
			else if(nbParam == 3)
				statement.setInt(3, ticket);
		
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		
		if(!results.next())
			throw new ElementNotFoundException("The researched comments from query builder doesn't match to any element in the database !");
		
		comments = new ArrayList<Comment>();
		while(!results.isAfterLast()) {
			comments.add(new Comment(results.getInt("id"),
									 results.getString("description"),
									 results.getInt("author_id"),
									 results.getInt("ticket_id")));
			results.next();
		}
		return comments;
	}
	

	@Override
	public int createComment(Comment new_comment) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(new_comment == null) 
			throw new SQLException("The new comment object to be created must be undefined !");
		
		String desc = new_comment.getComment_description();
		int ticket = new_comment.getComment_ticketID();
		int author = new_comment.getComment_userID();
		int cid = 0;		
		if(new_comment.getCommentID() > 0) cid = new_comment.getCommentID();
		
		String query = "INSERT INTO Comment";
		if(cid > 0) query += " VALUES (?,?,?,?)";
		else query += "(description,author_id,ticket_id) VALUES (?,?,?)";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(cid > 0) {
			statement.setInt(1, cid);
			statement.setString(2, desc);
			statement.setInt(3, ticket);
			statement.setInt(4, author);
		} else {
			statement.setString(1, desc);
			statement.setInt(2, ticket);
			statement.setInt(3, author);
		}
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		if(cid > 0) result = cid;
		else result = this.getLastInsertID();
		return result;
	}
	

	@Override
	public int updateComment(SearchComment search_comment) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_comment == null || !(search_comment.getSearchedCommentID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
		
		int cid = search_comment.getSearchedCommentID();
		int ticket = 0;
		int author = 0;
		String desc = null;
		String query = "UPDATE Comment SET ";
		int nbParam = 0;
		
		if(search_comment.getSearchedDescription() != null) {
			desc = search_comment.getSearchedDescription();
			query += "description LIKE ?";
			nbParam++;
		}
		
		if(search_comment.getSearchedAuthor() > 0) {
			author = search_comment.getSearchedAuthor();
			if(nbParam > 0) query += " AND ";
			query += "author_id=?";
			nbParam++;
		}
		
		if(search_comment.getSearchedTicket() > 0) {
			ticket = search_comment.getSearchedTicket();
			if(nbParam > 0) query += " AND ";
			query += "ticket_id=?";
			nbParam++;
		}
		
		if(nbParam == 0) 
			throw new SQLException("The fields of the comment to be updated must not be all undefined");			
			
		query += " WHERE id=?"; 
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		
		if(desc != null)
			statement.setString(1, "%"+desc+"%");
		
		if(author > 0)
			if(nbParam == 1) 
				statement.setInt(1, author);
			else if(nbParam == 3)
				statement.setInt(2, author);
			else if(nbParam == 2 && desc == null) 
				statement.setInt(1, author);
			else
				statement.setInt(2, author);
		
		if(ticket > 0)
			if(nbParam == 1)
				statement.setInt(1, ticket);
			else if(nbParam == 2)
				statement.setInt(2, ticket);
			else if(nbParam == 3)
				statement.setInt(3, ticket);
		
		if(nbParam == 1)
			statement.setInt(2, cid);
		else if(nbParam == 2)
			statement.setInt(3, cid);
		else if(nbParam == 3)
			statement.setInt(4, cid);
		
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = cid;
		return result;
	}
	

	@Override
	public int deleteComment(SearchComment search_comment) throws SQLException {
		int result = -1;
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		if(search_comment == null || !(search_comment.getSearchedCommentID() > 0))
			throw new SQLException("The research object or its identifiers must not be undefined");
	
		int cid = search_comment.getSearchedCommentID();
		String query = "DELETE Comment WHERE id=?";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setInt(1, cid);
		statement.setQueryTimeout(1);
		statement.executeQuery();
		
		result = cid;
		return result;
	}


	@Override
	public int getLastInsertID() throws SQLException {
		if(this.getConnection().isClosed())
			throw new SQLException("This DAO Object is not yet connected to the database");
		
		int lastid = -1;
		String query = "SELECT id FROM Comment ORDER BY DESC id LIMIT 1";
		PreparedStatement statement = this.getConnection().prepareStatement(query);
		statement.setQueryTimeout(1);
		ResultSet results = statement.executeQuery();
		lastid = results.getInt("id");
		return lastid;
	}

}
