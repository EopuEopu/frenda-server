package com.diary.frienda.response;

public class AdditionResponse {
	private int status_code;
	private int diary_id;
	private String message;
	
	public AdditionResponse() {
		
	}
	
	public AdditionResponse(int status_code, int diary_id, String message) {
		this.status_code = status_code;
		this.diary_id = diary_id;
		this.message = message;
	}
	
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
