package drive.fitness.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.LiftingHistory;

public interface LiftingHistoryDao extends CrudRepository<LiftingHistory, Long>{ 

}
