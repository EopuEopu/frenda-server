package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.clova.Document;
import com.diary.frienda.db.diary.Diary;
import com.diary.frienda.db.diary.DiaryDAOService;
import com.diary.frienda.db.diarySentiment.DiarySentiment;
import com.diary.frienda.db.diarySentiment.DiarySentimentDAOService;
import com.diary.frienda.db.food.FoodDAOService;
import com.diary.frienda.db.request.AddFavorValueRequest;
import com.diary.frienda.db.sentiment.SentimentDAOService;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.ClovaHandler;
import com.diary.frienda.handler.DiaryHandler;
import com.diary.frienda.handler.UserHandler;
import com.diary.frienda.handler.EncryptHandler;
import com.diary.frienda.request.DiaryInsertion;
import com.diary.frienda.request.DiaryUpdate;
import com.diary.frienda.request.DiaryView;
import com.diary.frienda.response.Response;
import com.diary.frienda.response.data.DiaryInsertionData;
import com.diary.frienda.response.data.DiaryUpdateData;
import com.diary.frienda.response.data.DiaryAllSentiments;
import com.diary.frienda.response.data.DiaryViewData;
import com.diary.frienda.response.data.FavorData;
import com.diary.frienda.response.data.FoodData;
import com.diary.frienda.response.data.MonthlyDiariesDataList;

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
	private FoodDAOService foodDAO;
	
	@Autowired
	private SentimentDAOService sentimentDAO;
	
	@Autowired
	private ClovaHandler clovaHandler;
	
	@Autowired
	private EncryptHandler encryptHandler;
	
	Response res = null;
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public Response insertDiary(@RequestParam("userId") String user_id, 
			@RequestBody final DiaryInsertion diary) throws Exception {
		
		String user_key = encryptHandler.decryptContent(user_id, diary.getUser_key());

		if(userDAO.getUserValidation(user_id, user_key) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		
		diaryDAO.insertDiary(new Diary(user_id, diary.getContent()));
		int diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		
		if(sentimentDAO.getApprSentimentByDetailedSentiment(diary.getUser_selected_sentiment()).compareTo("negative") == 0)
			userDAO.addNegativeDiaryCount(user_id);
		
		diarySentimentDAO.insertDiaryUserSentiment(new DiarySentiment(diary_id, diary.getUser_selected_sentiment()));
		
		userFriendStatusDAO.addFavorValue(new AddFavorValueRequest(user_id, 1));
		
		res = new Response(200, "일기가 저장되었습니다.",
							new DiaryInsertionData(diary_id, diaryDAO.getLatestDiaryDateByUserId(user_id),
													UserHandler.getPortalOpen(userDAO.getNegativeDiaryCountByUserId(user_id)),
													new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 1),
													new FoodData(foodDAO.getFoodBySentiment(diary.getUser_selected_sentiment()))));
		
		return res;
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.POST)
	public Response viewDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
												@RequestBody final DiaryView diary_view) throws Exception{
		
		String user_key = encryptHandler.decryptContent(user_id, diary_view.getUser_key());
		
		if(userDAO.getUserValidation(user_id, user_key) < 1) {
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		}
		
		Diary diary = diaryDAO.getDiaryByUserIdAndDiaryId(user_id, diary_id);
		
		DiarySentiment diary_sent = diarySentimentDAO.getDiarySentimentByDiaryId(Integer.parseInt(diary_id));
		DiaryViewData diaryData = new DiaryViewData(diary.getContent(), diary.getCommitted_date(),
														new DiaryAllSentiments(diary_sent));

		
		res = new Response(200, "일기를 성공적으로 가져왔습니다.", diaryData);
		return res;
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.GET)
	public Response viewAllDiaries(@RequestParam("userId") String user_id,
									@RequestParam("yearMonth") String year_month) throws Exception {
		if(userDAO.checkUserId(user_id) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);		
						
		res = new Response(200, "일기를 성공적으로 가져왔습니다.", 
							new MonthlyDiariesDataList(diaryDAO.getMonthlyDiariesByUserIdAndDate(user_id, year_month)));
		return res;
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.PATCH)
	public Response updateDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
									@RequestBody final DiaryUpdate diary) throws Exception {
		
		String user_key = encryptHandler.decryptContent(user_id, diary.getUser_key());
		if(userDAO.getUserValidation(user_id, user_key) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		
		diaryDAO.updateDiaryContent(new Diary(user_id, Integer.parseInt(diary_id), diary.getContent()));
						
		res = new Response(200, "일기를 성공적으로 수정했습니다.", new DiaryUpdateData(Integer.parseInt(diary_id)));
		return res;
	}
	
	
}
