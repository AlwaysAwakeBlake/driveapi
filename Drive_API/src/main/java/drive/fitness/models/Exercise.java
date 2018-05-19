package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exercises")
public class Exercise {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="`exercise_name`")
	private String exerciseName;
	
	@ManyToOne
	private MuscleGroup muscleGroup;

	@Column(name="`variation`")
	private String variation;
	
	@Column(name="`user_Id`")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public MuscleGroup getMuscleGroup() {
		return muscleGroup;
	}

	public void setMuscleGroup(MuscleGroup muscleGroup) {
		this.muscleGroup = muscleGroup;
	}

	public String getVariation() {
		return variation;
	}

	public void setVariation(String variation) {
		this.variation = variation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUser(int user) {
		this.userId = user;
	}
}
