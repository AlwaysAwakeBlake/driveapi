package drive.fitness.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.Exercise;
import drive.fitness.models.User;

public interface ExerciseDao extends CrudRepository<Exercise, Long>{ 
	@Query("FROM Exercise where userId=:userID")
	public List<Exercise> getExerciseByUser(@Param("userID") int userId);
	
	@Query(value="SELECT *"
		 + "FROM exercises "
		 + "WHERE exercise_name "
		 + "LIKE CONCAT('%',:searchTerm,'%') "
		 + "	AND id NOT IN (SELECT id "
		 + "				   FROM exercises e, users_exercises ue "
		 + "				   WHERE users_id!=:userId"
		 + "				       AND e.id = ue.exercises_id"
		 + ")", nativeQuery=true)
	public List<Exercise> getFilteredExercisesSearch(@Param("searchTerm") String searchTerm, @Param("userId") int userId);
}