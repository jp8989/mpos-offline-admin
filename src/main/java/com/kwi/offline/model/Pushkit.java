package com.kwi.offline.model;

public class Pushkit {
	
	private long id;
	private String token;
	private String pushText;
	private String pushLabel;
	private String tokenLabel;
	
	public String getPushLabel() {
		return pushLabel;
	}
	public void setPushLabel(String pushLabel) {
		this.pushLabel = "Push Text:";
	}
	public String getTokenLabel() {
		return tokenLabel;
	}
	public void setTokenLabel(String tokenLabel) {
		this.tokenLabel = "Device Token:";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPushText() {
		return pushText;
	}
	public void setPushText(String pushText) {
		this.pushText = pushText;
	}

}
