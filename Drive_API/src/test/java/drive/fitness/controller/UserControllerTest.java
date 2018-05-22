package drive.fitness.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import drive.fitness.dao.UserDao;
import drive.fitness.models.User;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserDao mockUserDao;
	
	
	
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


	@Test
	public void getAllUsers() {	
		List<User> users = new ArrayList<User>();
		User u1 = new User();
		User u2 = new User();
		users.add(u1);
		users.add(u2);
		
		 when(mockUserDao.findAll()).thenReturn(users);
		 
		 Assert.assertEquals(users.size(), userController.getAllUsers().size());
	}
	
//	@Test
//	public void getUserCompeting() {	
//		List<User> users = new ArrayList<User>();
//		
//		 when(mockUserDao.getUserCompeting("TD")).thenReturn(users);
//		 
//		 Assert.assertEquals("", userController.getUserCompeting("TD"));
//	}
}