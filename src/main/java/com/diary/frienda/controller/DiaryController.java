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
import com.diary.frienda.db.request.AddFavorValueRequest;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.ClovaHandler;
import com.diary.frienda.handler.DiaryHandler;
import com.diary.frienda.handler.EncryptHandler;
import com.diary.frienda.request.DiaryInsertion;
import com.diary.frienda.request.DiaryView;
import com.diary.frienda.response.DiaryInsertionResponse;
import com.diary.frienda.response.DiaryViewContent;
import com.diary.frienda.response.DiaryViewResponse;

@RestController
public class DiaryController {
	@Autowired
	private DiaryDAOService diaryDAO;
	
	@Autowired
	private DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	private UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	private ClovaHandler clovaHandler;
	
	@Autowired
	private EncryptHandler encryptHandler;
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public DiaryInsertionResponse insertDiary(@RequestParam("userId") String user_id, 
			@RequestBody final DiaryInsertion diary) throws Exception {
		String user_key = encryptHandler.decryptContent(user_id, diary.getUser_key());

		if(userDAO.getUserValidation(user_id, user_key) < 1) {
			return new DiaryInsertionResponse(500, -1, "저장되지 않은 사용자입니다.", -1, false);
		}
		
		diaryDAO.insertDiary(new Diary(user_id, diary.getContent()));
		int diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		
		Document dc = clovaHandler.getDocumentFromDiary(encryptHandler.decryptContent(user_id, diary.getContent()));
		
		if(dc.getSentiment().equals("negative"))
			userDAO.addNegativeDiaryCount(user_id);
		
		Confidence conf = roundValues(dc.getConfidence());
		diarySentimentDAO.insertDiarySentiment(new DiarySentiment(diary_id, dc.getSentiment(), 
				conf.getNegative(), conf.getPositive(), conf.getNeutral(), diary.getUser_selected_sentiment()));
		
		userFriendStatusDAO.addFavorValue(new AddFavorValueRequest(user_id, 1));
		
		int favor_value = userFriendStatusDAO.getFavorValueByUserId(user_id);
		
		DiaryInsertionResponse res = new DiaryInsertionResponse(200, diary_id, "일기가 저장되었습니다.", favor_value, 
				DiaryHandler.getPortalOpen(userDAO.getNegativeDiaryCountByUserId(user_id)));
		
		return res;
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.POST)
	public DiaryViewResponse viewDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
												@RequestBody final DiaryView diary_view) throws Exception{
		DiaryViewResponse res = null;
		DiaryViewContent content = null;
		
		String user_key = encryptHandler.decryptContent(user_id, diary_view.getUser_key());
		
		if(userDAO.getUserValidation(user_id, user_key) < 1) {
			return new DiaryViewResponse(500, "저장되지 않은 사용자입니다.", null);
		}
		
		Diary diary = diaryDAO.getDiaryByUserIdAndDiaryId(user_id, diary_id);
		
		DiarySentiment diary_sent = diarySentimentDAO.getDiarySentimentByDiaryId(Integer.parseInt(diary_id));
		content = new DiaryViewContent(diary.getContent(), diary.getCommitted_date(), diary_sent.getSentiment(), 
										diary_sent.getNegative_value(), diary_sent.getPositive_value(), diary_sent.getNeutral_value(),
										diary_sent.getUser_selected_sentiment());
		
		res = new DiaryViewResponse(200, "일기를 성공적으로 가져왔습니다.", content);
		return res;
	}
	
	private Confidence roundValues(Confidence conf) {
		conf.setNegative(clovaHandler.doRound(conf.getNegative()));
		conf.setPositive(clovaHandler.doRound(conf.getPositive()));
		conf.setNeutral(clovaHandler.doRound(conf.getNeutral()));
		return conf;
	}
}
