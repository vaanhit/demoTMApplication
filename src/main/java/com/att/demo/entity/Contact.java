package com.att.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import com.att.demo.annotations.UniqueSSN;

/**
 * @author Rohit
 *
 */
@Entity
public class Contact {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, message = "First Name must be atleast 3 characters!")
	private String firstName;

	@Size(min = 3, message = "Last Name must be atleast 3 characters!")
	private String lastName;

	@DateTimeFormat(pattern = "yyyy-dd-MM")
	private java.util.Date dob;
	
	/*@Column(unique = true)
	@UniqueSSN(message = "This ssn is already exist.")*/
	private Long ssn;
	private String street;
	private String city;
	private String state;
	private long zip;

	private String userName;

	/**
	 * No-Argument Constructor
	 */
	public Contact() {

	}

	/**
	 * Constructor
	 * 
	 * @param fName
	 * @param lName
	 * @param dob
	 * @param SSN
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Contact(int id, String fName, String lName, Date dob, long SSN, String street, String city, String state,
			long zip, String userName) {

		this.id = id; // I will remove this. This is for testing.
		this.firstName = fName;
		this.lastName = lName;
		this.dob = dob;
		this.ssn = SSN;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.userName = userName;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public java.util.Date getDob() {
		return dob;
	}

	public void setDob(java.util.Date dob) {
		this.dob = dob;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
