package drive.fitness.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private FirebaseAuthenticationProvider authenticationProvider;

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
	        .antMatchers(HttpMethod.OPTIONS);
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
	    		    .antMatchers("/getVersion").permitAll()
	    		    .antMatchers("/addFlexHistory").permitAll()
	    		    .anyRequest().authenticated()
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
