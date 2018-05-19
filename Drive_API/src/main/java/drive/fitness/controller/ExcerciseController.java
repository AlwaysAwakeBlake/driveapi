package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ExcerciseController {
	
	@Autowired
	private ExcerciseDao excerciseDao;
    
    @RequestMapping(value = "/findExcercisesByUserId", method= RequestMethod.GET)
    public List<Excercise> getUserByUsername(@RequestParam(value = "userId", defaultValue = "0") int userId) {
    	return excerciseDao.findExcercisesByUserId(userId);
    }
}