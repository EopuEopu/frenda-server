package com.diary.frenda.db.user;

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
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public int getNegative_diary_count() {
		return negative_diary_count;
	}
	public void setNegative_diary_count(int negative_diary_count) {
		this.negative_diary_count = negative_diary_count;
	}
	
}