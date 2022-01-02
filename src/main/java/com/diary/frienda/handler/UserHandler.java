package com.diary.frienda.handler;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.frienda.db.user.UserDAOService;

@Service
public class UserHandler {
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	private EncryptHandler encryptHandler;
	
	public static boolean getPortalOpen(int negative_diary_count) {
		if(negative_diary_count < 3)
			return false;
		return true;
	}
	
	public static String makeUserKey() {
		return RandomStringUtils.randomAlphanumeric(16);
	}
	
	public boolean isPresentUser(String user_id) throws Exception {
		return (userDAO.checkUserId(user_id) < 1);
	}
	
	public boolean isValidUser(String user_id, String user_key) throws Exception {
		return (userDAO.getUserValidation(user_id, user_key) < 1);
	}
	
}
