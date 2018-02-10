package com.objects.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="password")
	private String password;
	
	@XmlElement(name="confpwd")
	private String confpwd;
	
	@XmlElement(name="emailId")
	private String emailId;
	
	@XmlElement(name="country")
	private String country;
	
	@XmlElement(name="mobileNumber")
	private String mobileNumber;
	
	@XmlElement(name="pincode")
	private String pincode;
	
	@XmlElement(name="street")
	private String street;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlElement(name="city")
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getConfpwd() {
		return confpwd;
	}

	public void setConfpwd(String confpwd) {
		this.confpwd = confpwd;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	

}
