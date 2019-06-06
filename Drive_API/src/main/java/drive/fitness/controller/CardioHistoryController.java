package drive.fitness.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.CardioHistoryDao;
import drive.fitness.dao.CompetingDao;
import drive.fitness.models.CardioHistory;
import drive.fitness.models.Competing;
import drive.fitness.models.LiftingHistory;
import drive.fitness.models.records.CardioRecord;
import drive.fitness.models.records.LiftingRecord;

@RestController
public class CardioHistoryController {
	@Autowired
	private CardioHistoryDao cardioHistoryDao;

    @RequestMapping(value = "/getCardioHistoryByExercise", method= RequestMethod.GET)
    @CrossOrigin
    public List<CardioHistory> getCardioHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
        return cardioHistoryDao.getCardioHistoryByExercise(userId, exerciseId);
    }
    
    @RequestMapping(value = "/addCardioHistory", method= RequestMethod.POST)
    @CrossOrigin
    public void addCardioHistory(@RequestBody CardioHistory ch) {    	
    	cardioHistoryDao.save(ch);
    }
    
    @RequestMapping(value = "/deleteCardioHistory", method= RequestMethod.POST)
    @CrossOrigin
    public void deleteCardioHistory(@RequestBody CardioHistory ch) {    	
    	cardioHistoryDao.delete(ch);
    }
    
    @RequestMapping(value = "/getCardioHistoryById", method= RequestMethod.GET)
    @CrossOrigin
    public List<CardioHistory> getCardioHistoryById(@RequestParam(value = "userId", defaultValue = "test") int userId) {    	
    	return cardioHistoryDao.getAllCardioHistoryById(userId);
    }
    
    @RequestMapping(value = "/getUserCardioHistoryBetween", method= RequestMethod.GET)
    @CrossOrigin
    public List<CardioHistory> getUserCardioHistoryBetween(@RequestParam(value = "userId", defaultValue = "test") int userId,
    													   @RequestParam(value = "startTime", defaultValue = "test") String startTime,
    													   @RequestParam(value = "endTime", defaultValue = "test") String endTime) {
    	Date start = null;
    	Date end = null;
		try {
			start = new SimpleDateFormat("EEE MMM dd y kk:mm:ss 'GMT'Z (zzzz)").parse(startTime);
	    	end = new SimpleDateFormat("EEE MMM dd y kk:mm:ss 'GMT'Z (zzzz)").parse(endTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(start.toString());
		System.out.println(end.toString());

    	return cardioHistoryDao.getUserCardioHistoryBetween(userId, start, end);
    }
    
    @RequestMapping(value = "/getCardioRecords", method= RequestMethod.GET)
    @CrossOrigin
    public List<CardioRecord> getCardioHistory(@RequestParam(value = "userId") int userId, @RequestParam(value = "exerciseId") int exerciseId) { 
    	List<Object[]> cardioHistoryObjects = cardioHistoryDao.getCardioRecords(userId, exerciseId);
    	List<CardioRecord> cardioRecords = new ArrayList<CardioRecord>();
    	for(int i = 0; i < cardioHistoryObjects.size(); i++) {
    		CardioRecord cardioRecord = new CardioRecord();
    		cardioRecord.setRange((String)cardioHistoryObjects.get(i)[0]);
    		cardioRecord.setMinutes((double)cardioHistoryObjects.get(i)[1]);
    		cardioRecord.setMiles((double)cardioHistoryObjects.get(i)[2]);
    		cardioRecord.setMph((double)cardioHistoryObjects.get(i)[3]);
    		cardioRecord.setRecords((BigInteger)cardioHistoryObjects.get(i)[4]);
    		
    		cardioRecords.add(cardioRecord);
    	}
    	
    	return cardioRecords;
    }
}
