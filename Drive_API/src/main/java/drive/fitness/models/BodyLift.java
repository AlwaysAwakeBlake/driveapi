package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="body_lift")
public class BodyLift {
	
	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="reps")
	private int reps;
	
	@Column(name="set", nullable = true)
	private int set;
	
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

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public int getSet() {
		return set;
	}
 	public void setSet(int set) {
		this.set = set;
	}
	
	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}
}