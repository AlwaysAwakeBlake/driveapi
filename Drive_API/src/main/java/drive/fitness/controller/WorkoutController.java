package drive.fitness.controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
		
		@RequestMapping(value = "/getTimelineData", method= RequestMethod.GET)
		public List<List<Object[]>> getTimelineData(@RequestParam(value = "userId", defaultValue = "test") int userId,
			      @RequestParam(value = "startIndex", defaultValue = "test") int startIndex,
			      @RequestParam(value = "endIndex", defaultValue = "test") int endIndex) {
			List<List<Object[]>> timelineData = new ArrayList<>();
			// Get the workouts to calculate
			List<Workout> workouts = getCompetingWorkouts(userId, startIndex, endIndex);
			Iterator<Workout> wi = workouts.iterator();
			
			
			while(wi.hasNext()) {
				Workout workout = wi.next();
				
		    	List<Object[]> workoutData = workoutDao.get_timechart_data(workout.getId());
		    	BigDecimal totalGains = new BigDecimal(0);
		    	int chestIndex1 = 0;
		    	int chestIndex2 = 0;
		    	for(int i = 0; i < workoutData.size(); i++) {
		    		System.out.println((BigDecimal)workoutData.get(i)[1]);
		    		totalGains = totalGains.add((BigDecimal)workoutData.get(i)[1]);
		    		if (((String) workoutData.get(i)[0]).equals("Chest")) {
		    			if (chestIndex1 == 0) {
		    				chestIndex1 = i;
		    			} else {
		    				chestIndex2 = i;
		    			}
		    		}
		    	}
		    	workoutData.get(chestIndex1)[1] = ((BigDecimal)workoutData.get(chestIndex1)[1]).add((BigDecimal)workoutData.get(chestIndex2)[1]);
		    	workoutData.remove(chestIndex2);
		    	
		    	for(int j = 0; j < workoutData.size(); j++) {
		    		if (((BigDecimal)workoutData.get(j)[1]).compareTo(new BigDecimal(0)) != 0) {
		    			workoutData.get(j)[1] = (((BigDecimal)workoutData.get(j)[1]).divide(totalGains, 2, RoundingMode.HALF_UP));

			    		System.out.println(workoutData.get(j)[1]);
		    		}
		    	}
		    	timelineData.add(workoutData);
			}
			return timelineData;
		}
		
		@RequestMapping(value = "/getCompetingWorkouts", method= RequestMethod.GET)
	    public List<Workout> getCompetingWorkouts(@RequestParam(value = "userId", defaultValue = "test") int userId,
    										      @RequestParam(value = "startIndex", defaultValue = "test") int startIndex,
    										      @RequestParam(value = "endIndex", defaultValue = "test") int endIndex) {
		 	StoredProcedureQuery query =
	                em.createNamedStoredProcedureQuery("getCompetingWorkouts");
	    	query.setParameter("user_id", userId);
	    	query.setParameter("start_index", startIndex);
	    	query.setParameter("end_index", endIndex);
	    	List<Workout> workouts = query.getResultList();
	    	for(int i = 0; i < workouts.size(); i++) {
	    		System.out.println(workouts.get(i).getStartTime());
	    	}
	    	return workouts;
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