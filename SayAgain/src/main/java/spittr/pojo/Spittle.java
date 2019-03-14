package spittr.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.sun.istack.internal.NotNull;
 
@Entity
@Table(name="smd_spittle")
public class Spittle {

	private Long id;
	public void setId(Long id) {
		this.id = id;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@NotNull
	private String message;
	private Date time;
	
	@NotNull
	private double latitude;
	
	@NotNull
	private double longitude;
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Spittle(){
		this((long) 11, "tutu123", new Date());
	}
	
	public Spittle(Long id, String message, Date time){
		this(id, message, time, 0, 0);
	}
	public Spittle(Long id, String message, Date time, double latitude, double longitude){
		this.id = id;
		this.message = message;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	@Column(name="message")
	public String getMessage() {
		return message;
	}
	
	@Column(name="edit_date")
	public Date getTime() {
		return time;
	}
	
	@Column(name="latitude")
	public double getLatitude() {
		return latitude;
	}
	
	@Column(name="longitude")
	public double getLongitude() {
		return longitude;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
		//return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return EqualsBuilder.reflectionEquals(this, obj, "id", "time");
		//return super.equals(obj);
	}
}
