package com.pfe.Entity;

public class User {
	private String CIN;
    private String name;
    private String phoneNumber;


	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
}
