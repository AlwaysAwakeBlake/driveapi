package drive.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.ExerciseDao;
import drive.fitness.dao.UserDao;
import drive.fitness.models.Exercise;
import drive.fitness.models.MuscleGroup;
import drive.fitness.models.User;

@RestController
public class ExerciseController {

	@Autowired
	private ExerciseDao exerciseDao;

    @RequestMapping(value = "/getExerciseByUserID", method= RequestMethod.GET)
    public List<Exercise> getUserByUsername(@RequestParam(value = "userID", defaultValue = "0") int userId) {
        return (List<Exercise>) exerciseDao.getExerciseByUser(userId);
    }
    
    @RequestMapping(value = "/createExercise", method= RequestMethod.POST)
    public void createExercise (@RequestBody Exercise ex) {
    	MuscleGroup group = ex.getMuscleGroup();
    	Exercise newEx = new Exercise();
    	newEx.setMuscleGroup(group);
        exerciseDao.save(newEx);
    }
     
}