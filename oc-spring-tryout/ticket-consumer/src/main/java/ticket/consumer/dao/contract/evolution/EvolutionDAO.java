package ticket.consumer.dao.contract.evolution;

import java.sql.SQLException;
import java.util.List;

import ticket.model.bean.evolution.Evolution;
import ticket.model.search.evolution.SearchEvolution;

public interface EvolutionDAO {
	
	public List<Evolution> getAllEvolutions() throws SQLException, Exception;
	
	public List<Evolution> getEvolutionsFrom_priority(SearchEvolution search_evolution) throws SQLException, Exception;
	
	public Evolution getEvolutionByID(SearchEvolution search_evolution) throws SQLException, Exception;
	
	public int createEvolution(Evolution new_evolution) throws SQLException;
	
	public int updateEvolution(SearchEvolution search_evolution) throws SQLException;

	public int deleteEvolution(SearchEvolution search_evolution) throws SQLException;
}
