package com.restws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "targetUrl")
public class TargetUrl {

	int id;
	String address;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
