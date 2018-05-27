package drive.fitness.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.dao.ExerciseDao;
import drive.fitness.dao.UserDao;
import drive.fitness.dao.UserExercisesDao;
import drive.fitness.models.Exercise;
import drive.fitness.models.MuscleGroup;
import drive.fitness.models.User;
import drive.fitness.models.UserExcercises;

@RestController
public class ExerciseController {

	@Autowired
	private ExerciseDao exerciseDao;
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private UserExercisesDao userExDao;

    @RequestMapping(value = "/getExerciseByUserID", method= RequestMethod.GET)
    public List<Exercise> getUserByUsername(@RequestParam(value = "userID", defaultValue = "0") int userId) {
        return (List<Exercise>) exerciseDao.getExerciseByUser(userId);
    }
    
    @Transactional
    @RequestMapping(value = "/createExercise", method= RequestMethod.POST)
    public void createExercise (@RequestBody Exercise ex, @RequestParam(value = "userID", defaultValue = "1") int userId) {
    	System.out.println(ex.getMuscleGroup());
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getExercise");
    	query.setParameter("muscle_group_param", ex.getMuscleGroup().getId());
    	query.setParameter("ex_name_param", ex.getExerciseName());
    	query.setParameter("variation_param", ex.getVariation());
    	Exercise newEx = (Exercise) query.getSingleResult();
    	if(newEx == null) {
            exerciseDao.save(ex);
            newEx = ex;
    	}
    	StoredProcedureQuery query2 =
                em.createNamedStoredProcedureQuery("saveUserExercise");
    	query.registerStoredProcedureParameter("user_id_param", int.class, ParameterMode.IN);
    	System.out.println(userId);
    	System.out.println(newEx.getId());
    	query.setParameter("user_id_param", userId);
    	query.setParameter("ex_name_param", newEx.getId());
    	query.getFirstResult();
//    	
//    	EntityTransaction et = em.getTransaction();
//    	et.begin();
//    	em.createNativeQuery("UPDATE ... ;").executeUpdate();
//    	et.commit();
//    	
    	String query3 = "insert into users_exercises VALUES (?1, ?2)";
    	em.createNativeQuery(query3)
    	   .setParameter(1, userId)
    	   .setParameter(2, newEx.getId())
    	   .executeUpdate();
    }
    
    @Transactional
    @RequestMapping(value = "/removeExercise", method= RequestMethod.GET)
    public void removeExercise (@RequestParam(value = "userID", defaultValue = "1") int userId, @RequestParam(value = "exID", defaultValue = "1") int exId) {
    	String query3 = "DELETE FROM users_exercises WHERE users_id = ?1 AND exercises_id = ?2";
    	em.createNativeQuery(query3)
    	   .setParameter(1, userId)
    	   .setParameter(2, exId)
    	   .executeUpdate();
    }
    
    @RequestMapping(value = "/getAllUserEx", method= RequestMethod.GET)
    public List<UserExcercises> getAllUserEx() {
        return (List<UserExcercises>) userExDao.findAll();
    }
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserExercises", method= RequestMethod.GET)
    public List<Exercise> getUserExercises(@RequestParam(value = "id", defaultValue = "1") int id) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getUserExercises");
    	query.setParameter("user_id", id);
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/getExercise", method= RequestMethod.GET)
    public Exercise getExercise(@RequestParam(value = "muscleGroup", defaultValue = "1") int muscleGroup,
    							@RequestParam(value = "name", defaultValue = "1") String name,
    							@RequestParam(value = "variation", defaultValue = "1") String variation) {
    	StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getExercise");
    	query.setParameter("muscle_group_param", muscleGroup);
    	query.setParameter("ex_name_param", name);
    	query.setParameter("variation_param", variation);
    	return (Exercise) query.getSingleResult();
    }
    
    
    
     
}