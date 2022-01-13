package com.diary.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frenda.handler.ResponseHandler;
import com.diary.frenda.handler.UserHandler;
import com.diary.frenda.response.Response;

@RestController
public class UserController {
	@Autowired
	UserHandler userH;
	
	@Autowired
	ResponseHandler responseH;
	
	@RequestMapping(value = "/new-user", method = RequestMethod.GET)
	public Response makeNewUser(@RequestParam("userId") String user_id) throws Exception {
		if(!userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();
		
		userH.insertNewUserInfo(user_id);
		
		return ResponseHandler.successResponse(userH.getUserKeyData(user_id));
	}
	
	@RequestMapping(value = "/new-friend", method = RequestMethod.GET)
	public Response makeNewFriend(@RequestParam("userId") String user_id, 
			@RequestParam("friendName") String friend_name) throws Exception {
		if(userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();
		
		if(userH.isPresentFriend(user_id))
			return ResponseHandler.failResponse(userH.getFriendStatus(user_id).convertToData());
		
		userH.insertNewUserFriend(user_id, friend_name);
		
		return ResponseHandler.successResponse(userH.getFriendStatus(user_id).convertToData());
	}
	
	@RequestMapping(value = "/user-key", method = RequestMethod.GET)
	public Response getUserKey(@RequestParam("userId") String user_id) throws Exception {
		if(userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();
		
		return ResponseHandler.successResponse(userH.getUserKeyData(user_id));
	}
	
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public Response getUserStatus(@RequestParam("userId") String user_id) throws Exception {		
		if(userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();
		
		return ResponseHandler.successResponse(responseH.logInData(user_id));
	}
}
