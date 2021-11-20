package com.diary.frienda.response;

public class UserInfoData {
	private String friend_name;
	private int favor_status;
	private int diary_id;
	private boolean portal_open;
	
	public UserInfoData(String friend_name, int favor_status, int diary_id, boolean portal_open) {
		this.friend_name = friend_name;
		this.favor_status = favor_status;
		this.diary_id = diary_id;
		this.portal_open = portal_open;
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public int getFavor_status() {
		return favor_status;
	}
	public void setFavor_status(int favor_status) {
		this.favor_status = favor_status;
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
