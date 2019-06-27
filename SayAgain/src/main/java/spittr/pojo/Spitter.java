package spittr.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="hao_spitter")
public class Spitter {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 。如果没有大括号的话，message中的值将会作为展现给用户的错误信 息。
	 * 但是使用了大括号之后，我们使用的就是属性文件中的某一个属性，该属性包含了实际
	 * 的信息。
	 */
	@NotNull
	@Size(min = 5, max = 16, message = "{username.size}")
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "acc", columnDefinition="int default 0")
	private int acc;
	
	@NotNull
	@Size(min = 5, max = 25, message = "{password.size}")
	@Column(name = "password")
	private String password;

	@NotNull
	@Size(min = 2, max = 30, message = "{firstName.size}")
	@Column(name = "firstName")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 30, message = "{lastName.size}")
	@Column(name = "lastName")
	private String lastName;

	@NotNull
	@Column(name = "email")
	private String email;

	public Spitter() {
	}

	public Spitter(String username, String password, String firstName, String lastName, String email) {
		this(null, username, password, firstName, lastName, email);
	}

	public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
	}

	public int getAcc() {
		return acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}

}
