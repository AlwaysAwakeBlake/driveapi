package drive.fitness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.CardioHistory;
import drive.fitness.models.Competing;
import drive.fitness.models.LiftingHistory;

public interface CardioHistoryDao extends CrudRepository<CardioHistory, Integer>{	
	@Query("FROM CardioHistory where userId=:userId AND exercise.id=:exerciseId")
	public List<CardioHistory> getCardioHistoryByExercise(@Param("userId") int userId, @Param("exerciseId") int exerciseId);
	
	@Query("FROM CardioHistory where userId=:userId")
	public List<CardioHistory> getAllCardioHistoryById(@Param("userId") int userId);
}
