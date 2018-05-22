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
public class CompetingController {

	@Autowired
	private CompetingDao competingDao;

    @RequestMapping(value = "/getAllCompeting", method= RequestMethod.GET)
    public List<Competing> getAllCompeting() {
        return (List<Competing>) competingDao.findAll();
    }
    
    @RequestMapping(value = "/getCompeting", method= RequestMethod.GET)
    public List<Competing> getCompeting() {
        return (List<Competing>) competingDao.findAll();
    }
}