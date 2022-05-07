package com.eopueopu.frenda.db.user;

public class User {
	private String user_id;
	private String user_key;
	private int negative_diary_count;
	
	public User() {
		
	}
	
	public User(String user_id, String user_key) {
		this.user_id = user_id;
		this.user_key = user_key;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public String getUser_key() {
		return user_key;
	}
	
	public int getNegative_diary_count() {
		return negative_diary_count;
	}
	
}
