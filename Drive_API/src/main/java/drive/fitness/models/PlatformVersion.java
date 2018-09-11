package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="platform_version")
public class PlatformVersion {

	@Column(name="android")
	private String android;
	
	@Id
	@Column(name="`ios`")
	private String ios;
	
	public String getAndroid() {
		return android;
	}

	public void setId(String android) {
		this.android = android;
	}

	public String getIos() {
		return ios;
	}

	public void setIos(String ios) {
		this.ios = ios;
	}
}