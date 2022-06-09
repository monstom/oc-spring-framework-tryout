package ticket.model.bean.ticket;

import org.apache.commons.validator.GenericValidator;

import ticket.model.exception.InvalidAttributeLengthException;
import ticket.model.exception.InvalidAttributeNumericValueException;

public class Statut {
	
	//private static final Logger logger = LoggerFactory.getLogger(Statut.class);
	
	private int id_statut;
	private String label;
	
	protected Statut() {}
	
	public Statut(int id, String slabel) {
		try {
			this.setStatutID(id);
			this.setStatut_label(slabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("status bean successfully created/retrieved with id : "+id+" !");		
	}
	
	public Statut(int id) {
		try {
			this.setStatutID(id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("status bean successfully created/retrieved by its id : "+id+" !");		
	}
	
	public Statut(String slabel) {
		try {
			this.setStatut_label(slabel);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			//logger.error(e.getMessage());
		}
		//logger.info("anonymous status bean successfully created/retrieved !");		
	}

	public int getStatutID() {
		return this.id_statut;
	}

	public void setStatutID(int statut_id) throws InvalidAttributeNumericValueException {
		if(GenericValidator.maxValue(statut_id,0)) 
			throw new InvalidAttributeNumericValueException("The key identifying a status must not be negative or equal 0 !");
		else this.id_statut = statut_id;
	}

	public String getStatut_label() {
		return this.label;
	}

	public void setStatut_label(String slabel) throws InvalidAttributeLengthException {
		if(GenericValidator.isBlankOrNull(slabel)) 
			throw new InvalidAttributeLengthException("The label of a status must not be empty or blank !");
		else if(GenericValidator.minLength(slabel,100)) 
			throw new InvalidAttributeLengthException("The label of a status must not contains more than 100 characters !");
		else this.label = slabel;
	}
	
	public String toString() {
		return "--- Statut Object ---\n id : "+ this.id_statut +"\n label : "+ this.label +"\n";
	}
	
	
}
