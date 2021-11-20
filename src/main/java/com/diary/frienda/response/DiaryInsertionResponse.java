package com.diary.frienda.response;

public class DiaryInsertionResponse {
	private int status_code;
	private int diary_id;
	private String message;
	private int favor_value;
	private boolean portal_open;
	
	public DiaryInsertionResponse() {
		
	}
	
	public DiaryInsertionResponse(int status_code, int diary_id, String message, int favor_value, boolean portal_open) {
		this.status_code = status_code;
		this.diary_id = diary_id;
		this.message = message;
		this.favor_value = favor_value;
		this.portal_open = portal_open;
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

	public int getFavor_value() {
		return favor_value;
	}

	public void setFavor_value(int favor_value) {
		this.favor_value = favor_value;
	}

	public boolean isPortal_open() {
		return portal_open;
	}

	public void setPortal_open(boolean portal_open) {
		this.portal_open = portal_open;
	}
	
	
	
}
