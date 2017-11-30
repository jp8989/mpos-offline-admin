package com.kwi.offline.model;

import java.io.Serializable;

public class GBDevice implements Serializable{
	
	String deviceId;
	String deviceToken;
	String client;
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	public GBDevice() {
	}	
	
	public GBDevice(String deviceid_) {
		this.deviceId = deviceid_;
	}	
	
	public GBDevice(String client_,String deviceToken_,String deviceid_) {
		this.deviceId = deviceid_;
		this.deviceToken = deviceToken_;
		this.client = client_;
	}


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
