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
import com.diary.frienda.handler.DiaryHandler;
import com.diary.frienda.response.UserInfoData;
import com.diary.frienda.response.UserInfoResponse;

@RestController
public class UserController {
	@Autowired
	UserDAOService userDAO;
	
	@Autowired
	UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	DiaryDAOService diaryDAO;
	
	@RequestMapping(value = "/user-key", method = RequestMethod.GET)
	public void getUserKey(@RequestParam("userId") String user_id) throws Exception {
		String user_key = userDAO.getUserKey(user_id);
	}
	
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public UserInfoResponse getUserStatus(@RequestParam("userId") String user_id) throws Exception {
		UserInfoResponse res = null;
		int diary_id = -1;
		
		//TODO : userID로 이미 존재하는 유저인지 판별하는 if문 생성
		
		diary_id = diaryDAO.getDiaryIdByUserId(user_id);
		UserFriendStatus friend_info = userFriendStatusDAO.getUserFriendStatusByUserId(user_id);
		
		boolean portal_open = DiaryHandler.getPortalOpen(userDAO.getNegativeDiaryCountByUserId(user_id));
		res = new UserInfoResponse(200, "사용자 정보를 성공적으로 불러왔습니다.", 
				new UserInfoData(friend_info.getFriend_name(), friend_info.getFavor_value(), diary_id, portal_open));
		
		return res;
	}
}
