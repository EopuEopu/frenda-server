package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.response.Data;

public class UserKeyData implements Data{
	private String user_key;
	
	public UserKeyData(String user_key) {
		this.user_key = user_key;
	}
	
	public String getUser_key() {
		return user_key;
	}
	
}
