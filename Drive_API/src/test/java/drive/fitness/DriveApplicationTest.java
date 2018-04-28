package drive.fitness;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DriveApiApplication.class)
	public class DriveApplicationTest {

	@Test
	public void contextLoads() {
	}
		
	@Test
	public void test() {
		DriveApiApplication.main(
				new String[] { "--spring.main.web-environment=false", "--spring.autoconfigure.exclude=blahblahblah",
				// Override any other environment properties according to your needs
				});
	}
}
