package com.restws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "targetUrl")
public class TargetUrl {

	public static final String HTTP_GROUP5 = "group5_";
	int id;
	String address;
	String shortUrl;
	
	
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
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(int index) {
		this.shortUrl = HTTP_GROUP5+index;
	}
	
	
	
}
