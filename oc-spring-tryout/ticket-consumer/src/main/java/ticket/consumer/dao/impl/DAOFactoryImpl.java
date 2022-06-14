package ticket.consumer.dao.impl;

import ticket.consumer.dao.contract.DAOFactory;
import ticket.consumer.dao.contract.bug.BugDAO;
import ticket.consumer.dao.contract.bug.BugVersionAssociationDAO;
import ticket.consumer.dao.contract.bug.SeverityDAO;
import ticket.consumer.dao.contract.evolution.EvolutionDAO;
import ticket.consumer.dao.contract.project.ProjectDAO;
import ticket.consumer.dao.contract.project.VersionDAO;
import ticket.consumer.dao.contract.ticket.CommentDAO;
import ticket.consumer.dao.contract.ticket.HistoryStatutDAO;
import ticket.consumer.dao.contract.ticket.StatutDAO;
import ticket.consumer.dao.contract.ticket.TicketAssociationDAO;
import ticket.consumer.dao.contract.ticket.TicketDAO;
import ticket.consumer.dao.contract.user.UserDAO;

public class DAOFactoryImpl implements DAOFactory {
	
	private UserDAO userDAO;
	private ProjectDAO projectDAO;
	private VersionDAO versionDAO;
	private StatutDAO statutDAO;
	private TicketDAO ticketDAO;
	private TicketAssociationDAO ticketassoDAO;
	private CommentDAO commentDAO;
	private HistoryStatutDAO historyDAO;
	private EvolutionDAO evolutionDAO;
	private SeverityDAO severityDAO;
	private BugDAO bugDAO;
	private BugVersionAssociationDAO bugversionDAO;

	public DAOFactoryImpl() {
		this.setUserDAO(null);
		this.setProjectDAO(null);
		this.setVersionDAO(null);
		this.setStatutDAO(null);
		this.setTicketDAO(null);
		this.setTicketAssociationDAO(null);
		this.setCommentDAO(null);
		this.setHistoryStatutDAO(null);
		this.setEvolutionDAO(null);
		this.setSeverityDAO(null);
		this.setBugDAO(null);
		this.setBugVersionAssociationDAO(null);
	}

	@Override
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	@Override
	public void setUserDAO(UserDAO userdao) {
		this.userDAO = userdao;
	}
	
	@Override
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}
	
	@Override
	public void setProjectDAO(ProjectDAO projectdao) {
		this.projectDAO = projectdao;
	}

	@Override
	public VersionDAO getVersionDAO() {
		return versionDAO;
	}

	@Override
	public void setVersionDAO(VersionDAO versiondao) {
		this.versionDAO = versiondao;
	}
	
	@Override
	public StatutDAO getStatutDAO() {
		return statutDAO;
	}

	@Override
	public void setStatutDAO(StatutDAO statutdao) {
		this.statutDAO = statutdao;
	}
	
	@Override
	public TicketDAO getTicketDAO() {
		return ticketDAO;
	}

	@Override
	public void setTicketDAO(TicketDAO ticketdao) {
		this.ticketDAO = ticketdao;
	}
	
	@Override
	public TicketAssociationDAO getTicketAssociationDAO() {
		return ticketassoDAO;
	}

	@Override
	public void setTicketAssociationDAO(TicketAssociationDAO ticketassodao) {
		this.ticketassoDAO = ticketassodao;
	}
	
	@Override
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}
	
	@Override
	public void setCommentDAO(CommentDAO commentdao) {
		this.commentDAO = commentdao;
	}
	
	@Override
	public HistoryStatutDAO getHistoryStatutDAO() {
		return historyDAO;
	}

	@Override
	public void setHistoryStatutDAO(HistoryStatutDAO historydao) {
		this.historyDAO = historydao;
	}
	
	@Override
	public EvolutionDAO getEvolutionDAO() {
		return evolutionDAO;
	}

	@Override
	public void setEvolutionDAO(EvolutionDAO evolutiondao) {
		this.evolutionDAO = evolutiondao;
	}
	
	@Override
	public SeverityDAO getSeverityDAO() {
		return severityDAO;
	}

	@Override
	public void setSeverityDAO(SeverityDAO severitydao) {
		this.severityDAO = severitydao;
	}

	@Override
	public BugDAO getBugDAO() {
		return bugDAO;
	}

	@Override
	public void setBugDAO(BugDAO bugdao) {
		this.bugDAO = bugdao;
	}
	
	@Override
	public BugVersionAssociationDAO getBugVersionAssociationDAO() {
		return bugversionDAO;
	}	

	@Override
	public void setBugVersionAssociationDAO(BugVersionAssociationDAO bugversiondao) {
		this.bugversionDAO = bugversiondao;
	}

}
