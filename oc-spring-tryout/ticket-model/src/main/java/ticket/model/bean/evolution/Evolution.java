package ticket.model.bean.evolution;

import java.util.Date;

import ticket.model.bean.ticket.Ticket;

public class Evolution extends Ticket {
	
	private int priority;
	
	protected Evolution() {
		super();
	}

	public Evolution(int id, String tname, Date cdate, String desc, int statut, int author, int project, int priority) {
		super(id, tname, cdate, desc, statut, author, project);
		this.setEvolution_priority(priority);
	}

	public int getEvolution_priority() {
		return this.priority;
	}

	public void setEvolution_priority(int epriority) {
		this.priority = epriority;
	}

}
