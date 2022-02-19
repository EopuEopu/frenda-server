package com.eopueopu.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.clova.Document;
import com.eopueopu.frenda.db.clovaTemp.ClovaTemp;
import com.eopueopu.frenda.db.clovaTemp.ClovaTempDAO;
import com.eopueopu.frenda.db.diary.Diary;
import com.eopueopu.frenda.db.diary.DiaryDAOService;
import com.eopueopu.frenda.db.diarySentiment.DiarySentiment;
import com.eopueopu.frenda.db.diarySentiment.DiarySentimentDAOService;
import com.eopueopu.frenda.request.DiaryInsertion;
import com.eopueopu.frenda.response.data.DiaryViewData;
import com.eopueopu.frenda.response.data.MonthlyDiariesDataList;
import com.eopueopu.frenda.response.data.sub.DiaryAllSentiments;

@Service
public class DiaryHandler {
	@Autowired
	private DiaryDAOService diaryDAO;
	
	@Autowired
	private DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	private ClovaTempDAO clovaTempDAO;
	
	@Autowired
	private EncryptHandler encryptH;
	
	@Autowired
	private ClovaHandler clovaH;
	
	public MonthlyDiariesDataList getMonthlyDiaries(String user_id, String year_month) throws Exception {
		return new MonthlyDiariesDataList(diaryDAO.getMonthlyDiariesByUserIdAndDate(user_id, year_month));
	}
	
	// define new error : if clovaTemp is null -> clova url Call diarySentimentDAO : in finally block
	public int insertDiaryInfoes(String user_id, DiaryInsertion diary) throws Exception {
		diaryDAO.insertDiary(user_id, diary.getContent());
		int diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		
		ClovaTemp clovaTemp;
		Document doc;
		
		if((clovaTemp = clovaTempDAO.getTodayTempDataByUserId(user_id)) == null) {
			doc = clovaH.getDocumentFromDiary(encryptH.decryptContent(user_id, diary.getContent()));
		} else {
			doc = clovaTemp.convertClovaTempToDoc();
			clovaTempDAO.deleteTodayTempData(user_id);
		}
		
		diarySentimentDAO.insertDiarySentiment(new DiarySentiment(diary_id, diary.getUser_selected_sentiment(), doc));
		
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
