package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.clova.Confidence;
import com.diary.frienda.clova.Document;
import com.diary.frienda.db.diary.Diary;
import com.diary.frienda.db.diary.DiaryDAOService;
import com.diary.frienda.db.diarySentiment.DiarySentiment;
import com.diary.frienda.db.diarySentiment.DiarySentimentDAOService;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.handler.ClovaHandler;
import com.diary.frienda.handler.EncryptHandler;
import com.diary.frienda.request.DiaryInsertion;
import com.diary.frienda.request.DiaryView;
import com.diary.frienda.response.DiaryInsertionResponse;
import com.diary.frienda.response.DiaryViewContent;
import com.diary.frienda.response.DiaryViewResponse;

@RestController
@PropertySource("classpath:/properties/frienda.properties")
public class DiaryController {
	@Autowired
	private DiaryDAOService diaryDAO;
	
	@Autowired
	private DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	private ClovaHandler ch;
	
	@Value("${frienda.diary.start}")
	private int subStart;
	
	@Value("${frienda.diary.end}")
	private int subEnd;
	
	
	//TODO : substring�ϴ� byte�� properties�� ������
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public DiaryInsertionResponse insertDiary(@RequestParam("userId") String user_id, @RequestBody final DiaryInsertion diary) {
		int diary_id = 0;
		int favor_value = 0;
		
		try {
			EncryptHandler eh = new EncryptHandler(user_id.substring(subStart, subEnd));
			String user_key = eh.decryptContent(diary.getUser_key());

			if(userDAO.getUserValidation(user_id, user_key) < 1) {
				return new DiaryInsertionResponse(500, diary_id, "저장되지 않은 사용자입니다.", favor_value);
			}
			
			diaryDAO.insertDiary(new Diary(user_id, diary.getContent()));
			diary_id = diaryDAO.getDiaryIdByUserId(user_id);
			
			Document dc = ch.getDocumentFromDiary(eh.decryptContent(diary.getContent()));
			Confidence conf = roundValues(dc.getConfidence());
			diarySentimentDAO.insertDiarySentiment(new DiarySentiment(diary_id, dc.getSentiment(), 
					conf.getNegative(), conf.getPositive(), conf.getNeutral(), diary.getUser_selected_sentiment()));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DiaryInsertionResponse res = new DiaryInsertionResponse(200, diary_id, "일기가 저장되었습니다..", favor_value);
		return res;
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.POST)
	public DiaryViewResponse viewDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
												@RequestBody final DiaryView diary_view) {
		DiaryViewResponse res = null;
		DiaryViewContent content = null;
		
		try {
			EncryptHandler eh = new EncryptHandler(user_id);
			String user_key = eh.decryptContent(diary_view.getUser_key());
			
			if(userDAO.getUserValidation(user_id, user_key) < 1) {
				return new DiaryViewResponse(500, "저장되지 않은 사용자입니다.", null);
			}
			
			Diary diary = diaryDAO.getDiaryByUserIdAndDiaryId(user_id, diary_id);
			
			DiarySentiment diary_sent = diarySentimentDAO.getDiarySentimentByDiaryId(Integer.parseInt(diary_id));
			content = new DiaryViewContent(diary.getContent(), diary.getCommitted_date(), diary_sent.getSentiment(), 
											diary_sent.getNegative_value(), diary_sent.getPositive_value(), diary_sent.getNeutral_value(),
											diary_sent.getUser_selected_sentiment());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		res = new DiaryViewResponse(200, "일기를 성공적으로 가져왔습니다.", content);
		return res;
	}
	
	private Confidence roundValues(Confidence conf) {
		conf.setNegative(ch.doRound(conf.getNegative()));
		conf.setPositive(ch.doRound(conf.getPositive()));
		conf.setNeutral(ch.doRound(conf.getNeutral()));
		return conf;
	}
}
