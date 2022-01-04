package com.diary.frienda.response.data.sub;

public class NegativeSentimentCount {
	private String sentiment;
	private int count;
	
	public NegativeSentimentCount() {	}
	
	public NegativeSentimentCount(String sentiment, int count) {
		this.sentiment = sentiment;
		this.count = count;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
