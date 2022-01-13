package com.diary.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.frenda.db.diary.Diary;
import com.diary.frenda.db.diary.DiaryDAOService;
import com.diary.frenda.db.diarySentiment.DiarySentiment;
import com.diary.frenda.db.diarySentiment.DiarySentimentDAOService;
import com.diary.frenda.request.DiaryInsertion;
import com.diary.frenda.response.data.DiaryViewData;
import com.diary.frenda.response.data.MonthlyDiariesDataList;
import com.diary.frenda.response.data.sub.DiaryAllSentiments;

@Service
public class DiaryHandler {
	@Autowired
	DiaryDAOService diaryDAO;
	
	@Autowired
	DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	EncryptHandler encryptH;
	
	@Autowired
	ClovaHandler clovaH;
	
	public MonthlyDiariesDataList getMonthlyDiaries(String user_id, String year_month) throws Exception {
		return new MonthlyDiariesDataList(diaryDAO.getMonthlyDiariesByUserIdAndDate(user_id, year_month));
	}
	
	public int insertDiaryInfoes(String user_id, DiaryInsertion diary) throws Exception {
		diaryDAO.insertDiary(user_id, diary.getContent());
		int diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		diarySentimentDAO.insertDiarySentiment(new DiarySentiment(diary_id, diary.getUser_selected_sentiment(), 
																	clovaH.getDocumentFromDiary(encryptH.decryptContent(user_id, diary.getContent()))));
		
		return diary_id;
	}
	
	public DiaryViewData getDiaryInfoes(String user_id, String diary_id) throws Exception {
		return new DiaryViewData(diaryDAO.getDiaryByUserIdAndDiaryId(user_id, diary_id),
							new DiaryAllSentiments(diarySentimentDAO.getDiarySentimentByDiaryId(Integer.parseInt(diary_id))));
	}
	
	public Diary getLatestDiaryInfoes(String user_id) throws Exception {
		return new Diary(diaryDAO.getDiaryIdByUserId(user_id), diaryDAO.getLatestDiaryDateByUserId(user_id));
	}
	
}
