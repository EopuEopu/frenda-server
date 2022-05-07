package com.eopueopu.frenda.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.clova.Document;
import com.eopueopu.frenda.db.clovaTemp.ClovaTemp;
import com.eopueopu.frenda.db.clovaTemp.ClovaTempDAO;
import com.eopueopu.frenda.db.diary.Diary;
import com.eopueopu.frenda.db.diary.DiaryDAO;
import com.eopueopu.frenda.db.diarySentiment.DiarySentiment;
import com.eopueopu.frenda.db.diarySentiment.DiarySentimentDAO;
import com.eopueopu.frenda.exception.diary.InexistDiaryIdException;
import com.eopueopu.frenda.handler.util.ClovaHandler;
import com.eopueopu.frenda.handler.util.EncryptHandler;
import com.eopueopu.frenda.request.DiaryInsertion;
import com.eopueopu.frenda.response.data.DiaryViewData;
import com.eopueopu.frenda.response.data.MonthlyDiariesDataList;
import com.eopueopu.frenda.response.data.sub.DiaryAllSentiments;
import com.eopueopu.frenda.response.data.sub.NegativeSentimentCount;

@Service
public class DiaryHandler {
	@Autowired
	private DiaryDAO diaryDAO;
	
	@Autowired
	private DiarySentimentDAO diarySentimentDAO;
	
	@Autowired
	private ClovaTempDAO clovaTempDAO;
	
	@Autowired
	private UserHandler user;
	
	@Autowired
	private SentimentHandler sentiment;
	
	@Autowired
	private EncryptHandler encrypt;
	
	@Autowired
	private ClovaHandler clova;
	
	/**
	 * Get Diary Information
	 */
	public MonthlyDiariesDataList getMonthlyDiaries(String user_id, String year_month) throws Exception {
		return new MonthlyDiariesDataList(diaryDAO.getMonthlyDiariesByUserIdAndDate(user_id, year_month));
	}
	
	public DiaryViewData getDiaryInfoes(String user_id, String diary_id) throws Exception {
		Diary diary = diaryDAO.getDiaryByUserIdAndDiaryId(user_id, diary_id);
		DiarySentiment diarySentiment = diarySentimentDAO.getDiarySentimentByDiaryId(Integer.parseInt(diary_id));
		
		if(diary == null || diarySentiment == null)
			throw new InexistDiaryIdException();
		
		return new DiaryViewData(diary, new DiaryAllSentiments(diarySentiment));
	}
	
	public Diary getLatestDiaryInfoes(String user_id) throws Exception {
		return new Diary(diaryDAO.getDiaryIdByUserId(user_id), diaryDAO.getLatestDiaryDateByUserId(user_id));
	}
	
	public List<NegativeSentimentCount> getErasedNegativeFeelings(String user_id) throws Exception {
		return diarySentimentDAO.getNegativeSentimentCount(user_id);
	}
	
	/**
	 * Insert Diary
	 */
	public void insertDiary(String user_id, DiaryInsertion diary) throws Exception {
		
		insertDiaryInfoes(user_id, diary);
		
		if(sentiment.isNegativeSentiment(diary.getUser_selected_sentiment()))
			user.increaseNegativeDiarys(user_id);
		
		user.updateFriendFavor(user_id, 1);
	}
	
	/**
	 * Update Diary : 존재하지 않는 id로 수정을 할 때 throw Exception
	 */
	public void updateDiary(String user_id, int diary_id, String content) throws Exception {
		
		diaryDAO.updateDiaryContent(new Diary(user_id, diary_id, content));
	}
	
	// define new error : if clovaTemp is null -> clova url Call diarySentimentDAO : in finally block
	private int insertDiaryInfoes(String user_id, DiaryInsertion diary) throws Exception {
		diaryDAO.insertDiary(user_id, diary.getContent());
		int diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		
		ClovaTemp clovaTemp;
		Document doc;
		
		if((clovaTemp = clovaTempDAO.getTodayTempDataByUserId(user_id)) == null) {
			doc = clova.getDocumentFromDiary(encrypt.decryptContent(user_id, diary.getContent()).replaceAll("\n", " "));
		} else {
			doc = clovaTemp.convertClovaTempToDoc();
			clovaTempDAO.deleteTodayTempData(user_id);
		}
		
		diarySentimentDAO.insertDiarySentiment(new DiarySentiment(diary_id, diary.getUser_selected_sentiment(), doc));
		
		return diary_id;
	}
		
	
}
