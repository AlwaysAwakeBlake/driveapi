package drive.fitness.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.CardioHistory;
import drive.fitness.models.Competing;
import drive.fitness.models.LiftingHistory;

public interface CardioHistoryDao extends CrudRepository<CardioHistory, Integer>{	
	@Query("FROM CardioHistory where userId=:userId AND exercise.id=:exerciseId ORDER BY date")
	public List<CardioHistory> getCardioHistoryByExercise(@Param("userId") int userId, @Param("exerciseId") int exerciseId);
	
	@Query("FROM CardioHistory where userId=:userId")
	public List<CardioHistory> getAllCardioHistoryById(@Param("userId") int userId);
	
	@Query("FROM CardioHistory where userId=:userId AND date>=:startTime AND date<=:endTime")
	public List<CardioHistory> getUserCardioHistoryBetween(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
