package com.diary.frenda.db.sentiment;

import java.util.List;

import com.diary.frenda.response.data.sub.SentimentString;

public interface SentimentMapper {
	public String getApprSentimentByDetailedSentiment(String detailed_sentiment) throws Exception;
	public List<SentimentString> getDetailedSentimentByApprSentiment(String appr_sentiment) throws Exception;
}
