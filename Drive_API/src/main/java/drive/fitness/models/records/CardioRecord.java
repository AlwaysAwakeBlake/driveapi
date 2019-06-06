package drive.fitness.models.records;

import java.math.BigInteger;

public class CardioRecord {
	private String range;
	private double minutes;
	private double miles;
	private double mph;
	private BigInteger records;
	
	public String getRange() {
		return range;
	}
	
	public void setRange(String range) {
		this.range = range;
	}
	
	public double getMinutes() {
		return minutes;
	}
	
	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}
	
	public double getMiles() {
		return miles;
	}
	
	public void setMiles(double miles) {
		this.miles = miles;
	}
	
	public double getMph() {
		return mph;
	}
	
	public void setMph(double mph) {
		this.mph = mph;
	}
	
	public BigInteger getRecords() {
		return records;
	}
	
	public void setRecords(BigInteger records) {
		this.records = records;
	}
}
