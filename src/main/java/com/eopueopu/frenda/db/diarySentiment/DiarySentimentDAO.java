package com.eopueopu.frenda.db.diarySentiment;

import java.util.List;

import com.eopueopu.frenda.response.data.sub.NegativeSentimentCount;

public interface DiarySentimentDAO {
	public DiarySentiment getDiarySentimentByDiaryId(int diary_id) throws Exception;
	
	public List<NegativeSentimentCount> getNegativeSentimentCount(String user_id) throws Exception;
	
	public void insertDiarySentiment(DiarySentiment diarySentiment) throws Exception;
	
	public void insertDiaryUserSentiment(DiarySentiment diarySentiment) throws Exception;
}
