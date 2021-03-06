package com.eopueopu.frenda.db.diarySentiment;

import com.eopueopu.frenda.clova.Document;

public class DiarySentiment {
	private int diary_id;
	private String sentiment;
	private double negative_value;
	private double positive_value;
	private double neutral_value;
	private String user_selected_sentiment;
	
	public DiarySentiment(int diary_id, String user_selected_sentiment, Document doc) {
		this.diary_id = diary_id;
		this.user_selected_sentiment = user_selected_sentiment;
		
		this.sentiment = doc.getSentiment();
		this.negative_value = doc.getConfidence().getNegative();
		this.positive_value = doc.getConfidence().getPositive();
		this.neutral_value = doc.getConfidence().getNeutral();
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
	
	public DiarySentiment(int diary_id, String user_selected_sentiment) {
		this.diary_id = diary_id;
		this.user_selected_sentiment = user_selected_sentiment;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	
	public double getNegative_value() {
		return negative_value;
	}
	
	public double getPositive_value() {
		return positive_value;
	}
	
	public double getNeutral_value() {
		return neutral_value;
	}
	
	public String getUser_selected_sentiment() {
		return user_selected_sentiment;
	}
}
