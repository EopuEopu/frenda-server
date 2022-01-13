package com.diary.frenda.db.userFriendStatus;

import com.diary.frenda.response.data.UserFriendStatusData;

public class UserFriendStatus {
	private String user_id;
	private String friend_name;
	private int favor_value;
	private int head_accessory_id;
	private int tail_accessory_id;
	private int foot_accessory_id;
	private String favor_updated_date;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public int getHead_accessory_id() {
		return head_accessory_id;
	}
	public void setHead_accessory_id(int head_accessory_id) {
		this.head_accessory_id = head_accessory_id;
	}
	public int getTail_accessory_id() {
		return tail_accessory_id;
	}
	public void setTail_accessory_id(int tail_accessory_id) {
		this.tail_accessory_id = tail_accessory_id;
	}
	public int getFoot_accessory_id() {
		return foot_accessory_id;
	}
	public void setFoot_accessory_id(int foot_accessory_id) {
		this.foot_accessory_id = foot_accessory_id;
	}
	public String getFavor_updated_date() {
		return favor_updated_date;
	}
	public void setFavor_updated_date(String favor_updated_date) {
		this.favor_updated_date = favor_updated_date;
	}
	
	public UserFriendStatusData convertToData() {
		return new UserFriendStatusData(this);
	}
}
