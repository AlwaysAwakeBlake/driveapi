package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.UserDao;
import drive.fitness.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;

    @RequestMapping(value = "/getAllUsers", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }

}