package ticket.model.search.project;

public class SearchVersion {
	
	private int searched_versionID;
	private String searched_label;
	
	public SearchVersion() {}

	public int getSearchedVersionID() {
		return searched_versionID;
	}

	public SearchVersion setSearchedVersionID(int searched_versionID) {
		this.searched_versionID = searched_versionID;
		return this;
	}

	public String getSearchedLabel() {
		return searched_label;
	}

	public SearchVersion setSearchedLabel(String searched_label) {
		this.searched_label = searched_label;
		return this;
	}

}
