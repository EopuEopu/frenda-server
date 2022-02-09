package com.eopueopu.frenda.response.data.sub;

public class MonthlyDiaries {
	private int diary_id;
	private String committed_date;
	private String user_selected_sentiment;
	
	public MonthlyDiaries() { }
	
	public MonthlyDiaries(int diary_id, String committed_date, String user_selected_sentiment) {
		this.diary_id = diary_id;
		this.committed_date = committed_date;
		this.user_selected_sentiment = user_selected_sentiment;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getCommitted_date() {
		return committed_date;
	}
	public void setCommitted_date(String committed_date) {
		this.committed_date = committed_date;
	}
	public String getUser_selected_sentiment() {
		return user_selected_sentiment;
	}
	public void setUser_selected_sentiment(String user_selected_sentiment) {
		this.user_selected_sentiment = user_selected_sentiment;
	}
	
	
}
