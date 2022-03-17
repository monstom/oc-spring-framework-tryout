package ticket.model.beans.projects;

import java.util.Date;

public class Project {
	
	private int id_project;
	
	private String title;
	private Date creation_date;
	private boolean closed; 
	private int id_manager;
	
	Project() {}
	
	public Project(int id, String pname, Date cdate, boolean done, int managerID) {
		this.setID_manager(id);
		this.setTitle(pname);
		this.setCreation_date(cdate);
		this.setClosed(done);
		this.setID_manager(managerID);
	}
	
	public Project(int id, String pname, Date cdate, boolean done) {
		this.setID_manager(id);
		this.setTitle(pname);
		this.setCreation_date(cdate);
		this.setClosed(done);
	}
	
	public Project(String pname, Date cdate, boolean done) {
		this.setTitle(pname);
		this.setCreation_date(cdate);
		this.setClosed(done);
	}
	
	public int getID_project() {
		return id_project;
	}
	
	public void setID_project(int project_ID) {
		this.id_project = project_ID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String project_name) {
		this.title = project_name;
	}
	
	public Date getCreation_date() {
		return creation_date;
	}
	
	public void setCreation_date(Date creation_time) {
		this.creation_date = creation_time;
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public int getID_manager() {
		return id_manager;
	}

	public void setID_manager(int manager_ID) {
		this.id_manager = manager_ID;
	}
	
	public String toString() {
		return this.title + "\n" + this.creation_date + "\n" + this.closed + "\n" + this.id_manager;
	}
	
}
	
