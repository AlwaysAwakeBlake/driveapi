package drive.fitness;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
public class DriveApiApplication extends SpringBootServletInitializer{
	public final static Logger logger = LoggerFactory.getLogger(DriveApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DriveApiApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(12);
	}
}
