package com.diary.frienda.db.sentiment;

public interface SentimentDAO {
	public String getApprSentimentByDetailedSentiment(String detailed_sentiment) throws Exception;
}