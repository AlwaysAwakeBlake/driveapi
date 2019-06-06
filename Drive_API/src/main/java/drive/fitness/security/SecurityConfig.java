package drive.fitness.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
	
	@Autowired
	private FirebaseAuthenticationProvider authenticationProvider;
	
	public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
	    return new ProviderManager(Arrays.asList(authenticationProvider));
	}


	public FirebaseAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
	    FirebaseAuthenticationTokenFilter authenticationTokenFilter = new FirebaseAuthenticationTokenFilter();
	    
	    authenticationTokenFilter.setAuthenticationManager(authenticationManager());
	    authenticationTokenFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {});
	    return authenticationTokenFilter;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers(HttpMethod.OPTIONS)
	        .and().ignoring().antMatchers("/getUserByUsername")
	        .and().ignoring().antMatchers("/getUserByEmail")
	        .and().ignoring().antMatchers("/createUser");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {		
	    httpSecurity
	            .cors()
	            .and()
	            .csrf().disable()
	            .authorizeRequests()
	                .antMatchers(HttpMethod.OPTIONS).permitAll()
	                .antMatchers("/signup").permitAll()
	    		    .antMatchers("/login").permitAll()
	    		    .antMatchers("/public").permitAll()
	    		    .antMatchers("/getUserByUsername").permitAll()
	    		    .antMatchers("/getUserByEmail").permitAll()
	    		    .antMatchers("/getVersion").permitAll()
	    		    .antMatchers("/addFlexHistory").permitAll()
	    		    .anyRequest().authenticated()
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .formLogin()
	            .failureHandler(this::loginFailureHandler);
	    httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
	
	private void loginFailureHandler(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        AuthenticationException e) throws IOException {
			
		System.out.println("HEEEEERE");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), "NOPE");
    }
}
