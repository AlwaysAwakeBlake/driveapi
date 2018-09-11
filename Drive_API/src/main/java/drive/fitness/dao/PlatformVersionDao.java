package drive.fitness.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import drive.fitness.models.PlatformVersion;


public interface PlatformVersionDao extends CrudRepository<PlatformVersion, Long>{ 

}