package drive.fitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import drive.fitness.dao.CompetingDao;
import drive.fitness.models.Competing;

public class CardioHistoryController {
	@Autowired
	private CompetingDao cardioHistoryDao;

    @RequestMapping(value = "/getAllCardioHistory", method= RequestMethod.GET)
    public List<Competing> getAllCompeting() {
        return (List<Competing>) cardioHistoryDao.findAll();
    }
}
