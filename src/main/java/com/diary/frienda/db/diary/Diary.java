package com.diary.frienda.db.diary;

public class Diary {
	private int diary_id;
	private String user_id;
	private String content;
	private String committed_date;
	
	public Diary(String user_id, String content) {
		this.user_id = user_id;
		this.content = content;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommitted_date() {
		return committed_date.toString();
	}
	public void setCommitted_date(String committed_date) {
		this.committed_date = committed_date;
	}
	
	
}
