package com.diary.frienda.response;

public class UserInfoResponse {
	private int status_code;
	private String message;
	private UserInfoData data;
	
	public UserInfoResponse(int status_code, String message, UserInfoData data) {
		this.status_code = status_code;
		this.message = message;
		this.data = data;
	}
	
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserInfoData getData() {
		return data;
	}
	public void setData(UserInfoData data) {
		this.data = data;
	}
	
}
