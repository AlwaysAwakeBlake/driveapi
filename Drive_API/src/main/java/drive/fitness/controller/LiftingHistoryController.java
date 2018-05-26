package drive.fitness.controller;

import org.springframework.web.bind.annotation.RestController;
import drive.fitness.dao.*;
import drive.fitness.models.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class LiftingHistoryController {

	@Autowired
	private LiftingHistoryDao liftingHistoryDao;

    @RequestMapping(value = "/getAllLiftingHistory", method= RequestMethod.GET)
    public List<LiftingHistory> getAllCompeting() {
        return (List<LiftingHistory>) liftingHistoryDao.findAll();
    }
    
    @RequestMapping(value = "/getUserTotalGains", method= RequestMethod.GET)
    public int getUserTotalGains(int userId) {
    	return liftingHistoryDao.getUserTotalGains(userId);
    }
    
    @RequestMapping(value = "/getUserGainsWeek", method= RequestMethod.GET)
    public long getUserGainsWeek(int userId) {    	
    	return liftingHistoryDao.getUserGainsWeek(userId);

    }
    
    @RequestMapping(value = "/getUserGainsMonth", method= RequestMethod.GET)
    public long getUserGainsMonth(int userId) {    	
    	return liftingHistoryDao.getUserGainsMonth(userId);

    }
}