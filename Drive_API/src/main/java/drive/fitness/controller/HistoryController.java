package drive.fitness.controller;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.BodyLiftDao;
import drive.fitness.dao.FlexibilityDao;
import drive.fitness.dao.HistoryDao;
import drive.fitness.models.BodyLift;
import drive.fitness.models.CardioHistory;
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
	
	@PersistenceContext
    private EntityManager em;
	
	@RequestMapping(value = "/addFlexHistory", method= RequestMethod.POST)
    public int addFlexHistory(@RequestBody History history) { 
    	historyDao.save(history);
    	return history.getId();
    }
	
	@RequestMapping(value = "/addFlex", method= RequestMethod.POST)
    public void addFlex(@RequestBody Flexibility flex) { 
		System.out.println(flex);
		System.out.println(flex.getHistory());
		historyDao.save(flex.getHistory());
    	flexDao.save(flex);
    }
	
	@RequestMapping(value = "/addBodyLiftHistory", method= RequestMethod.POST)
    public int addBodyLiftHistory(@RequestBody History history) {    	
    	historyDao.save(history);
    	return history.getId();
    }
	
	@RequestMapping(value = "/addBodyLift", method= RequestMethod.POST)
    public void addBodyLift(@RequestBody BodyLift bl) {   
		historyDao.save(bl.getHistory());
		blDao.save(bl);
    }
	
	@RequestMapping(value = "/removeBodyLift", method= RequestMethod.POST)
    public void removeBodyLift(@RequestBody BodyLift bl) {    
		History history = bl.getHistory();
		blDao.delete(bl);
		historyDao.delete(history);
    }
	
	@RequestMapping(value = "/removeFlex", method= RequestMethod.POST)
    public void removeFlex(@RequestBody Flexibility flex) {
		History history = flex.getHistory();
		flexDao.delete(flex);
		historyDao.delete(history);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getFlexHistoryByExercise", method= RequestMethod.GET)
    public List<Flexibility> getFlexHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
		StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getFlexHistoryByExercise");
    	query.setParameter("user_id", userId);
    	query.setParameter("exercise_id", exerciseId);
    	return query.getResultList();
    }
	 
	@RequestMapping(value = "/getBodyLiftHistoryByExercise", method= RequestMethod.GET)
	public List<BodyLift> getBodyLiftHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
		StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getBodyLiftHistoryByExercise");
    	query.setParameter("user_id", userId);
    	query.setParameter("exercise_id", exerciseId);
    	return query.getResultList();
    }
	
	@RequestMapping(value = "/getHistoryByExercise", method= RequestMethod.GET)
	public List<BodyLift> getHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
		return historyDao.getHistoryByExercise(userId, exerciseId);
    }
}
