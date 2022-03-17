package ticket.model.beans.projects;

public class Version {

	private int project_id;
	private String version_id;
	
	Version() {}
	
	public Version(int id_project, String id_version) {
		this.setProjectID(id_project);
		this.setVersionID(id_version);
	}

	public int getProjectID() {
		return project_id;
	}

	public void setProjectID(int project_id) {
		this.project_id = project_id;
	}

	public String getVersionID() {
		return version_id;
	}

	public void setVersionID(String version_id) {
		this.version_id = version_id;
	}
	
}
