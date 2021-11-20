package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.db.diary.DiaryDAOService;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatus;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.UserHandler;
import com.diary.frienda.handler.EncryptHandler;
import com.diary.frienda.response.Response;
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
	
	Response res = null;
	
	@RequestMapping(value = "/user-key", method = RequestMethod.GET)
	public Response getUserKey(@RequestParam("userId") String user_id) throws Exception {
		if(userDAO.checkUserId(user_id) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		
		String user_key = encryptHandler.encryptContent(user_id, userDAO.getUserKey(user_id));
		res = new Response(200, "사용자 정보를 성공적으로 불러왔습니다.", new UserKeyData(user_key));
		
		return res;
	}
	
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Response getUserStatus(@RequestParam("userId") String user_id) throws Exception {
		int diary_id = -1;
		
		if(userDAO.checkUserId(user_id) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		
		diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		UserFriendStatus friend_info = userFriendStatusDAO.getUserFriendStatusByUserId(user_id);
		
		boolean portal_open = UserHandler.getPortalOpen(userDAO.getNegativeDiaryCountByUserId(user_id));
		res = new Response(200, "사용자 정보를 성공적으로 불러왔습니다.", 
				new UserInfoData(friend_info.getFriend_name(), friend_info.getFavor_value(), diary_id, portal_open));
		
		return res;
	}
}
