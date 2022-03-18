package ticket.model.beans.bugs;

public class Severity {
	
	private int id_severity;
	
	private int level;
	private String label;
	
	protected Severity() {}
	
	public Severity(int id, int slevel, String slabel) {
		this.setSeverityID(id);
		this.setSeverity_level(slevel);
		this.setSeverity_label(slabel);
	}

	public int getSeverityID() {
		return this.id_severity;
	}

	public void setSeverityID(int severity_id) {
		this.id_severity = severity_id;
	}

	public int getSeverity_level() {
		return this.level;
	}

	public void setSeverity_level(int slevel) {
		this.level = slevel;
	}

	public String getSeverity_label() {
		return this.label;
	}

	public void setSeverity_label(String slabel) {
		this.label = slabel;
	}
}
