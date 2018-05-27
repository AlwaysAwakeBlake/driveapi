package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cardio_history")
public class CardioHistory {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private String date;
	
	@Column(name="gains")
	private int gains;
	
	@Column(name="mph")
	private double mph;
	
	@Column(name="miles")
	private double miles;
	
	@Column(name="minutes")
	private double minutes;
	
	@ManyToOne
	private Exercise exercise;
	
	@Column(name="user_id")
	private int userId;
	
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
	
	public int getGains() {
		return gains;
	}

	public void setGains(int gains) {
		this.gains = gains;
	}
	
	public double getMph() {
		return mph;
	}

	public void setMph(double mph) {
		this.mph = mph;
	}
	
	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}
	
	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}
	
	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
