package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import drive.fitness.dao.*;
import drive.fitness.models.*;

import java.io.IOException;
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

import drive.fitness.s3.util.*;
import drive.fitness.services.impl.S3Services;

import drive.fitness.s3.util.Utility;

@RestController
public class UserController {
	
	@Autowired
	private S3Services S3Services;

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
    
    @RequestMapping(value = "/getUserByEmail", method= RequestMethod.GET)
    public User getUserByEmail(@RequestParam(value = "email", defaultValue = "test") String email) {
        return (User) userDao.findUserByEmail(email);
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
    
    @RequestMapping(value = "/getUserProfilePic", method= RequestMethod.GET)
    public String getUserProfilePic(@RequestParam(value = "username", defaultValue = "test") String username) throws IOException {
    	Utility utility = new Utility();
    	return utility.displayText(S3Services.downloadFile("profile_pics/" + username).getObjectContent());
    }

}