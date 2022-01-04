package com.diary.frienda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.frienda.db.food.FoodDAOService;
import com.diary.frienda.db.sentiment.SentimentDAOService;
import com.diary.frienda.response.data.SentimentRecommendationData;
import com.diary.frienda.response.data.sub.FoodData;

@Service
public class SentimentHandler {
	@Autowired
	SentimentDAOService sentimentDAO;
	
	@Autowired
	private FoodDAOService foodDAO;
	
	@Autowired
	EncryptHandler encrypt;
	
	@Autowired
	ClovaHandler clova;
	
	private static final String Negative = "negative"; 
	
	public boolean isNegativeSentiment(String sentiment) throws Exception {
		return sentimentDAO.getApprSentimentByDetailedSentiment(sentiment).equals(Negative);
		
	}
	
	public SentimentRecommendationData getSentimentData(String user_id, String encrypted_diary) throws Exception {
		String clova_sentiment = clova.getDocumentFromDiary(encrypt.decryptContent(user_id, encrypted_diary)).getSentiment();
		
		return new SentimentRecommendationData(clova_sentiment, sentimentDAO.getDetailedSentimentByApprSentiment(clova_sentiment));
	}
	
	public FoodData getFood(String sentiment) throws Exception {
		return foodDAO.getFoodBySentiment(sentiment).convertToData();
	}
	
}
