package ticket.model.beans.tickets;

public class Statut {
	
	private int id_statut;
	private String label;
	
	Statut() {}
	
	public Statut(int id, String slabel) {
		this(slabel);
		this.setStatutID(id);
	}
	
	public Statut(int id) {
		this("");
		this.setStatutID(id);
	}
	
	public Statut(String slabel) {
		this.setStatutID(0);
		this.setStatut_label(slabel);
	}

	public int getStatutID() {
		return id_statut;
	}

	public void setStatutID(int statut_id) {
		this.id_statut = statut_id;
	}

	public String getStatut_label() {
		return label;
	}

	public void setStatut_label(String slabel) {
		this.label = slabel;
	}
	
	
}
