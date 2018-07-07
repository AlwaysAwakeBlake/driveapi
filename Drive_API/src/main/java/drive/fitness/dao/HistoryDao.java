package drive.fitness.dao;

import org.springframework.data.repository.CrudRepository;
import drive.fitness.models.History;

public interface HistoryDao extends CrudRepository<History, Integer>{	
	
}
