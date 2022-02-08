package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.db.diary.Diary;
import com.eopueopu.frenda.db.diary.DiaryDAOService;
import com.eopueopu.frenda.db.user.UserDAOService;
import com.eopueopu.frenda.handler.DiaryHandler;
import com.eopueopu.frenda.handler.ResponseHandler;
import com.eopueopu.frenda.handler.SentimentHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.request.DiaryInsertion;
import com.eopueopu.frenda.request.DiaryUpdate;
import com.eopueopu.frenda.request.DiaryView;
import com.eopueopu.frenda.request.SentimentAnalysis;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.DiaryUpdateData;

// handler간 상속관계 설정 필요
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
	private ResponseHandler responseH;

	@RequestMapping(value = "/recommendation", method = RequestMethod.POST)
	public Response recommendSentiment(@RequestParam("userId") String user_id, 
			@RequestBody final SentimentAnalysis sent) throws Exception {
		
		if(userH.isInvalidUser(user_id, sent.getUser_key()))
			return responseH.failResponse();
		
		return responseH.successResponse(sentimentH.getSentimentData(user_id, sent.getContent()));
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.POST)
	public Response insertDiary(@RequestParam("userId") String user_id, 
									@RequestBody final DiaryInsertion diary) throws Exception {
		
		if(userH.isInvalidUser(user_id, diary.getUser_key()))
			return responseH.failResponse();
		
		// TODO : insertionAction method로 묶어버리기
		diaryH.insertDiaryInfoes(user_id, diary);
		
		if(sentimentH.isNegativeSentiment(diary.getUser_selected_sentiment()))
			userDAO.addNegativeDiaryCount(user_id);
			
		userH.updateFriendFavor(user_id, 1);
		
		return responseH.successResponse(responseH.insertDiaryData(user_id, diary.getUser_selected_sentiment()));
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.POST)
	public Response viewDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
												@RequestBody final DiaryView diary_view) throws Exception{
		
		if(userH.isInvalidUser(user_id, diary_view.getUser_key()))
			return responseH.failResponse();

		return responseH.successResponse(diaryH.getDiaryInfoes(user_id, diary_id));
	}
	
	@RequestMapping(value = "/diary/list", method = RequestMethod.GET)
	public Response viewAllDiaries(@RequestParam("userId") String user_id,
									@RequestParam("yearMonth") String year_month) throws Exception {
		
		if(userH.isNotPresentUser(user_id))
			return responseH.failResponse();		
						
		return responseH.successResponse(diaryH.getMonthlyDiaries(user_id, year_month));
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.PATCH)
	public Response updateDiary(@RequestParam("userId") String user_id, @RequestParam("diaryId") String diary_id,
									@RequestBody final DiaryUpdate diary) throws Exception {
		
		if(userH.isInvalidUser(user_id, diary.getUser_key()))
			return responseH.failResponse();
		
		diaryDAO.updateDiaryContent(new Diary(user_id, Integer.parseInt(diary_id), diary.getContent()));
						
		return responseH.successResponse(new DiaryUpdateData(Integer.parseInt(diary_id)));
	}
	
	
}