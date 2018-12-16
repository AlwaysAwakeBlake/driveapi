package drive.fitness.controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.MuscleGroupDao;
import drive.fitness.dao.UserDao;
import drive.fitness.dao.WorkoutDao;
import drive.fitness.exception.ResourceNotFoundException;
import drive.fitness.models.MuscleGroup;
import drive.fitness.models.User;
import drive.fitness.models.Workout;

@RestController
public class WorkoutController {

		@Autowired
		private WorkoutDao workoutDao;
		
		@PersistenceContext
	    private EntityManager em;
		
	    
}