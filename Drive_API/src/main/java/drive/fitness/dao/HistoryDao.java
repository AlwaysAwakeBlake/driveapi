package drive.fitness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.BodyLift;
import drive.fitness.models.Flexibility;
import drive.fitness.models.History;

public interface HistoryDao extends CrudRepository<History, Integer>{

	@Procedure(name = "get_body_lift_history_by_exercise")
	List<BodyLift> getBodyLiftHistoryByExercise(@Param("userId") int userId, @Param("exerciseId") int exerciseId);
	
	@Procedure(name = "get_flex_history_by_exercise")
	List<Flexibility> getFlexHistoryByExercise(@Param("userId") int userId, @Param("exerciseId") int exerciseId);

	@Query("FROM History where userId=:userId AND exerciseId=:exerciseId ORDER BY date")
	List<BodyLift> getHistoryByExercise(@Param("userId") int userId, @Param("exerciseId") int exerciseId);	
	
}
