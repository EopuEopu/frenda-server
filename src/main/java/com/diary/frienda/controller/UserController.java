package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.db.diary.DiaryDAOService;
import com.diary.frienda.db.user.User;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatus;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.UserHandler;
import com.diary.frienda.handler.EncryptHandler;
import com.diary.frienda.handler.ResponseHandler;
import com.diary.frienda.response.Response;
import com.diary.frienda.response.data.UserFriendStatusData;
import com.diary.frienda.response.data.UserInfoData;
import com.diary.frienda.response.data.UserKeyData;

@RestController
public class UserController {
	@Autowired
	UserDAOService userDAO;
	
	@Autowired
	UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	DiaryDAOService diaryDAO;
	
	@Autowired
	EncryptHandler encryptHandler;
	
	@RequestMapping(value = "/new-user", method = RequestMethod.GET)
	public Response makeNewUser(@RequestParam("userId") String user_id) throws Exception {
		if(userDAO.checkUserId(user_id) > 0)
			return ResponseHandler.failResponse();
		
		String user_key = encryptHandler.encryptContent(user_id, UserHandler.makeUserKey());
		userDAO.insertNewUser(new User(user_id, user_key));
		
		return ResponseHandler.successResponse(new UserKeyData(user_key));
	}
	
	@RequestMapping(value = "/new-friend", method = RequestMethod.GET)
	public Response makeNewFriend(@RequestParam("userId") String user_id, 
			@RequestParam("friendName") String friend_name) throws Exception {
		
		if(userDAO.checkUserId(user_id) < 1)
			return ResponseHandler.failResponse();
		
		if(userFriendStatusDAO.checkUserFriendByUserId(user_id) > 0)
			return ResponseHandler.failResponse(new UserFriendStatusData(userFriendStatusDAO.getUserFriendStatusByUserId(user_id)));
		
		userFriendStatusDAO.insertNewFriend(user_id, friend_name);
		
		return ResponseHandler.successResponse(new UserFriendStatusData(userFriendStatusDAO.getUserFriendStatusByUserId(user_id)));
	}
	
	@RequestMapping(value = "/user-key", method = RequestMethod.GET)
	public Response getUserKey(@RequestParam("userId") String user_id) throws Exception {
		
		if(userDAO.checkUserId(user_id) < 1)
			return ResponseHandler.failResponse();
		
		String user_key = encryptHandler.encryptContent(user_id, userDAO.getUserKey(user_id));
		
		return ResponseHandler.successResponse(new UserKeyData(user_key));
	}
	
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Response getUserStatus(@RequestParam("userId") String user_id) throws Exception {
		int diary_id = -1;
		
		if(userDAO.checkUserId(user_id) < 1)
			return ResponseHandler.failResponse();
		
		diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		UserFriendStatus friend_info = userFriendStatusDAO.getUserFriendStatusByUserId(user_id);
		
		boolean portal_open = UserHandler.getPortalOpen(userDAO.getNegativeDiaryCountByUserId(user_id));
		
		return ResponseHandler.successResponse(new UserInfoData(friend_info, diary_id, 
													diaryDAO.getLatestDiaryDateByUserId(user_id) , portal_open));
	}
}
