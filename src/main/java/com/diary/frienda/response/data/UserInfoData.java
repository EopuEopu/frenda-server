package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class UserInfoData implements Data{
	private String friend_name;
	private int favor_value;
	private int diary_id;
	private boolean portal_open;
	
	public UserInfoData(String friend_name, int favor_value, int diary_id, boolean portal_open) {
		this.friend_name = friend_name;
		this.favor_value = favor_value;
		this.diary_id = diary_id;
		this.portal_open = portal_open;
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public int getFavor_value() {
		return favor_value;
	}
	public void setFavor_value(int favor_value) {
		this.favor_value = favor_value;
	}
	public boolean isPortal_open() {
		return portal_open;
	}
	public void setPortal_open(boolean portal_open) {
		this.portal_open = portal_open;
	}
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	
}
