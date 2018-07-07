package drive.fitness.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

  private String username;

  private String email;

  private String password;

  public String getUsername() {
    return this.username.toLowerCase();
  }

  public void setUsername(String username) {
    this.username = username.toLowerCase();
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password.toLowerCase();
  }

  public void encodePassword(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
  }

}