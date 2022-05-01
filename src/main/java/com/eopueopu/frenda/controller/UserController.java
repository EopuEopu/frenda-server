package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;

//@Api(tags = "로그인, 회원가입 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserHandler user;

	@Autowired
	ResponseHandler response;

	@GetMapping("/new-user")
	public Response makeNewUser(String userId) throws Exception {

		user.isNotPresentUser(userId, true);

		user.insertNewUserInfo(userId);

		return response.getForm(user.getUserKeyData(userId));
	}

	@GetMapping("/new-friend")
	public Response makeNewFriend(String userId, String friendName) throws Exception {

		user.isNotPresentUser(userId, false);
		user.hasFullFriends(userId);

		user.insertNewUserFriend(userId, friendName);

		return response.getForm(user.getFriendStatus(userId).convertToData());
	}

	@GetMapping("/key")
	public Response getUserKey(String userId) throws Exception {
		user.isNotPresentUser(userId, false);
		
		return response.getForm(user.getUserKeyData(userId));
	}

	// /new-friend를 하기 전 호출 시
	@GetMapping("/info")
	public Response getUserStatus(String userId) throws Exception {
		user.isNotPresentUser(userId, false);
		
		return response.getForm(response.logInData(userId));

	}
}
