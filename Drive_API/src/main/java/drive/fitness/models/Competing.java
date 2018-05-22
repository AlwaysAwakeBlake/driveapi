package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="competing")
public class Competing {
	
	@Id
	@Column(name="users_id")
	private int id;
	
	@Column(name="`competing_users_id`")
	private int competingUser;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompetingUser() {
		return competingUser;
	}

	public void setCompetingUser(int competingUserId) {
		this.competingUser = competingUserId;
	}
}