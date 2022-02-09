package com.eopueopu.frenda.db.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddFavorValueRequest {
	private String user_id;
	private int up;
	private String updated_day;
	
	public AddFavorValueRequest(String user_id, int up) {
		this.user_id = user_id;
		this.up = up;
		this.updated_day = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}

	public String getUpdatedDay() {
		return updated_day;
	}

	public void setUpdatedDay(String updated_day) {
		this.updated_day = updated_day;
	}
		
}
