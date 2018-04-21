package drive.fitness.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import drive.fitness.models.Test;

public interface TestDao extends CrudRepository<Test, Long>{ 

}
