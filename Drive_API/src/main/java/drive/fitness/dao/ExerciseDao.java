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
}