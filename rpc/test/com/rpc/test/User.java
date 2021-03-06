package com.rpc.test;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -2330722557494342851L;
	
	private String userName;
	
	private Integer age;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", age=" + age + "]";
	}
	
	
	
}
