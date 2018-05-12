package drive.fitness.dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.User;

public interface UserDao extends CrudRepository<User, Long>{ 

	@Query("FROM User where username=:userName")
	public User findByUsername(@Param("userName") String userName);
}