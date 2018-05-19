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
	private int user;
	
	@Column(name="`competing_users_id`")
	private int competingUser;
	
	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getCompetingUser() {
		return user;
	}

	public void setCompetingUser(int user) {
		this.user = user;
	}
}