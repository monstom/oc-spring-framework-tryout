package ticket.consumer.dao.contract.ticket;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.ticket.Comment;
import ticket.model.search.ticket.SearchComment;

public interface CommentDAO {

	public List<Comment> getAllComments() throws SQLException;
	
	public List<Comment> getCommentsLike_description(SearchComment search_comment) throws SQLException;
	
	public List<Comment> getCommentsFrom_ticket(SearchComment search_comment) throws SQLException;
	
	public List<Comment> getCommentsFrom_author(SearchComment search_comment) throws SQLException;

	public List<Comment> getComments_builder(SearchComment search_comment) throws SQLException;
	
	public Comment getCommentByID(SearchComment search_comment) throws SQLException;
	
	public int createComment(Comment new_comment) throws SQLException;
	
	public int updateComment(SearchComment search_comment) throws SQLException;
	
	public int deleteComment(SearchComment search_comment) throws SQLException;	

	public int getLastInsertID() throws SQLException;
}
