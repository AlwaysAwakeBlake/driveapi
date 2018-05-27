package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="exercises")
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "getUserExercises", 
                           procedureName = "get_user_exercises",
                           resultClasses=Exercise.class,
                           parameters = {
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class),
                           }),
@NamedStoredProcedureQuery(name = "getExercise", 
						   procedureName = "get_exercise",
						   resultClasses=Exercise.class,
                           parameters = {
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "muscle_group_param", type = int.class),
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "ex_name_param", type = String.class),
                               @StoredProcedureParameter(mode = ParameterMode.IN, name = "variation_param", type = String.class),
                           }),
@NamedStoredProcedureQuery(name = "saveUserExercise", 
							procedureName = "save_user_exercise",
							parameters = {
									@StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id_param", type = int.class),
									@StoredProcedureParameter(mode = ParameterMode.IN, name = "ex_id_param", type = int.class),
						})
})
public class Exercise {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="`exercise_name`")
	private String exerciseName;
	
	@ManyToOne
	@JoinColumn(name="muscle_group_id")
	@JsonProperty("MuscleGroup")
	private MuscleGroup muscleGroup;

	@Column(name="`variation`")
	private String variation;

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
}
