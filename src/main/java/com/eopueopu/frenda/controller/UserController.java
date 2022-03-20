package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.exception.user.FriendsCountOutOfBoundsException;
import com.eopueopu.frenda.handler.ResponseHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.response.Response;

@RestController
public class UserController {
	@Autowired
	UserHandler userH;
	
	@Autowired
	ResponseHandler responseH;
	
	@RequestMapping(value = "/new-user", method = RequestMethod.GET)
	public Response makeNewUser(@RequestParam("userId") String user_id) throws Exception {
		try {
			userH.isNotPresentUser(user_id);
			return responseH.failResponse("AlreadyExistedUser");
		} catch(Exception e) {
			userH.insertNewUserInfo(user_id);
			return responseH.successResponse(userH.getUserKeyData(user_id));
		}
	}
	
	@RequestMapping(value = "/new-friend", method = RequestMethod.GET)
	public Response makeNewFriend(@RequestParam("userId") String user_id, 
			@RequestParam("friendName") String friend_name) throws Exception {
		try {
			userH.isNotPresentUser(user_id);
			userH.hasFullFriends(user_id);
			
			userH.insertNewUserFriend(user_id, friend_name);
			
			return responseH.successResponse(userH.getFriendStatus(user_id).convertToData());
			
		} catch(FriendsCountOutOfBoundsException e) {
			return responseH.failResponse(e.getMessage(), userH.getFriendStatus(user_id).convertToData());
		} catch(Exception e) {
			return responseH.failResponse(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/user-key", method = RequestMethod.GET)
	public Response getUserKey(@RequestParam("userId") String user_id) {
		try {
			userH.isNotPresentUser(user_id);
			return responseH.successResponse(userH.getUserKeyData(user_id));
		} catch(Exception e) {
			return responseH.failResponse(e.getMessage());
		}
		
	}
	
	// /new-friend를 하기 전 호출 시 
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Response getUserStatus(@RequestParam("userId") String user_id) {		
		try {
			userH.isNotPresentUser(user_id);
			return responseH.successResponse(responseH.logInData(user_id));
		} catch(Exception e) {
			return responseH.failResponse(e.getMessage());
		}
		
	}
}
