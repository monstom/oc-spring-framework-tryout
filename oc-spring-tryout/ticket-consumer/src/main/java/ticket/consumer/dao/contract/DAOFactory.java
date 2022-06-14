package ticket.consumer.dao.contract;

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

public interface DAOFactory {

	public UserDAO getUserDAO();
	public ProjectDAO getProjectDAO();
	public VersionDAO getVersionDAO();
	public StatutDAO getStatutDAO();
	public TicketDAO getTicketDAO();
	public TicketAssociationDAO getTicketAssociationDAO();
	public CommentDAO getCommentDAO();
	public HistoryStatutDAO getHistoryStatutDAO();
	public EvolutionDAO getEvolutionDAO();
	public SeverityDAO getSeverityDAO();
	public BugDAO getBugDAO();
	public BugVersionAssociationDAO getBugVersionAssociationDAO();
	
	public void setUserDAO(UserDAO userdao);
	public void setProjectDAO(ProjectDAO projectdao);
	public void setVersionDAO(VersionDAO versiondao);
	public void setStatutDAO(StatutDAO statutdao);
	public void setTicketDAO(TicketDAO ticketdao);
	public void setTicketAssociationDAO(TicketAssociationDAO ticketassodao);
	public void setCommentDAO(CommentDAO commentdao);
	public void setHistoryStatutDAO(HistoryStatutDAO historydao);
	public void setEvolutionDAO(EvolutionDAO evolutiondao);
	public void setSeverityDAO(SeverityDAO severitydao);
	public void setBugDAO(BugDAO bugdao);
	public void setBugVersionAssociationDAO(BugVersionAssociationDAO bugversiondao);

}
