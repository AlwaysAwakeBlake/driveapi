package drive.fitness.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.Competing;
import drive.fitness.models.User;

public interface CompetingDao extends CrudRepository<Competing, Integer>{	

}