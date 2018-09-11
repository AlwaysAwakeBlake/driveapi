package drive.fitness;

import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableAutoConfiguration
@EnableJpaRepositories
@SpringBootApplication
public class DriveApiApplication extends SpringBootServletInitializer{
	public final static Logger logger = LoggerFactory.getLogger(DriveApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DriveApiApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		
		FileInputStream serviceAccount = new FileInputStream(
				"firebase-adminsdk.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://drive-cadf7.firebaseio.com").build();	

		FirebaseApp.initializeApp(options);
		
		return FirebaseAuth.getInstance();
	}
}
