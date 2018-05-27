package drive.fitness.models;

import java.math.BigInteger;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

import javax.persistence.Table;

@Entity
@Table(name="lifting_history")
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
public class LiftingHistory {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private String date;
	
	@Column(name="gains")
	private int gains;
	
	@Column(name="weight")
	private int weight;
	
	@Column(name="reps")
	private int reps;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="one_rep_max", nullable = true)
	private int oneRepMax;
	
	@ManyToOne
	private Exercise exercise;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getGains() {
		return gains;
	}

	public void setGains(int gains) {
		this.gains = gains;
	}
	
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	public int getOneRepMax() {
		return oneRepMax;
	}

	public void setOneRepMax(int oneRepMax) {
		this.oneRepMax = oneRepMax;
	}
}