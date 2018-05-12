package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeController {
	
	@Autowired
	private TestDao testDao;
	
	@Autowired
	private UserDao userDao;
	
    @RequestMapping(value = "/test", method= RequestMethod.GET)
    public List<Test> index() {
        return (List<Test>) testDao.findAll();
    }
    
    @RequestMapping(value = "/getUsers", method= RequestMethod.GET)
    public List<User> getUsers() {
        return (List<User>) userDao.findAll();
    }
    
    @RequestMapping(value = "/getUserByUsername", method= RequestMethod.GET)
    public User getUserByUsername(@RequestParam(value = "username", defaultValue = "test") String username) {
        return (User) userDao.findByUsername(username);
    }
}