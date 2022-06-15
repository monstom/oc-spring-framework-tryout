package ticket.consumer.dao.impl.ticket;

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
import ticket.model.bean.ticket.Comment;
import ticket.model.search.ticket.SearchComment;


@Tag("CommentDAOImplClass_UniteTesting")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentDAOImplTest {

	private CommentDAOImpl commentDAO;
	private static int newCommentid;
	
	@BeforeEach
	public void init_CommentDAO() {
		System.out.println("CommentDAOImplTest - Appel avant chaque test");
		DatabaseConnection db_con = new DatabaseConnectionFactoryImpl().getJDBCConnector(DatabaseConnectionTypes.MYSQL);
		AbstractDAO.setConnection(db_con.getConnection());
		commentDAO = new CommentDAOImpl();
	}
	
	@AfterEach
	public void end_CommentDAO() {
		commentDAO = null;
		System.out.println("CommentDAOImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_CommentDAOTest() {
		newCommentid = -1;
		System.out.println("CommentDAOImplTest - Début du test de classe CommentDAOImpl");
	}
	
	@AfterAll 
	public static void end_CommentDAOTest() {
		newCommentid = -1;
		System.out.println("CommentDAOImplTest - Fin du test de classe CommentDAOImpl");
	}
	
	
	@Test
	@Tag("CommentDAOImpl-instance_valid")
	public void validInstanceOf_CommentDAOImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(commentDAO instanceof CommentDAOImpl && commentDAO != null);
	}
	
	
	@Test
	@Order(3)
	@Tag("CommentDAOImpl-find_allComments")
	public void validBehaviorOf_getAllComments() {
		// Arrange
		System.out.println("FindAllComments");
		List<Comment> comments = null;
		
		// Act
		try {
			comments = commentDAO.getAllComments();
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(comments == null);
			return;
		}
		System.out.println(comments);
		
		// Assert
		assertTrue(!comments.isEmpty());
	}
	
	
	@ParameterizedTest(name = "The sql query used to research a comment by its id must match only one or no records in the database !")
	@ValueSource(ints = { 2, 1, -6, 0, 10 })
	@Tag("CommentDAOImpl-findby_PrimaryKey")
	public void validBehaviorOf_getCommentbyID(int arg1) {
		// Arrange
		System.out.println("FindByID");
		System.out.println("id : ["+arg1+"]");
		Comment comment = null;
		SearchComment searched_comment = new SearchComment();
		
		// Act
		try {
			searched_comment = searched_comment.setSearchedCommentID(arg1);
			comment = commentDAO.getCommentByID(searched_comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(comment == null);
			return;
		}
		System.out.println(comment);
		
		// Assert
		assertTrue(comment != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research comments from its description must be able to return any macthing records in the database !")
	@ValueSource(strings = { "require", "issue", "PEER", "", "errors" })
	@Tag("CommentDAOImpl-findLike_Description")
	public void validBehaviorOf_getCommentsLikeDescription(String arg1) {
		// Arrange
		System.out.println("FindFromDescription");
		System.out.println("description : ["+arg1+"]");
		List<Comment> comments = null;
		SearchComment searched_comment = new SearchComment();
		
		// Act
		try {
			searched_comment = searched_comment.setSearchedDescription(arg1);
			comments = commentDAO.getCommentsLike_description(searched_comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(comments == null);
			return;
		}
		System.out.println(comments);
		
		// Assert
		assertTrue(comments != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research comments from its author id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 5, 1, -6, 0, 10 })
	@Tag("CommentDAOImpl-findFrom_Author")
	public void validBehaviorOf_getCommentsFromAuthor(int arg1) {
		// Arrange
		System.out.println("FindFromAuthor");
		System.out.println("author_id : ["+arg1+"]");
		List<Comment> comments = null;
		SearchComment searched_comment = new SearchComment();
		
		// Act
		try {
			searched_comment = searched_comment.setSearchedAuthor(arg1);
			comments = commentDAO.getCommentsFrom_author(searched_comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(comments == null);
			return;
		}
		System.out.println(comments);
		
		// Assert
		assertTrue(comments != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to research comments by its ticket id must be able to return any macthing records in the database !")
	@ValueSource(ints = { 2, 1, -6, 0, 10 })
	@Tag("CommentDAOImpl-findFrom_Ticket")
	public void validBehaviorOf_getCommentsFromTicket(int arg1) {
		// Arrange
		System.out.println("FindFromTicket");
		System.out.println("ticket_id : ["+arg1+"]");
		List<Comment> comments = null;
		SearchComment searched_comment = new SearchComment();
		
		// Act
		try {
			searched_comment = searched_comment.setSearchedTicket(arg1);
			comments = commentDAO.getCommentsFrom_ticket(searched_comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(comments == null);
			return;
		}
		System.out.println(comments);
		
		// Assert
		assertTrue(comments != null);
	}
	
	
	@ParameterizedTest(name = "The sql query used to create a comment by its id must successfully add a new record with the highest id in the database !")
	@CsvSource({ "0,'',5,2", "0,PEER,-5,1", "0,PEER,5,-1", "1,PEER,5,1", "0,'test1',2,2" })
	@Order(1)
	@Tag("CommentDAOImpl-createComment")
	public void validBehaviorOf_createComment(int arg1, String arg2, int arg3, int arg4) {
		// Arrange
		System.out.println("createComment(id,description,author,ticket)");
		System.out.println("id : ["+arg1+"]");
		System.out.println("description : ["+arg2+"]");
		System.out.println("author : ["+arg3+"]");
		System.out.println("ticket : ["+arg4+"]");
		Comment comment = null;
		int newid = -1;
		
		// Act
		try {
			if(arg1 > 0) comment = new Comment(arg1,arg2,arg3,arg4);
			else comment = new Comment(arg2,arg3,arg4);
			newid = commentDAO.createComment(comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(newid <= 0);
			return;
		}
		System.out.println(newid);
		newCommentid = newid;
		
		// Assert
		assertTrue(newCommentid > 0);
	}
	
	
	@ParameterizedTest(name = "The sql query used to update a comment by its id must successfully match only one record in the database !")
	@CsvSource({ "0,'',-5,-2", "0,'test2',0,0" })
	@Order(2)
	@Tag("CommentDAOImpl-updateComment")
	public void validBehaviorOf_updateComment(int arg1, String arg2, int arg3, int arg4) {
		// Arrange
		System.out.println("updateComment(id,description,author,ticket)");
		if(arg1 == 0) arg1 = newCommentid;
		System.out.println("id : ["+arg1+"]");
		System.out.println("description : ["+arg2+"]");
		System.out.println("author : ["+arg3+"]");
		System.out.println("ticket : ["+arg4+"]");
		SearchComment search_comment = new SearchComment();
		int id = -1;
		
		// Act
		try {
			search_comment.setSearchedCommentID(arg1);
			if(arg2 != null) search_comment.setSearchedDescription(arg2);
			if(arg3 > 0) search_comment.setSearchedAuthor(arg3);
			if(arg4 > 0) search_comment.setSearchedTicket(arg4);
			id = commentDAO.updateComment(search_comment);
			
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newCommentid);
	}
	
	
	@Test
	@Order(4)
	@Tag("CommentDAOImpl-deleteComment")
	public void validBehaviorOf_deleteComment() {
		// Arrange
		System.out.println("deleteComment(id)");
		System.out.println("id : ["+newCommentid+"]");
		SearchComment search_comment = new SearchComment();
		int id = -1;
		
		// Act
		try {
			search_comment.setSearchedCommentID(newCommentid);
			id = commentDAO.deleteComment(search_comment);
		} catch(Exception e) {
			System.out.println(e.toString());
			assertTrue(id <= 0);
			return;
		}
		System.out.println(id);
		
		// Assert
		assertSame(id,newCommentid);
	}

}
