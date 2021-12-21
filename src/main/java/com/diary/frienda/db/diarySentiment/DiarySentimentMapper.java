package com.diary.frienda.db.diarySentiment;

import java.util.List;

import com.diary.frienda.response.data.NegativeSentimentCount;

public interface DiarySentimentMapper {
	public DiarySentiment getDiarySentimentByDiaryId(int diary_id) throws Exception;
	public List<NegativeSentimentCount> getNegativeSentimentCount(String user_id) throws Exception;
	public void insertDiarySentiment(DiarySentiment diarySentiment) throws Exception;
	public void insertDiaryUserSentiment(DiarySentiment diarySentiment) throws Exception;
}
