package drive.fitness.models.records;

import java.math.BigInteger;

import javax.persistence.Entity;

public class LiftingRecord {
	private int reps;
	private double weight;
	private double oneRepMax;
	private BigInteger records;
	
	public int getReps() {
		return reps;
	}
	
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getOneRepMax() {
		return oneRepMax;
	}
	
	public void setOneRepMax(double oneRepMax) {
		this.oneRepMax = oneRepMax;
	}
	
	public BigInteger getRecords() {
		return records;
	}
	
	public void setRecords(BigInteger records) {
		this.records = records;
	}
}
