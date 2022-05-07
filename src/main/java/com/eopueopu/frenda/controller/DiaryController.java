package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.DiaryHandler;
import com.eopueopu.frenda.handler.SentimentHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.request.DiaryInsertion;
import com.eopueopu.frenda.request.DiaryUpdate;
import com.eopueopu.frenda.request.DiaryView;
import com.eopueopu.frenda.request.SentimentAnalysis;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.DiaryUpdateData;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

// handler간 상속관계 설정 필요
//@Api(tags = {"일기 CRUD 및 감정 추천용 API"})
@RestController
@RequestMapping("/diary")
public class DiaryController {
	@Autowired
	private UserHandler user;
	
	@Autowired
	private DiaryHandler diary;
	
	@Autowired
	private SentimentHandler sentiment;
	
	@Autowired
	private ResponseHandler response;

	@PostMapping("/recommendation")
//	@ApiOperation(value = "일기 내용에 대한 감정 추천", notes = "일기 작성 완료 전 감정 추천을 위해 Clova Sentiment를 Call")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, 
        					dataType = "string", paramType = "query", defaultValue = "abcdefghijklmnop")
    })
	public Response recommendSentiment(String userId, @RequestBody final SentimentAnalysis sent) throws Exception {
		
    	user.isInvalidUser(userId, sent.getUser_key());
		
		return response.getForm(sentiment.getSentimentData(userId, sent.getContent()));
	}
	
    @PostMapping("/new")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, 
        					dataType = "string", paramType = "query", defaultValue = "abcdefghijklmnop")
    })
	public Response insert(String userId, @RequestBody final DiaryInsertion diaryData) throws Exception {
		
    	user.isInvalidUser(userId, diaryData.getUser_key());
		
		diary.insertDiary(userId, diaryData);
		
		return response.getForm(response.insertDiaryData(userId, diaryData.getUser_selected_sentiment()));
	}
	
    @PostMapping("/view")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, 
        					dataType = "string", paramType = "query", defaultValue = "abcdefghijklmnop"),
        @ApiImplicitParam(name = "diaryId", value = "다이어리 번호", required = true, 
							dataType = "string", paramType = "query", defaultValue = "159")
    })
	public Response viewDiary(String userId, String diaryId, @RequestBody final DiaryView diary_view) throws Exception{
		
    	user.isInvalidUser(userId, diary_view.getUser_key());
		
		return response.getForm(diary.getDiaryInfoes(userId, diaryId));
	}
	
    
    @GetMapping("/list")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, 
        					dataType = "string", paramType = "query", defaultValue = "abcdefghijklmnop"),
        @ApiImplicitParam(name = "yearMonth", value = "작성한 연도, 월", required = true, 
							dataType = "string", paramType = "query", defaultValue = "2022-04")
    })
	public Response viewAllDiaries(String userId, String yearMonth) throws Exception {

		user.isNotPresentUser(userId);

		return response.getForm(diary.getMonthlyDiaries(userId, yearMonth));
	}
	
    
    @PatchMapping("/edit-diary")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, 
        					dataType = "string", paramType = "query", defaultValue = "abcdefghijklmnop"),
        @ApiImplicitParam(name = "diaryId", value = "다이어리 번호", required = true, 
							dataType = "string", paramType = "query", defaultValue = "159")
    })
	public Response update(String userId, String diaryId, @RequestBody final DiaryUpdate diaryData) throws Exception {
		
    	user.isInvalidUser(userId, diaryData.getUser_key());
		
    	int diary_id = Integer.parseInt(diaryId);
    	
    	diary.updateDiary(userId, diary_id, diaryData.getContent());
						
		return response.getForm(new DiaryUpdateData(Integer.parseInt(diaryId)));
	}
	
	
}
