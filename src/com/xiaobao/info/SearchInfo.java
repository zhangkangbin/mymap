package com.xiaobao.info;

public class SearchInfo {
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SearchInfo(String city, String address ,String key) {
		this.city = city;
		this.address = address;
		this.key=key;
		
		
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	String key;
	String city;
	String address;

}
