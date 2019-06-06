package drive.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.MuscleGroupDao;
import drive.fitness.dao.UserDao;
import drive.fitness.models.MuscleGroup;
import drive.fitness.models.User;

@RestController
public class MuscleGroupController {

		@Autowired
		private MuscleGroupDao mgDao;

	    @RequestMapping(value = "/getAllMG", method= RequestMethod.GET)
	    @CrossOrigin
	    public List<MuscleGroup> getAllMuscleGroups() {
	        return (List<MuscleGroup>) mgDao.findAll();
	    }
	    
}
