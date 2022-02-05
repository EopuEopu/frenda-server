package com.eopueopu.frenda.response.data.sub;

import com.eopueopu.frenda.db.diarySentiment.DiarySentiment;

public class DiaryAllSentiments {
	private String sentiment;
	private double negative_value;
	private double positive_value;
	private double neutral_value;
	private String user_selected_sentiment;
	
	public DiaryAllSentiments(DiarySentiment diarySent) {
		this.sentiment = diarySent.getSentiment();
		this.negative_value = diarySent.getNegative_value();
		this.positive_value = diarySent.getPositive_value();
		this.neutral_value = diarySent.getNeutral_value();
		this.user_selected_sentiment = diarySent.getUser_selected_sentiment();
	}
	
	public DiaryAllSentiments(String sentiment, double negative_value, double positive_value, 
			double neutral_value, String user_selected_sentiment) {

	this.sentiment = sentiment;
	this.negative_value = negative_value;
	this.positive_value = positive_value;
	this.neutral_value = neutral_value;
	this.user_selected_sentiment = user_selected_sentiment;
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
