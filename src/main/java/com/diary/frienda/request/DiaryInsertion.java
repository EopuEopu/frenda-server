package com.diary.frienda.request;

import java.util.List;

public class DiaryInsertion {
	private String user_key;
	private String content;
	private List<Stickers> stickers;
	private String user_selected_sentiment;
	
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<Stickers> getStickers() {
		return stickers;
	}
	public void setStickers(List<Stickers> stickers) {
		this.stickers = stickers;
	}
	
	public String getUser_selected_sentiment() {
		return user_selected_sentiment;
	}
	public void setUser_selected_sentiment(String user_selected_sentiment) {
		this.user_selected_sentiment = user_selected_sentiment;
	}
	
}
