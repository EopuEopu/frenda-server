package com.eopueopu.frenda.response.data;

import java.util.List;

import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.SentimentString;

public class SentimentRecommendationData implements Data {
	private String clova_analyzed_sentiment;
	private List<SentimentString> recommend_sentiments;
	
	public SentimentRecommendationData(String clova_analyzed_sentiment, List<SentimentString> recommend_sentiments) {
		this.clova_analyzed_sentiment = clova_analyzed_sentiment;
		this.recommend_sentiments = recommend_sentiments;
	}
	
	
	public String getClova_analyzed_sentiment() {
		return clova_analyzed_sentiment;
	}
	public void setClova_analyzed_sentiment(String clova_analyzed_sentiment) {
		this.clova_analyzed_sentiment = clova_analyzed_sentiment;
	}
	public List<SentimentString> getRecommend_sentiments() {
		return recommend_sentiments;
	}
	public void setRecommend_sentiments(List<SentimentString> recommend_sentiments) {
		this.recommend_sentiments = recommend_sentiments;
	}
	
	
}
