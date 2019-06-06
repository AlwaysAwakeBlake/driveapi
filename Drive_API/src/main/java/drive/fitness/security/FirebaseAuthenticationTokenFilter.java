package drive.fitness.security;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.util.Strings;
import com.google.firebase.auth.FirebaseAuth;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


public class FirebaseAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    private final static String TOKEN_HEADER = "Authorization";

    public FirebaseAuthenticationTokenFilter() {
    	
        super("/**");
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final String authToken = request.getHeader(TOKEN_HEADER);
        System.out.println(authToken);
        if (Strings.isNullOrEmpty(authToken)) {
            throw new RuntimeException("Invaild auth token");
        }
        
        
        
        try {
			System.out.println(FirebaseAuth.getInstance().verifyIdTokenAsync(authToken, false).get());
			return getAuthenticationManager().authenticate(new FirebaseAuthenticationToken(authToken));
		} catch (InterruptedException | ExecutionException e) {
			throw new InternalAuthenticationServiceException("TOKEN EXPIRED", e);
		}
    }
    
  /**
     * Make sure the rest of the filterchain is satisfied
     *
    */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
}