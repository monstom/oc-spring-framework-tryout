package ticket.consumer.dao.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import ticket.consumer.dao.impl.bug.BugDAOImpl;
import ticket.consumer.dao.impl.bug.BugVersionAssociationDAOImpl;
import ticket.consumer.dao.impl.bug.SeverityDAOImpl;
import ticket.consumer.dao.impl.evolution.EvolutionDAOImpl;
import ticket.consumer.dao.impl.project.ProjectDAOImpl;
import ticket.consumer.dao.impl.project.VersionDAOImpl;
import ticket.consumer.dao.impl.ticket.CommentDAOImpl;
import ticket.consumer.dao.impl.ticket.HistoryStatutDAOImpl;
import ticket.consumer.dao.impl.ticket.StatutDAOImpl;
import ticket.consumer.dao.impl.ticket.TicketAssociationDAOImpl;
import ticket.consumer.dao.impl.ticket.TicketDAOImpl;
import ticket.consumer.dao.impl.user.UserDAOImpl;


@Tag("DAOFactoryImplClass_UniteTesting")
public class DAOFactoryImplTest {

	private DAOFactoryImpl daoFactory; 
	
	@BeforeEach
	public void init_DAOFactoryImpl() {
		System.out.println("DAOFactoryImplTest - Appel avant chaque test");
		daoFactory = new DAOFactoryImpl();
	}
	
	@AfterEach
	public void end_DAOFactoryImpl() {
		daoFactory = null;
		System.out.println("DAOFactoryImplTest - Appel après chaque test");
	}
	
	@BeforeAll 
	public static void init_DAOFactoryImplTest() {
		System.out.println("DAOFactoryImplTest - Début du test de classe DAOFactoryImpl");
	}
	
	@AfterAll 
	public static void end_DAOFactoryImplTest() {
		System.out.println("DAOFactoryImplTest - Fin du test de classe DAOFactoryImpl");
	}
	
	
	@Test
	@Tag("DAOFactoryImpl-instance_valid")
	public void validInstanceOf_DAOFactoryImpl() {
		// Arrange
		
		// Act
		
		// Assert
		assertTrue(daoFactory instanceof DAOFactoryImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_userDAO")
	public void validBehaviorOfAttribute_userDAO() {
		// Arrange
		
		// Act
		daoFactory.setUserDAO(new UserDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getUserDAO() instanceof UserDAOImpl);
	}
	
	
	@Test
	@Tag("DAOFactoryImpl-valid_projectDAO")
	public void validBehaviorOfAttribute_projectDAO() {
		// Arrange
		
		// Act
		daoFactory.setProjectDAO(new ProjectDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getProjectDAO() instanceof ProjectDAOImpl);
	}
	
	
	@Test
	@Tag("DAOFactoryImpl-valid_versionDAO")
	public void validBehaviorOfAttribute_versionDAO() {
		// Arrange
		
		// Act
		daoFactory.setVersionDAO(new VersionDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getVersionDAO() instanceof VersionDAOImpl);
	}
	
	
	@Test
	@Tag("DAOFactoryImpl-valid_statutDAO")
	public void validBehaviorOfAttribute_StatutDAO() {
		// Arrange
		
		// Act
		daoFactory.setStatutDAO(new StatutDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getStatutDAO() instanceof StatutDAOImpl);
	}
	
	

	@Test
	@Tag("DAOFactoryImpl-valid_ticketDAO")
	public void validBehaviorOfAttribute_ticketDAO() {
		// Arrange
		
		// Act
		daoFactory.setTicketDAO(new TicketDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getTicketDAO() instanceof TicketDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_ticketassoDAO")
	public void validBehaviorOfAttribute_ticketassoDAO() {
		// Arrange
		
		// Act
		daoFactory.setTicketAssociationDAO(new TicketAssociationDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getTicketAssociationDAO() instanceof TicketAssociationDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_commentDAO")
	public void validBehaviorOfAttribute_commentDAO() {
		// Arrange
		
		// Act
		daoFactory.setCommentDAO(new CommentDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getCommentDAO() instanceof CommentDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_historyDAO")
	public void validBehaviorOfAttribute_historyDAO() {
		// Arrange
		
		// Act
		daoFactory.setHistoryStatutDAO(new HistoryStatutDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getHistoryStatutDAO() instanceof HistoryStatutDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_evolutionDAO")
	public void validBehaviorOfAttribute_evolutionDAO() {
		// Arrange
		
		// Act
		daoFactory.setEvolutionDAO(new EvolutionDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getEvolutionDAO() instanceof EvolutionDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_severityDAO")
	public void validBehaviorOfAttribute_severityDAO() {
		// Arrange
		
		// Act
		daoFactory.setSeverityDAO(new SeverityDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getSeverityDAO() instanceof SeverityDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_bugDAO")
	public void validBehaviorOfAttribute_bugDAO() {
		// Arrange
		
		// Act
		daoFactory.setBugDAO(new BugDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getBugDAO() instanceof BugDAOImpl);
	}
	

	@Test
	@Tag("DAOFactoryImpl-valid_bugversionDAO")
	public void validBehaviorOfAttribute_bugversionDAO() {
		// Arrange
		
		// Act
		daoFactory.setBugVersionAssociationDAO(new BugVersionAssociationDAOImpl());
		
		// Assert
		assertTrue(daoFactory.getBugVersionAssociationDAO() instanceof BugVersionAssociationDAOImpl);
	}

}
