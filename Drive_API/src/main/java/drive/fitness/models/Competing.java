package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competing")
public class Competing {
	
	@Id
	@Column(name="user_id")
	private User user;
	
	@Column(name="`competing_users_id`")
	private User competingUser;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getCompetingUser() {
		return user;
	}

	public void setCompetingUser(User user) {
		this.user = user;
	}
}