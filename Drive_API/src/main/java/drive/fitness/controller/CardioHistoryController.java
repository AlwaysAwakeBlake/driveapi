package drive.fitness.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class CardioHistoryController {
	@Autowired
	private CardioHistoryDao cardioHistoryDao;

    @RequestMapping(value = "/getCardioHistoryByExercise", method= RequestMethod.GET)
    public List<CardioHistory> getCardioHistoryByExercise(@RequestParam(value = "userId", defaultValue = "test") int userId, @RequestParam(value = "exerciseId", defaultValue = "test") int exerciseId) {
        return cardioHistoryDao.getCardioHistoryByExercise(userId, exerciseId);
    }
    
    @RequestMapping(value = "/addCardioHistory", method= RequestMethod.POST)
    public void addCardioHistory(@RequestBody CardioHistory ch) {    	
    	cardioHistoryDao.save(ch);
    }
    
    @RequestMapping(value = "/deleteCardioHistory", method= RequestMethod.POST)
    public void deleteCardioHistory(@RequestBody CardioHistory ch) {    	
    	cardioHistoryDao.delete(ch);
    }
    
    @RequestMapping(value = "/getCardioHistoryById", method= RequestMethod.GET)
    public List<CardioHistory> getCardioHistoryById(@RequestParam(value = "userId", defaultValue = "test") int userId) {    	
    	return cardioHistoryDao.getAllCardioHistoryById(userId);
    }
    
    @RequestMapping(value = "/getUserCardioHistoryBetween", method= RequestMethod.GET)
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
}
