package drive.fitness.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.Excercise;


public interface ExcerciseDao extends CrudRepository<Excercise, Long>{ 

	@Query("FROM Excercise where user_id=:userID")
	public List<Excercise> findExcercisesByUserId(@Param("userID") int userId);
}