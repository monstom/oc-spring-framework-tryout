package ticket.model.beans.projects;

import java.util.Date;

public class Project {
	
	private int id_project;
	
	private String title;
	private Date date_creation;
	private boolean closed; 
	private int id_manager;
	
	Project() {}
	
	public Project(int id, String pname, Date cdate, boolean done, int managerID) {
		this(id,pname,cdate,done);
		this.setProject_managerID(managerID);
	}
	
	public Project(int id, String pname, Date cdate, boolean done) {
		this(pname,cdate,done);
		this.setProjectID(id);
	}
	
	public Project(String pname, Date cdate, boolean done) {
		this.setProjectID(0);		
		this.setProject_title(pname);
		this.setProject_creationDate(cdate);
		this.setProject_isClosed(done);
		this.setProject_managerID(0);
	}
	
	public int getProjectID() {
		return this.id_project;
	}
	
	public void setProjectID(int project_ID) {
		this.id_project = project_ID;
	}
	
	public String getProject_title() {
		return this.title;
	}
	
	public void setProject_title(String project_name) {
		this.title = project_name;
	}
	
	public Date getProject_creationDate() {
		return this.date_creation;
	}
	
	public void setProject_creationDate(Date creation_time) {
		this.date_creation = creation_time;
	}
	
	public boolean getProject_isClosed() {
		return this.closed;
	}
	
	public void setProject_isClosed(boolean closed) {
		this.closed = closed;
	}

	public int getProject_managerID() {
		return this.id_manager;
	}

	public void setProject_managerID(int manager_ID) {
		this.id_manager = manager_ID;
	}
	
	public String toString() {
		return this.title + "\n" + this.date_creation.toString() + "\n" + this.closed + "\n" + this.id_manager;
	}
	
}
	
