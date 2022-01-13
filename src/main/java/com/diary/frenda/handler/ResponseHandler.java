package com.diary.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.frenda.response.Data;
import com.diary.frenda.response.Response;
import com.diary.frenda.response.data.DiaryInsertionData;
import com.diary.frenda.response.data.UserInfoData;

@Service
public class ResponseHandler {
	@Autowired
	UserHandler userH;
	
	@Autowired
	DiaryHandler diaryH;
	
	@Autowired
	SentimentHandler sentimentH;
	
	public static Response failResponse() {
		return new Response(500, "FAIL", null);
	}
	
	public static Response failResponse(Data data) {
		return new Response(500, "FAIL", data);
	}
	
	public static Response successResponse(Data data) {
		return new Response(200, "SUCCESS", data);
	}
	
	public UserInfoData logInData(String user_id) throws Exception {
		return new UserInfoData(diaryH.getLatestDiaryInfoes(user_id), userH.getFriendStatus(user_id), userH.getPortalOpen(user_id));
	}
	
	public DiaryInsertionData insertDiaryData(String user_id, String sentiment) throws Exception {
		return new DiaryInsertionData(diaryH.getLatestDiaryInfoes(user_id), userH.getFriendStatus(user_id), 
										userH.getPortalOpen(user_id), sentimentH.getFood(sentiment));
	}
	
//	private BlahData makeFrameData(String user_id) {
//		return new BlahData(diaryH.getLatestDiaryInfoes(user_id), 
//							userH.getFriendStatus(user_id), 
//							userH.getPortalOpen(user_id));
//	}
}
