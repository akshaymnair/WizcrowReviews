package com.javatpoint;

/*import java.util.ArrayList;
import java.util.Date;*/

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

	@JsonProperty("name")
	@Pattern(regexp="[^a-z]*")
	private  String name;
	
//	@Size(min=5 , max=20)@IsValidHobby()
//	private String studentHobby;
//	
	private String email;
	private String mobile;
	
	private String password;
	private String confpassword;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfpassword() {
		return confpassword;
	}

	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
/*	@Past @NotNull
	private Date studentDOB;*/
	
	/*@NotNull
	private ArrayList<String> studentSkills;
	*/
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	/*public ArrayList<String> getStudentSkills() {
		return studentSkills;
	}
	public void setStudentSkills(ArrayList<String> studentSkills) {
		this.studentSkills = studentSkills;
	}*/
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
/*	public Date getStudentDOB() {
		return studentDOB;
	}
	public void setStudentDOB(Date studentDOB) {
		this.studentDOB = studentDOB;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public String getStudentHobby() {
		return studentHobby;
	}
	public void setStudentHobby(String studentHobby) {
		this.studentHobby = studentHobby;
	}*/
	
	
}
