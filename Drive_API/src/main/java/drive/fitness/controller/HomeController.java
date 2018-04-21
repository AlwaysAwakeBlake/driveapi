package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.TestDao;
import drive.fitness.models.Test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HomeController {
	
	@Autowired
	private TestDao testDao;

    @RequestMapping(value = "/test", method= RequestMethod.GET)
    public List<Test> index() {
        return (List<Test>) testDao.findAll();
    }

}