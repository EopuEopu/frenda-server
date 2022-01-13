package com.diary.frenda.response.data;

import com.diary.frenda.response.Data;

public class UserKeyData implements Data{
	private String user_key;
	
	public UserKeyData(String user_key) {
		this.user_key = user_key;
	}
	
	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	
}
