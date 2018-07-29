package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;


import drive.fitness.dao.*;
import drive.fitness.models.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LiftingHistoryController {

	@Autowired
	private LiftingHistoryDao liftingHistoryDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserController userController;
	
	@PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/getAllLiftingHistory", method= RequestMethod.GET)
    public List<LiftingHistory> getAllCompeting() {
        return (List<LiftingHistory>) liftingHistoryDao.findAll();
    }
    
    @RequestMapping(value = "/getUserLiftingHistory", method= RequestMethod.GET)
    public List<LiftingHistory> getUserLiftingHistory(@RequestParam(value = "userId", defaultValue = "test") int userId) {
        return liftingHistoryDao.getUserLiftingHistory(userId);
    }
    
    @RequestMapping(value = "/getLiftingHistoryByExercise", method= RequestMethod.GET)
    public List<LiftingHistory> getLiftingHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
        return liftingHistoryDao.getLiftingHistoryByExercise(userId, exerciseId);
    }
    
    
    @RequestMapping(value = "/addLiftingHistory", method= RequestMethod.POST)
    public void addLiftingHistory(@RequestBody LiftingHistory lf) {
    	System.out.println(lf.getUserId());
    	liftingHistoryDao.save(lf);
    }
    
    @RequestMapping(value = "/deleteLiftingHistory", method= RequestMethod.POST)
    public void deleteLiftingHistory(@RequestBody LiftingHistory lf) {    	
    	liftingHistoryDao.delete(lf);
    }
    
    @RequestMapping(value = "/getUserLiftingHistoryBetween", method= RequestMethod.GET)
    public List<LiftingHistory> getUserLiftingHistoryBetween(@RequestParam(value = "userId", defaultValue = "test") int userId,
    													     @RequestParam(value = "startTime", defaultValue = "test") Date startTime,
    													     @RequestParam(value = "endTime", defaultValue = "test") Date endTime) {
        
    	return liftingHistoryDao.getUserLiftingHistory(userId);
    }
}