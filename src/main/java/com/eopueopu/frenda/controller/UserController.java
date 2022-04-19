package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;

//@Api(tags = "로그인, 회원가입 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserHandler userH;

	@Autowired
	ResponseHandler responseH;

	@GetMapping("/new-user")
	public Response makeNewUser(@RequestParam("userId") String user_id) throws Exception {

		userH.isNotPresentUser(user_id, true);

		userH.insertNewUserInfo(user_id);

		return responseH.getForm(userH.getUserKeyData(user_id));
	}

	@GetMapping("/new-friend")
	public Response makeNewFriend(@RequestParam("userId") String user_id,
			@RequestParam("friendName") String friend_name) throws Exception {

		userH.isNotPresentUser(user_id, false);
		userH.hasFullFriends(user_id);

		userH.insertNewUserFriend(user_id, friend_name);

		return responseH.getForm(userH.getFriendStatus(user_id).convertToData());
	}

	@GetMapping("/key")
	public Response getUserKey(@RequestParam("userId") String user_id) throws Exception {
		userH.isNotPresentUser(user_id, false);
		
		return responseH.getForm(userH.getUserKeyData(user_id));
	}

	// /new-friend를 하기 전 호출 시
	@GetMapping("/info")
	public Response getUserStatus(@RequestParam("userId") String user_id) throws Exception {
		userH.isNotPresentUser(user_id, false);
		
		return responseH.getForm(responseH.logInData(user_id));

	}
}
