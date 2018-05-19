package drive.fitness.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.ParameterMode;

@Entity
@Table(name="users")
@NamedStoredProcedureQueries({
	   @NamedStoredProcedureQuery(name = "get_User_Competing", 
	                              procedureName = "getUserCompeting",
	                              parameters = {
	                                 @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name_param", type = String.class)
	                              })
})
public class User {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="`username`")
	private String username;
	
	@Column(name="`email`")
	private String email;

	@Column(name="`location`")
	private String location;
	
	@Column(name="`age`")
	private Integer age;
	
	@Column(name="`height`")
	private String height;
	
	@Column(name="`weight`")
	private Integer weight;
	
	@Column(name="`profile_pic_s3_ref`")
	private String profilePicRef;
	
	@Column(name="`gym`")
	private String gym;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public String getProfilePicRef() {
		return profilePicRef;
	}

	public void setProfilePicRef(String profilePicRef) {
		this.profilePicRef = profilePicRef;
	}

	public String getGym() {
		return gym;
	}

	public void setGym(String gym) {
		this.gym = gym;
	}

}
