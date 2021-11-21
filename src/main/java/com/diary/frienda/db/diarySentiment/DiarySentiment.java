package com.diary.frienda.db.diarySentiment;

import com.diary.frienda.clova.Confidence;

public class DiarySentiment {
	private int diary_id;
	private String sentiment;
	private double negative_value;
	private double positive_value;
	private double neutral_value;
	private String user_selected_sentiment;
	
	public DiarySentiment(int diary_id, String sentiment, String user_selected_sentiment, Confidence conf) {
		this.diary_id = diary_id;
		this.sentiment = sentiment;
		this.user_selected_sentiment = user_selected_sentiment;
		this.negative_value = conf.getNegative();
		this.positive_value = conf.getPositive();
		this.neutral_value = conf.getNeutral();
		}
	
	public DiarySentiment(int diary_id, String sentiment, double negative_value, double positive_value, double neutral_value
							, String user_selected_sentiment) {
		this.diary_id = diary_id;
		this.sentiment = sentiment;
		this.negative_value = negative_value;
		this.positive_value = positive_value;
		this.neutral_value = neutral_value;
		this.user_selected_sentiment = user_selected_sentiment;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public double getNegative_value() {
		return negative_value;
	}
	public void setNegative_value(double negative_value) {
		this.negative_value = negative_value;
	}
	public double getPositive_value() {
		return positive_value;
	}
	public void setPositive_value(double positive_value) {
		this.positive_value = positive_value;
	}
	public double getNeutral_value() {
		return neutral_value;
	}
	public void setNeutral_value(double neutral_value) {
		this.neutral_value = neutral_value;
	}
	public String getUser_selected_sentiment() {
		return user_selected_sentiment;
	}
	public void setUser_selected_sentiment(String user_selected_sentiment) {
		this.user_selected_sentiment = user_selected_sentiment;
	}
}
