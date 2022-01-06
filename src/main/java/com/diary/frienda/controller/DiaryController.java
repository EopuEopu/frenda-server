package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.db.diary.Diary;
import com.diary.frienda.db.diary.DiaryDAOService;
import com.diary.frienda.db.food.FoodDAOService;
import com.diary.frienda.db.request.AddFavorValueRequest;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.DiaryHandler;
import com.diary.frienda.handler.UserHandler;
import com.diary.frienda.handler.ResponseHandler;
import com.diary.frienda.handler.SentimentHandler;
import com.diary.frienda.request.DiaryInsertion;
import com.diary.frienda.request.DiaryUpdate;
import com.diary.frienda.request.DiaryView;
import com.diary.frienda.response.Response;
import com.diary.frienda.response.data.DiaryInsertionData;
import com.diary.frienda.response.data.DiaryUpdateData;
import com.diary.frienda.response.data.sub.FavorData;
import com.diary.frienda.response.data.sub.FoodData;

@RestController
public class DiaryController {
	@Autowired
	private DiaryDAOService diaryDAO;
	
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	private UserHandler userH;
	
	@Autowired
	private DiaryHandler diaryH;
	
	@Autowired
	private SentimentHandler sentimentH;
	
	@Autowired
	ResponseHandler responseH;

	@RequestMapping(value = "/recommendation", method = RequestMethod.POST)
	public Response recommendSentiment(@RequestParam("userId") String user_id, 
			@RequestBody final DiaryInsertion diary) throws Exception {
		
		if(userH.isInvalidUser(user_id, diary.getUser_key()))
			return ResponseHandler.failResponse();
		
		return ResponseHandler.successResponse(sentimentH.getSentimentData(user_id, diary.getContent()));
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public Response insertDiary(@RequestParam("userId") String user_id, 
									@RequestBody final DiaryInsertion diary) throws Exception {
		
		if(userH.isInvalidUser(user_id, diary.getUser_key()))
			return ResponseHandler.failResponse();
		
		// TODO : insertionAction method로 묶어버리기
		diaryH.insertDiaryInfoes(user_id, diary);
		
		if(sentimentH.isNegativeSentiment(diary.getUser_selected_sentiment()))
			userDAO.addNegativeDiaryCount(user_id);
			
		userH.updateFriendFavor(user_id, 1);
		
		return ResponseHandler.successResponse(responseH.insertDiaryData(user_id, diary.getUser_selected_sentiment()));
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.POST)
	public Response viewDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
												@RequestBody final DiaryView diary_view) throws Exception{
		
		if(userH.isInvalidUser(user_id, diary_view.getUser_key()))
			return ResponseHandler.failResponse();

		return ResponseHandler.successResponse(diaryH.getDiaryInfoes(user_id, diary_id));
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.GET)
	public Response viewAllDiaries(@RequestParam("userId") String user_id,
									@RequestParam("yearMonth") String year_month) throws Exception {
		
		if(userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();		
						
		return ResponseHandler.successResponse(diaryH.getMonthlyDiaries(user_id, year_month));
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.PATCH)
	public Response updateDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
									@RequestBody final DiaryUpdate diary) throws Exception {
		
		if(userH.isInvalidUser(user_id, diary.getUser_key()))
			return ResponseHandler.failResponse();
		
		diaryDAO.updateDiaryContent(new Diary(user_id, Integer.parseInt(diary_id), diary.getContent()));
						
		return ResponseHandler.successResponse(new DiaryUpdateData(Integer.parseInt(diary_id)));
	}
	
	
}
