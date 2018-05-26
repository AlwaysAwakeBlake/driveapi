package drive.fitness.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import drive.fitness.models.Exercise;
import drive.fitness.models.User;

public interface UserDao extends CrudRepository<User, Long>{ 

	@Query("FROM User where username=:userName")
	public User findByUsername(@Param("userName") String userName);

    @Procedure(name = "get_user_competing")
    public List<User> getUserCompeting(@Param("user_name_param") String user_name_param);
    
    @Procedure(name = "get_user")
    public User getUser(@Param("username") String username);
    
    @Procedure(name = "get_users")
    public User getUsers();
    
    @Procedure(name = "get_user_id")
    public int getUserId(@Param("username") String username);
    
	@Query("FROM User where email=:userEmail")
	public User findUserByEmail(@Param("userEmail") String userEmail);
}