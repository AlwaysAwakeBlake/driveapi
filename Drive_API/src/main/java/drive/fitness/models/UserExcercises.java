package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_exercises")
public class UserExcercises {
	
	@Id
	@Column(name="users_id")
	private int userID;
	
	@ManyToOne
	@JoinColumn(name="exercises_id")
	private Exercise exercise;
	
	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise ex) {
		this.exercise = ex;
	}
}