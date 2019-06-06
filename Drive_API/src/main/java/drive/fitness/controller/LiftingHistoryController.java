package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;



import drive.fitness.dao.*;
import drive.fitness.models.*;
import drive.fitness.models.records.LiftingRecord;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LiftingHistoryController {
	private static final Logger logger = LogManager.getLogger(LiftingHistoryController.class);

	@Autowired
	private LiftingHistoryDao liftingHistoryDao;
	
	@PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/getAllLiftingHistory", method= RequestMethod.GET)
    @CrossOrigin
    public List<LiftingHistory> getAllCompeting() {
        return (List<LiftingHistory>) liftingHistoryDao.findAll();
    }
    
    @RequestMapping(value = "/getUserLiftingHistory", method= RequestMethod.GET)
    @CrossOrigin
    public List<LiftingHistory> getUserLiftingHistory(@RequestParam(value = "userId", defaultValue = "test") int userId) {
        return liftingHistoryDao.getUserLiftingHistory(userId);
    }
    
    @RequestMapping(value = "/getLiftingHistoryByExercise", method= RequestMethod.GET)
    @CrossOrigin
    public List<LiftingHistory> getLiftingHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
        return liftingHistoryDao.getLiftingHistoryByExercise(userId, exerciseId);
    }
    
    
    @RequestMapping(value = "/addLiftingHistory", method= RequestMethod.POST)
    @CrossOrigin
    public String addLiftingHistory(@RequestBody LiftingHistory lf) {
    	try {
    		liftingHistoryDao.save(lf);
    		return "SUCCESS";
    	} catch (Exception e) {
    		logger.info("ERROR ADDING LIFTING HISTORY");
    		logger.info("LIFTING HISTORY USER : " + lf.getUserId());
    		logger.info("LIFTING HISTORY EXERCISE ID: " + lf.getExercise().getId());
    		logger.error(e.getMessage());
    		return "FAILED";
    	}
    }
    
    @RequestMapping(value = "/deleteLiftingHistory", method= RequestMethod.POST)
    @CrossOrigin
    public String deleteLiftingHistory(@RequestBody LiftingHistory lf) {    	
    	try {
    		liftingHistoryDao.delete(lf);
    		return "SUCCESS";
    	} catch (Exception e) {
    		logger.info("ERROR deleting Lifting history - Lifting History ID: " + String.valueOf(lf.getId()));
    		logger.error(e.getMessage());
    		return "FAILED";
    	}
    }
    
    @RequestMapping(value = "/getUserLiftingHistoryBetween", method= RequestMethod.GET)
    @CrossOrigin
    public List<LiftingHistory> getUserLiftingHistoryBetween(@RequestParam(value = "userId", defaultValue = "test") int userId,
    													     @RequestParam(value = "startTime", defaultValue = "test") String startTime,
    													     @RequestParam(value = "endTime", defaultValue = "test") String endTime) {
    	Date start = null;
    	Date end = null;
		try {
			start = new SimpleDateFormat("EEE MMM dd y kk:mm:ss 'GMT'Z (zzzz)").parse(startTime);
	    	end = new SimpleDateFormat("EEE MMM dd y kk:mm:ss 'GMT'Z (zzzz)").parse(endTime);

		} catch (Exception e) {
			logger.info("Error getting user Lifting History Between User ID: " + String.valueOf(userId));
			logger.info("START TIME : " + startTime + " END TIME: " + endTime);
			logger.error(e.getMessage());
			e.printStackTrace();
		}
    	return liftingHistoryDao.getUserLiftingHistoryBetween(userId, start, end);
    }
    
    @RequestMapping(value = "/getLiftingRecords", method= RequestMethod.GET)
    @CrossOrigin
    public List<LiftingRecord> getLiftingRecords(@RequestParam(value = "userId") int userId, @RequestParam(value = "exerciseId") int exerciseId) { 
    	List<Object[]> liftingRecordObjects = liftingHistoryDao.getLiftingRecords(userId, exerciseId);
    	List<LiftingRecord> liftingRecords = new ArrayList<LiftingRecord>();
    	for(int i = 0; i < liftingRecordObjects.size(); i++) {
    		LiftingRecord liftingRecord = new LiftingRecord();
    		liftingRecord.setReps((short)liftingRecordObjects.get(i)[0]);
    		liftingRecord.setWeight((double)liftingRecordObjects.get(i)[1]);
    		liftingRecord.setOneRepMax((double)liftingRecordObjects.get(i)[2]);
    		liftingRecord.setRecords((BigInteger)liftingRecordObjects.get(i)[3]);
    		liftingRecords.add(liftingRecord);
    	}
    	
    	return liftingRecords;
    }
}