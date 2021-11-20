package com.diary.frienda.response;

public class DiaryViewResponse {
	private int status_code;
	private String message;
	private DiaryViewContent contents;
	
	public DiaryViewResponse(int status_code, String message, DiaryViewContent contents) {
		this.status_code = status_code;
		this.message = message;
		this.contents = contents;
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
	public DiaryViewContent getContents() {
		return contents;
	}
	public void setContents(DiaryViewContent contents) {
		this.contents = contents;
	}
	
	
	
}
