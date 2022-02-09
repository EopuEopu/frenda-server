package com.eopueopu.frenda.db.sentiment;

import java.util.List;

import com.eopueopu.frenda.response.data.sub.SentimentString;

public interface SentimentDAO {
	public String getApprSentimentByDetailedSentiment(String detailed_sentiment) throws Exception;
	public List<SentimentString> getDetailedSentimentByApprSentiment(String appr_sentiment) throws Exception;
}
