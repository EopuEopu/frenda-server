package com.eopueopu.frenda.response.data.sub;

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
	
	public int getCount() {
		return count;
	}
	
}
