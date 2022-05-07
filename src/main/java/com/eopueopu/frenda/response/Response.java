package com.eopueopu.frenda.response;

public class Response {
	private int status_code;
	private String message;
	private Data data;
	
	public Response(int status_code, String message, Data data) {
		this.status_code = status_code;
		this.message = message;
		this.data = data;
	}

	public int getStatus_code() {
		return status_code;
	}

	public String getMessage() {
		return message;
	}
	
	public Data getData() {
		return data;
	}
	
}
