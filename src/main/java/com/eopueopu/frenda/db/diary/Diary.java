package com.eopueopu.frenda.db.diary;

public class Diary {
	private int diary_id;
	private String user_id;
	private String content;
	private String committed_date;
	private String updated_date;
	
	public Diary(int diary_id, String committed_date) {
		this.diary_id = diary_id;
		this.committed_date = committed_date;
	}
	
	public Diary(String user_id, int diary_id, String content) {
		this.user_id = user_id;
		this.diary_id = diary_id;
		this.content = content;
	}
	
	public Diary(int diary_id, String user_id, String content, String committed_date, String updated_date) {
		this.user_id = user_id;
		this.diary_id = diary_id;
		this.content = content;
		this.committed_date = committed_date;
		this.updated_date = updated_date;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getContent() {
		return content;
	}
	public String getCommitted_date() {
		return committed_date;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	
	
}
