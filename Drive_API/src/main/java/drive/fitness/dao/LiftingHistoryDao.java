package drive.fitness.dao;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.LiftingHistory;
import drive.fitness.models.User;

public interface LiftingHistoryDao extends CrudRepository<LiftingHistory, Integer>{ 
	@Procedure(name = "get_user_gains_total")
	public long getUserGainsTotal(@Param("user_id") int user_id);
	
	@Procedure(name = "get_user_gains_week")
	public long getUserGainsWeek(@Param("user_id") int user_id);
	
	@Procedure(name = "get_user_gains_month")
	public long getUserGainsMonth(@Param("user_id") int userId);
	
	@Procedure(name = "get_user_gains_today")
	public long getUserGainsToday(@Param("user_id") int userId);
}
