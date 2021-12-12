package com.diary.frienda.handler;

import org.apache.commons.lang3.RandomStringUtils;

public class UserHandler {
	public static boolean getPortalOpen(int negative_diary_count) {
		if(negative_diary_count < 3)
			return false;
		return true;
	}
	
	public static String makeUserKey() {
		return RandomStringUtils.randomAlphanumeric(16);
	}
}
