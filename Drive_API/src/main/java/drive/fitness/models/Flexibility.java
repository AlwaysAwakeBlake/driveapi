package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="flexibility")
public class Flexibility {
	
	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="minutes")
	private double minutes;
	
	@OneToOne
	@JoinColumn(name="history_id")
	@JsonProperty("History")
	private History history;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}
	
	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}   
}