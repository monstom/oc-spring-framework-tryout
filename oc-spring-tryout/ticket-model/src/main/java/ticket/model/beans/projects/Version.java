package ticket.model.beans.projects;

public class Version {

	private int id_project;
	private String version_label;
	
	Version() {}
	
	public Version(int project_id, String vlabel) {
		this(vlabel);
		this.setVersion_projectID(project_id);
	}
	
	public Version(String vlabel) {
		this.setVersion_projectID(0);
		this.setVersion_label(vlabel);
	}

	public int getVersion_projectID() {
		return this.id_project;
	}

	public void setVersion_projectID(int project_id) {
		this.id_project = project_id;
	}

	public String getVersion_label() {
		return this.version_label;
	}

	public void setVersion_label(String vlabel) {
		this.version_label = vlabel;
	}
	
}
