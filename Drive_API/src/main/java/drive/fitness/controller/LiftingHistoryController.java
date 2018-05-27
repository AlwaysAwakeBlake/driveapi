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
    public List<LiftingHistory> getLiftingHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseName", defaultValue = "test") String exerciseName) {
        return liftingHistoryDao.getLiftingHistoryByExercise(userId, exerciseName);
    }
    
    @RequestMapping(value = "/getUserGainsTotal", method= RequestMethod.GET)
    public BigInteger getUserGainsTotal(@RequestParam(value = "userId", defaultValue = "test") int userId) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserGainsTotal");
    	query.setParameter("user_id", userId);
    	return (BigInteger)query.getSingleResult();
    }
    
    @RequestMapping(value = "/getUserGainsWeek", method= RequestMethod.GET)
    public BigInteger getUserGainsWeek(@RequestParam(value = "userId", defaultValue = "test") int userId) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserGainsWeek");
    	query.setParameter("user_id", userId);
    	return (BigInteger)query.getSingleResult();
    }
    
    @RequestMapping(value = "/getUserGainsMonth", method= RequestMethod.GET)
    public BigInteger getUserGainsMonth(@RequestParam(value = "userId", defaultValue = "test") int userId) {    	
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserGainsMonth");
    	query.setParameter("user_id", userId);
    	return (BigInteger)query.getSingleResult();
    }
    
    @RequestMapping(value = "/getUserGainsToday", method= RequestMethod.GET)
    public BigInteger getUserGainsToday(@RequestParam(value = "userId", defaultValue = "test") int userId) {    	
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserGainsMonth");
    	query.setParameter("user_id", userId);
    	return (BigInteger)query.getSingleResult();
    }
    
    @RequestMapping(value = "/getLeaderboardData", method= RequestMethod.GET)
    public Iterable<User> getLeaderboardData(@RequestParam(value = "userId", defaultValue = "test") int userId) {    	
    	Iterable<User> competingUsers = new ArrayList<User>();
    	competingUsers = userController.getUserCompeting(userId);
    	competingUsers.forEach((user) -> {
    		BigInteger gainsToday = this.getUserGainsToday(user.getId());
    		BigInteger gainsWeek = this.getUserGainsWeek(user.getId());
    		BigInteger gainsMonth = this.getUserGainsMonth(user.getId());
    		BigInteger gainsTotal = this.getUserGainsTotal(user.getId());
    		user.setGainsToday(gainsToday);
    		user.setGainsWeek(gainsWeek);
    		user.setGainsMonth(gainsMonth);
    		user.setGainsTotal(gainsTotal);
    	});
    	return competingUsers;
    }
    
    @RequestMapping(value = "/addLiftingHistory", method= RequestMethod.POST)
    public void addLiftingHistory(LiftingHistory lf) {    	
    	liftingHistoryDao.save(lf);
    }
}