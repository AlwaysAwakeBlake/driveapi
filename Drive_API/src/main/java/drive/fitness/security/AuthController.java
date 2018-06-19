package drive.fitness.security;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import drive.fitness.security.User;
import drive.fitness.security.UserService;
import drive.fitness.DriveApiApplication;
import drive.fitness.security.TokenProvider;

@RestController
public class AuthController {

  private final UserService userService;

  private final TokenProvider tokenProvider;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthController(PasswordEncoder passwordEncoder, UserService userService,
      TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.tokenProvider = tokenProvider;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;

    User user = new User();
    user.setUsername("admin");
    user.setPassword(this.passwordEncoder.encode("admin"));
    this.userService.save(user);
  }

  @RequestMapping(value = "/authenticate", method= RequestMethod.GET)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void authenticate() {
    // we don't have to do anything here
    // this is just a secure endpoint and the JWTFilter
    // validates the token
    // this service is called at startup of the app to check
    // if the jwt token is still valid
  }

  @RequestMapping(value = "/login", method= RequestMethod.POST)
  public String authorize(@Valid @RequestBody User loginUser,
      HttpServletResponse response) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        loginUser.getUsername(), loginUser.getPassword());

    try {
      this.authenticationManager.authenticate(authenticationToken);
      return this.tokenProvider.createToken(loginUser.getUsername());
    }
    catch (AuthenticationException e) {
      DriveApiApplication.logger.info("Security exception {}", e.getMessage());
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
  }

  @RequestMapping(value = "/signup", method= RequestMethod.GET)
  public String signup(@RequestBody User signupUser) {
	System.out.println("here");
//    if (this.userService.usernameExists(signupUser.getUsername())) {
//      return "EXISTS";
//    }
//
//    signupUser.encodePassword(this.passwordEncoder);
//    this.userService.save(signupUser);
//    return this.tokenProvider.createToken(signupUser.getUsername());
	return "here";
  }

}