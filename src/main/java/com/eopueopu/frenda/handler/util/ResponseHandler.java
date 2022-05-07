package com.eopueopu.frenda.handler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.exception.user.NotFoundFriendException;
import com.eopueopu.frenda.handler.DiaryHandler;
import com.eopueopu.frenda.handler.SentimentHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.DiaryInsertionData;
import com.eopueopu.frenda.response.data.UserInfoData;
import com.eopueopu.frenda.response.data.error.ErrorData;


// TODO : using interface, extends
@Service
public class ResponseHandler {
	@Autowired
	private UserHandler userH;
	
	@Autowired
	private DiaryHandler diaryH;
	
	@Autowired
	private SentimentHandler sentimentH;
	
	public Response getForm(Data data) {
		return new Response(200, data instanceof ErrorData ? "Fail" : "Success", data);
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
}
