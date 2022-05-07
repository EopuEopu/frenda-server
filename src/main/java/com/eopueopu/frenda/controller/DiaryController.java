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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "일기 CRUD 및 감정 추천용 API")
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

	@PostMapping(value = "/recommendation", headers = {"Content-type=application/json"})
	@ApiOperation(value = "일기 내용에 대한 감정 추천", notes = "일기 작성 완료 전 감정 추천을 위해 Clova Sentiment를 Call")
	@ApiResponses({
		@ApiResponse(code = 200, message = "감정 분석 후 추천 감정 제공 완료")
	})
	public Response recommendSentiment(String userId, @RequestBody final SentimentAnalysis sent) throws Exception {
		
    	user.isInvalidUser(userId, sent.getUser_key());
		
		return response.getForm(sentiment.getSentimentData(userId, sent.getContent()));
	}
	
	@PostMapping(value = "/new", headers = {"Content-type=application/json"})
	@ApiOperation(value = "일기 작성", notes = "일기 작성 완료 + 일기 작성 시 recommendation을 거치지 않은 경우 체크하여 일기 작성 완료")
	@ApiResponses({
		@ApiResponse(code = 200, message = "일기 저장 완료"),
		@ApiResponse(code = 3100, message = "동일한 날짜에 2개 이상의 일기 작성 시도"),
		@ApiResponse(code = 3101, message = "몬스터를 잡는 portal이 open되어 있을 때 일기 작성 시도"),
		@ApiResponse(code = 3102, message = "선택 범위 외의 감정(user_selected_sentiment) 입력 시도"),
	})
	public Response insert(String userId, @RequestBody final DiaryInsertion diaryData) throws Exception {
		
    	user.isInvalidUser(userId, diaryData.getUser_key());
		
		diary.insertDiary(userId, diaryData);
		
		return response.getForm(response.insertDiaryData(userId, diaryData.getUser_selected_sentiment()));
	}
	
	@PostMapping(value = "/view", headers = {"Content-type=application/json"})
	@ApiOperation(value = "일기 내용 열람", notes = "특정 날짜의 일기 내용 및 감정 등의 정보를 열람")
    @ApiImplicitParam(name = "diaryId", value = "다이어리 번호", required = true, dataType = "string", paramType = "query", defaultValue = "159")
	@ApiResponses({
		@ApiResponse(code = 200, message = "일기 불러오기 완료"),
		@ApiResponse(code = 3200, message = "존재하지 않는 일기 불러오기 시도"),
	})
	public Response viewDiary(String userId, String diaryId, @RequestBody final DiaryView diary_view) throws Exception{
		
    	user.isInvalidUser(userId, diary_view.getUser_key());
		
		return response.getForm(diary.getDiaryInfoes(userId, diaryId));
	}
	
    
    @GetMapping("/list")
    @ApiOperation(value = "일기 리스트 열람", notes = "년-월에 따라 작성한 일기를 리스트로 전달")
    @ApiImplicitParam(name = "yearMonth", value = "작성한 연도, 월", required = true, dataType = "string", paramType = "query", defaultValue = "2022-04")
    @ApiResponses({
		@ApiResponse(code = 200, message = "년-월에 맞는 일기 리스트 불러오기 완료")
	})
	public Response viewAllDiaries(String userId, String yearMonth) throws Exception {

		user.isNotPresentUser(userId);

		return response.getForm(diary.getMonthlyDiaries(userId, yearMonth));
	}
	
    @PatchMapping(value = "/edit-diary", headers = {"Content-type=application/json"})
    @ApiOperation(value = "일기 수정", notes = "과거의 일기 내용을 단순히 수정")
    @ApiImplicitParam(name = "diaryId", value = "다이어리 번호", required = true, 
							dataType = "string", paramType = "query", defaultValue = "159")
    @ApiResponses({
		@ApiResponse(code = 200, message = "일기 수정 완료"),
		@ApiResponse(code = 3300, message = "존재하지 않는 일기 수정 시도"),
	})
	public Response update(String userId, String diaryId, @RequestBody final DiaryUpdate diaryData) throws Exception {
		
    	user.isInvalidUser(userId, diaryData.getUser_key());
		
    	int diary_id = Integer.parseInt(diaryId);
    	
    	diary.updateDiary(userId, diary_id, diaryData.getContent());
						
		return response.getForm(new DiaryUpdateData(Integer.parseInt(diaryId)));
	}
	
	
}
