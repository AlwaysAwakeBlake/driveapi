package drive.fitness.models;

import java.math.BigInteger;

import java.util.Date;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="history")
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "getFlexHistoryByExercise", 
						   resultClasses=Flexibility.class,
                           procedureName = "get_flex_history_by_exercise",
                           parameters = {
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class),
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "exercise_id", type = int.class),
                           }),
@NamedStoredProcedureQuery(name = "getBodyLiftHistoryByExercise", 
						   resultClasses=BodyLift.class,
						   procedureName = "get_body_lift_history_by_exercise",
						   parameters = {
							   @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class),
							   @StoredProcedureParameter(mode = ParameterMode.IN, name = "exercise_id", type = int.class),
							})
})
public class History {
	
	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date")
	@JsonProperty("date")
	private Date date;
	
	@Column(name="gains")
	private int gains;
	
	
	@Column(name="user_id")
	@JsonProperty("user_id")
	private int userId;
	
	@ManyToOne
	private Exercise exercise;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getGains() {
		return gains;
	}

	public void setGains(int gains) {
		this.gains = gains;
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
}