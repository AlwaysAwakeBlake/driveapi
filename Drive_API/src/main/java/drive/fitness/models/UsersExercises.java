package drive.fitness.models;

import javax.persistence.Column;

public class UsersExercises {
	@Column(name="`users_id`")
	private int usersId;
	
	@Column(name="`exercises_id`")
	private int exercisesId;

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
	public int getExercisesId() {
		return exercisesId;
	}

	public void setExercisesId(int exercisesId) {
		this.exercisesId = exercisesId;
	}
}
