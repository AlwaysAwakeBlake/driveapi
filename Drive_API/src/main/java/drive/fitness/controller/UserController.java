package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private CompetingDao competingDao;

    @RequestMapping(value = "/getAllUsers", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }
    
    @RequestMapping(value = "/getUsers", method= RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) userDao.findAll();
    }
    
    @RequestMapping(value = "/getUserByUsername", method= RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value = "username", defaultValue = "test") String username) {
        return (User) userDao.findByUsername(username);
    }
    
    
    
    @RequestMapping(value = "/getUserCompeting", method= RequestMethod.GET)
    public Optional<Competing> getUserCompeting(@RequestParam(value = "username", defaultValue = "test") String username) {
    	Iterable<Integer> userId = new ArrayList<>(this.getUserByUsername(username).getId());
    	Iterable<Competing> competingUsers = competingDao.findAllById(userId);
    	return competingDao.findById(this.getUserByUsername(username).getId());
//    	return competingDao.findAll();

//    	List<User> users = new ArrayList<>();
//    	competingUsers.forEach((id)->{
//    		users.push();
//    	});
        
    }
}