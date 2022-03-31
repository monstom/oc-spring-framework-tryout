package ticket.model.search.bug;

public class SearchBugVersionAssociation {
	
	private int searched_bug;
	private int searched_VID;
	private String searched_vlabel;

	public SearchBugVersionAssociation() {}

	public int getSearchedBug() {
		return searched_bug;
	}

	public SearchBugVersionAssociation setSearchedBug(int searched_bug) {
		this.searched_bug = searched_bug;
		return this;
	}

	public int getSearchedVersionID() {
		return searched_VID;
	}

	public SearchBugVersionAssociation setSearchedVersionID(int searched_version) {
		this.searched_VID = searched_version;
		return this;
	}

	public String getSearchedVersionLabel() {
		return searched_vlabel;
	}

	public SearchBugVersionAssociation setSearchedVersionLabel(String searched_vlabel) {
		this.searched_vlabel = searched_vlabel;
		return this;
	}
}
