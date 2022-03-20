package com.eopueopu.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.exception.user.NotFoundFriendException;
import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.DiaryInsertionData;
import com.eopueopu.frenda.response.data.UserInfoData;


// TODO : using interface, extends
@Service
public class ResponseHandler {
	@Autowired
	UserHandler userH;
	
	@Autowired
	DiaryHandler diaryH;
	
	@Autowired
	SentimentHandler sentimentH;
	
	public Response failResponse(String msg) {
		return new Response(500, msg, null);
	}
	
	public Response failResponse(String msg, Data data) {
		return new Response(500, msg, data);
	}
	
	public Response successResponse(Data data) {
		return new Response(200, "SUCCESS", data);
	}
	
	public UserInfoData logInData(String user_id) throws Exception {
		UserFriendStatus friend = userH.getFriendStatus(user_id);
		if(friend == null) {
			throw new NotFoundFriendException();
		}
		
		return new UserInfoData(diaryH.getLatestDiaryInfoes(user_id), friend, 
				userH.getPortalOpen(user_id), userH.isFavorIncreased(user_id));
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
