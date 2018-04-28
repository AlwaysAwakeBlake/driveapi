package drive.fitness.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import drive.fitness.models.User;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserModelTest {
	
	private User u1;
	private int id = 1;
	private Integer age = 22;
	private String email = "doge@gmail.com";
	private String gym = "Gold's";
	private String location = "Arlington";
	private String profilePicRef = "path/to/pic";
	private Integer weight = 185;
	private String height = "6'3";
	private String username = "testUser";
	
	@Before
	public void setup() {
		u1 = new User();
		u1.setAge(age);
		u1.setEmail(email);
		u1.setGym(gym);
		u1.setHeight(height);
		u1.setId(id);
		u1.setLocation(location);
		u1.setProfilePicRef(profilePicRef);
		u1.setUsername(username);
		u1.setWeight(weight);
	}
	
	@Test
	public void userModelTest() {	
		 Assert.assertEquals(u1.getEmail(), email);
		 Assert.assertEquals(u1.getGym(), gym);
		 Assert.assertEquals(u1.getHeight(), height);
		 Assert.assertEquals(u1.getLocation(), location);
		 Assert.assertEquals(u1.getProfilePicRef(), profilePicRef);
		 Assert.assertEquals(u1.getUsername(), username);
		 Assert.assertEquals(u1.getAge(), age);
		 Assert.assertEquals(u1.getId(), id);
		 Assert.assertEquals(u1.getWeight(), weight);
	}
}