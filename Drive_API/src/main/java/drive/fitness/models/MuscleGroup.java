package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="muscle_groups")
public class MuscleGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="`muscle_group_name`")
	private String muscleGroupName;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMuscleGroupeName() {

		return muscleGroupName;
	}

	public void setMuscleGroupName(String muscleGroupName) {
		this.muscleGroupName = muscleGroupName;
	}
}

