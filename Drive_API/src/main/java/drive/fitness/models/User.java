package drive.fitness.models;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.ParameterMode;

@Entity
@Table(name="users")
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "getUserCompeting", 
                           procedureName = "get_users_competing",
                           resultClasses=User.class,
                           parameters = {
                              @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class),
                           }),
@NamedStoredProcedureQuery(name = "getUserCompetitors", 
						   procedureName = "get_users_competitors",
						   resultClasses=User.class,
						   parameters = {
							  @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_id", type = int.class),
						   }),
@NamedStoredProcedureQuery(name = "get_user_id", 
						   procedureName = "get_user_id",
						   parameters = {
								   @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
								   @StoredProcedureParameter(mode = ParameterMode.OUT, name = "user_id", type = int.class)
						   }),
@NamedStoredProcedureQuery(name="getUser", 
						   procedureName="get_user",
						   resultClasses=User.class,
						   parameters = {
								   @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
								   @StoredProcedureParameter(mode = ParameterMode.OUT, name = "user", type = User.class)
						   }),
@NamedStoredProcedureQuery(name="getUsers", 
						   procedureName="get_users",
						   resultClasses=User.class)
})
public class User {
	
	@Id
	@Column(name="id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Transient
	private BigInteger gainsToday;
	@Transient
	private BigInteger gainsWeek;
	@Transient
	private BigInteger gainsMonth;
	@Transient
	private BigInteger gainsTotal;

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
	
	public BigInteger getGainsToday() {
		return gainsToday;
	}

	public void setGainsToday(BigInteger gainsToday) {
		this.gainsToday = gainsToday;
	}
	
	public BigInteger getGainsWeek() {
		return gainsWeek;
	}

	public void setGainsWeek(BigInteger gainsWeek) {
		this.gainsWeek = gainsWeek;
	}
	
	public BigInteger getGainsMonth() {
		return gainsMonth;
	}

	public void setGainsMonth(BigInteger gainsMonth) {
		this.gainsMonth = gainsMonth;
	}
	
	public BigInteger getGainsTotal() {
		return gainsTotal;
	}

	public void setGainsTotal(BigInteger gainsTotal) {
		this.gainsTotal = gainsTotal;
	}

}
