package com.diary.frienda.handler;


public class UserHandler {
	public static boolean getPortalOpen(int negative_diary_count) {
		if(negative_diary_count < 3)
			return false;
		return true;
	}
}
