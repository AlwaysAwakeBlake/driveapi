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

	@Query(value="SELECT range, minutes, miles, mph, records "
			   + "FROM get_cardio_records(:user_id,:exercise_id) AS foo(range text, minutes double precision, miles double precision, mph double precision, records bigint);", nativeQuery=true)
	public List<Object[]> getCardioRecords(@Param("user_id") int user_id, @Param("exercise_id") int exercise_id);
}
