package drive.fitness.dao;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.User;
import drive.fitness.models.Workout;

public interface WorkoutDao extends CrudRepository<Workout, Integer>{
	
	@Query(value="SELECT muscle_group_name, total "
			   + "FROM get_timechart_data(1) AS foo(muscle_group_name text, total numeric);", nativeQuery=true)
	public List<Object[]> get_timechart_data(@Param("work_id") int workId);
}