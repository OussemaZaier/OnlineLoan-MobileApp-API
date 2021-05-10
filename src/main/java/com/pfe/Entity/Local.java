package com.pfe.Entity;

public class Local {
	private String address;
	private String latitude;
	private String longitude;
	public Local() {}
	public Local(String address, String latitude, String longitude) {
		super();
		this.address=address;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}