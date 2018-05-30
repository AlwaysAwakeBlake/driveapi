package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.*;
import drive.fitness.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}