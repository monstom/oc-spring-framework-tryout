package ticket.model.bean.bug;

import java.util.Date;

import ticket.model.bean.ticket.Ticket;

public class Bug extends Ticket {
	
	private int severity;
	

	protected Bug() {
		super();
	}

	public Bug(int id, String tname, Date cdate, String desc, int statut, int author, int project, int level) {
		super(id, tname, cdate, desc, statut, author, project);
		this.setBug_severity(level);
	}

	public int getBug_severity() {
		return this.severity;
	}

	public void setBug_severity(int severity) {
		this.severity = severity;
	}
	
}
