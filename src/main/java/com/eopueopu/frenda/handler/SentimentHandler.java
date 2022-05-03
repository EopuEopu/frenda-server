package com.eopueopu.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.clovaTemp.ClovaTemp;
import com.eopueopu.frenda.db.clovaTemp.ClovaTempDAO;
import com.eopueopu.frenda.db.food.FoodDAO;
import com.eopueopu.frenda.db.sentiment.SentimentDAO;
import com.eopueopu.frenda.handler.util.ClovaHandler;
import com.eopueopu.frenda.handler.util.EncryptHandler;
import com.eopueopu.frenda.response.data.SentimentRecommendationData;
import com.eopueopu.frenda.response.data.sub.FoodData;

@Service
public class SentimentHandler {
	@Autowired
	private SentimentDAO sentimentDAO;
	
	@Autowired
	private FoodDAO foodDAO;
	
	@Autowired
	private ClovaTempDAO clovaTempDAO;
	
	@Autowired
	private EncryptHandler encrypt;
	
	@Autowired
	private ClovaHandler clova;
	
	private static final String Negative = "negative"; 
	
	public boolean isNegativeSentiment(String sentiment) throws Exception {
		return sentimentDAO.getApprSentimentByDetailedSentiment(sentiment).equals(Negative);
		
	}
	
	public SentimentRecommendationData getSentimentData(String user_id, String encrypted_diary) throws Exception {
		
		// document에서 clovaTemp로 변환하는 코드 작성 후 바로 insert 필요, clova_sentiment는 .getSentiment로 파라미터로 전달
		ClovaTemp clovaAnalyzed = clova.getDocumentFromDiary(encrypt.decryptContent(user_id, encrypted_diary).replaceAll("\n", " "))
										.convertDocToClovaTemp(user_id, encrypted_diary);
		
		clovaTempDAO.insertTodayTempData(clovaAnalyzed);
		
		String clova_sentiment = clovaAnalyzed.getSentiment();
		
		return new SentimentRecommendationData(clova_sentiment, sentimentDAO.getDetailedSentimentByApprSentiment(clova_sentiment));
	}
	
	public FoodData getFood(String sentiment) throws Exception {
		return foodDAO.getFoodBySentiment(sentiment).convertToData();
	}
	
}
