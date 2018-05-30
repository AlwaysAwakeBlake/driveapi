package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/getAllUsers", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUsers", method= RequestMethod.GET)
    public List<User> getUsers() {
    	StoredProcedureQuery findByYearProcedure =
                em.createNamedStoredProcedureQuery("getUsers");
    	return findByYearProcedure.getResultList();
    }
    
    @RequestMapping(value = "/getUserByEmail", method= RequestMethod.GET)
    public User getUserByEmail(@RequestParam(value = "email", defaultValue = "test") String email) {
    	User user = userDao.findUserByEmail(email);
    	if (user == null) {
    		user = new User();
    		user.setEmail("doesnt_exist");
    	}
        return user;
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserCompeting", method= RequestMethod.GET)
    public List<User> getUserCompeting(@RequestParam(value = "userId", defaultValue = "test") int userId) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserCompeting");
    	query.setParameter("user_id", userId);
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserCompetitors", method= RequestMethod.GET)
    public List<User> getUserCompetitors(@RequestParam(value = "userId", defaultValue = "test") int userId) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserCompetitors");
    	query.setParameter("user_id", userId);
    	return query.getResultList();
    }
    
    @RequestMapping(value = "/getUser", method= RequestMethod.GET)
    public User getUser(@RequestParam(value = "username", defaultValue = "test") String username) {
    	return userDao.getUser(username);
    }
    
    @RequestMapping(value = "/getUserId", method= RequestMethod.GET)
    public int getUserId(@RequestParam(value = "username", defaultValue = "test") String username) {
    	return userDao.getUserId(username);
    }
    
    @RequestMapping(value = "/getUserByUsername", method= RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value = "username", defaultValue = "test") String username) {
    	User user = userDao.findByUsername(username);
    	if (user == null) {
    		user = new User();
    		user.setUsername("alreadyexists");
    	}
        return user;
    }
    
    @PostMapping(value = "/createUser")
    public void createUser(@RequestBody User user) {
    	System.out.println(user.getId());
        userDao.save(user);
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
    	competingUsers = this.getUserCompeting(userId);
    	competingUsers.forEach((user) -> {
    		BigInteger gainsToday = this.getUserGainsToday(user.getId());
    		gainsToday = gainsToday != null ? gainsToday : BigInteger.ZERO;
    		BigInteger gainsWeek = this.getUserGainsWeek(user.getId());
    		gainsWeek = gainsWeek != null ? gainsWeek : BigInteger.ZERO;
    		BigInteger gainsMonth = this.getUserGainsMonth(user.getId());
    		gainsMonth = gainsMonth != null ? gainsMonth : BigInteger.ZERO;
    		BigInteger gainsTotal = this.getUserGainsTotal(user.getId());
    		gainsTotal = gainsTotal != null ? gainsTotal : BigInteger.ZERO;
    		user.setGainsToday(gainsToday);
    		user.setGainsWeek(gainsWeek);
    		user.setGainsMonth(gainsMonth);
    		user.setGainsTotal(gainsTotal);
    	});
    	return competingUsers;
    }
}