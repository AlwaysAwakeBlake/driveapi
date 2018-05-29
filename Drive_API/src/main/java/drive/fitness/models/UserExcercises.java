package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name="users_exercises")
@NamedStoredProcedureQueries({
	   @NamedStoredProcedureQuery(name = "getUserGainsWeek", 
	                              procedureName = "get_user_gains_week",
	                              parameters = {
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class)
	                              }),
	   @NamedStoredProcedureQuery(name = "getUserGainsMonth", 
							      procedureName = "get_user_gains_month",
							      parameters = {
							          @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class)
							      }),
	   @NamedStoredProcedureQuery(name = "getUserGainsToday", 
	      						  procedureName = "get_user_gains_today",
							      parameters = {
							          @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class)
							      }),
	   @NamedStoredProcedureQuery(name = "getUserGainsTotal", 
								  procedureName = "get_user_gains_total",
							      parameters = {
							          @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class)
							      })
})
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