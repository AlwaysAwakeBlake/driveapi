package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Competing getCompeting(Competing competing) {
    	Competing local_competing = competingDao.getCompeting(competing.getId(), competing.getCompetingUser());
        if (local_competing == null) {
        	local_competing = new Competing();
        	local_competing.setId(-1);
        	local_competing.setCompetingUser(-1);
        }
    	return local_competing;
    }
    
    @RequestMapping(value = "/createCompeting", method= RequestMethod.POST)
    public String createCompeting(@RequestBody Competing competing) {
    	if (getCompeting(competing).getId() == -1) {
    		competingDao.save(competing);
    		return "create_successfull";
    	} else {
    		return "already_exists";
    	}
    }
    
    @RequestMapping(value = "/deleteCompeting", method= RequestMethod.POST)
    public String deleteCompeting(@RequestBody Competing competing) {
    	if (getCompeting(competing).getId() == -1) {
    		return "doesnt_exists";
    	} else {
    		competingDao.delete(competing);
    		return "delete_successfull";
    	}
    }
}