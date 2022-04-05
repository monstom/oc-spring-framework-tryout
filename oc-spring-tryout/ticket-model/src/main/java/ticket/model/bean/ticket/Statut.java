package ticket.model.bean.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Statut {
	
	private int id_statut;
	private String label;
	
	protected Statut() {}
	
	public Statut(int id, String slabel) {
		this(slabel);
		try {
			this.setStatutID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Statut(int id) {
		this("");
		try {
			this.setStatutID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Statut(String slabel) {
		try {
			this.setStatutID(0);
			this.setStatut_label(slabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int getStatutID() {
		return id_statut;
	}

	public void setStatutID(int statut_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(statut_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a status must not be negative or equal 0 !");
		else this.id_statut = statut_id;
	}

	public String getStatut_label() {
		return label;
	}

	public void setStatut_label(String slabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(slabel)) 
			throw new InvalidAttributeLengthException("The title of a project must not be empty or blank !");
		else if(GenericValidator.minLength(slabel,100)) 
			throw new InvalidAttributeLengthException("The title of a project must not contains more than 100 characters !");
		else this.label = slabel;
	}
	
	
}
