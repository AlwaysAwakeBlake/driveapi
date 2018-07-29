package drive.fitness.controller;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.MuscleGroupDao;
import drive.fitness.dao.UserDao;
import drive.fitness.dao.WorkoutDao;
import drive.fitness.exception.ResourceNotFoundException;
import drive.fitness.models.MuscleGroup;
import drive.fitness.models.User;
import drive.fitness.models.Workout;

@RestController
public class WorkoutController {

		@Autowired
		private WorkoutDao workoutDao;
		
		@PersistenceContext
	    private EntityManager em;
		
		@RequestMapping(value = "/getCompetingWorkouts", method= RequestMethod.GET)
	    public List<Workout> getCompetingWorkouts(@RequestParam(value = "userId", defaultValue = "test") String userId,
    										      @RequestParam(value = "startIndex", defaultValue = "test") int startIndex,
    										      @RequestParam(value = "endIndex", defaultValue = "test") String endIndex) {
		 	StoredProcedureQuery query =
	                em.createNamedStoredProcedureQuery("getCompetingWorkouts");
	    	query.setParameter("user_id", userId);
	    	query.setParameter("start_index", startIndex);
	    	query.setParameter("end_index", endIndex);
	    	return query.getResultList();
	    }
		
		@PostMapping(value = "/createWorkout")
	    public Workout createWorkout(@RequestBody Workout workout) {
	    	workoutDao.save(workout);
	        return workout;
	    }
		
		@SuppressWarnings("unchecked")
		@PostMapping(value = "/setWorkoutEndTime")
	    public Workout setWorkoutEndTime(@RequestBody Workout workout) {
//	    	Workout workout = workoutDao.findById(workoutId).orElseThrow((Supplier<ResourceNotFoundException>) () -> new ResourceNotFoundException("Workout", "id", workoutId));
//	    	workout.setEndTime(endTime);
	    	workoutDao.save(workout);
	        return workout;
	    }
		
		
	    
}