package com.diary.frienda.db.diarySentiment;

public interface DiarySentimentDAO {
	public DiarySentiment getDiarySentimentByDiaryId(int diary_id) throws Exception;
	public void insertDiarySentiment(DiarySentiment diarySentiment) throws Exception;
	public void insertDiaryUserSentiment(DiarySentiment diarySentiment) throws Exception;
}
