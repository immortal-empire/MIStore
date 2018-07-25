package com.neu.util.model.bean;

import java.util.Date;

public class Customer {
	private Integer id;
	private String Cname;
	private String Cpassword;
	private String Cphone;
	private Date birthday;
	private String gender;
	private String email;
	private String profile;
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getCpassword() {
		return Cpassword;
	}
	public void setCpassword(String cpassword) {
		Cpassword = cpassword;
	}
	public String getCphone() {
		return Cphone;
	}
	public void setCphone(String cphone) {
		Cphone = cphone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
