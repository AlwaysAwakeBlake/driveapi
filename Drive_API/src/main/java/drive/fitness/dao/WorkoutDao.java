package drive.fitness.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import drive.fitness.models.Workout;

public interface WorkoutDao extends CrudRepository<Workout, Integer>{
	
}