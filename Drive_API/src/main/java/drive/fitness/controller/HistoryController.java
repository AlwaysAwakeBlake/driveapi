package drive.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.BodyLiftDao;
import drive.fitness.dao.FlexibilityDao;
import drive.fitness.dao.HistoryDao;
import drive.fitness.models.BodyLift;
import drive.fitness.models.Flexibility;
import drive.fitness.models.History;
import drive.fitness.models.LiftingHistory;

@RestController
public class HistoryController {
	@Autowired
	private HistoryDao historyDao;

	@Autowired
	private FlexibilityDao flexDao;
	
	@Autowired
	private BodyLiftDao blDao;
	
	@RequestMapping(value = "/addFlexHistory", method= RequestMethod.POST)
    public void addFlexHistory(@RequestBody History history, Flexibility flex) { 
		System.out.println(history);
		System.out.println("HERE");
		System.out.println(flex);
    	historyDao.save(history);
    	flex.setHistoryId(history.getId());
    	flexDao.save(flex);
    }
	
	
	@RequestMapping(value = "/addBodyLiftHistory", method= RequestMethod.POST)
    public void addBodyLiftHistory(@RequestBody History history, @RequestBody BodyLift bl) {    	
    	historyDao.save(history);
    	bl.setHistoryId(history.getId());
    	blDao.save(bl);
    }
}
