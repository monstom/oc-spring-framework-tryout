package ticket.model.beans.bugs;

public class BugVersionAssociation {
	
	private int id_bug;
	private int id_version;
	private String versionLabel;
	
	protected BugVersionAssociation() {}
	
	public BugVersionAssociation(int bugID, int versionID, String vlabel) {
		this.setBugVersionAssociation_bugID(bugID);
		this.setBugVersionAssociation_versionID(versionID);
		this.setBugVersionAssociation_versionLabel(vlabel);
	}

	public int getBugVersionAssociation_bugID() {
		return this.id_bug;
	}

	public void setBugVersionAssociation_bugID(int bugID) {
		this.id_bug = bugID;
	}

	public String getBugVersionAssociation_versionLabel() {
		return this.versionLabel;
	}

	public void setBugVersionAssociation_versionLabel(String vlabel) {
		this.versionLabel = vlabel;
	}

	public int getBugVersionAssociation_versionID() {
		return this.id_version;
	}

	public void setBugVersionAssociation_versionID(int versionID) {
		this.id_version = versionID;
	}	
}
