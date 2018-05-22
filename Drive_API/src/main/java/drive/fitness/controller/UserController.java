package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping(value = "/getUserByUsername", method= RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value = "username", defaultValue = "test") String username) {
        return (User) userDao.findByUsername(username);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserCompeting", method= RequestMethod.GET)
    public List<User> getUserCompeting(@RequestParam(value = "username", defaultValue = "test") String username) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserCompeting");
    	query.setParameter("user_name_param", username);
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
}