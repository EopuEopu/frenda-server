package com.eopueopu.frenda.db.userFriendStatus;

import com.eopueopu.frenda.response.data.UserFriendStatusData;

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
	
	public String getFriend_name() {
		return friend_name;
	}
	
	public int getFavor_value() {
		return favor_value;
	}
	
	public int getHead_accessory_id() {
		return head_accessory_id;
	}
	
	public int getTail_accessory_id() {
		return tail_accessory_id;
	}
	
	public int getFoot_accessory_id() {
		return foot_accessory_id;
	}
	
	public String getFavor_updated_date() {
		return favor_updated_date;
	}
	
	public UserFriendStatusData convertToData() {
		return new UserFriendStatusData(this);
	}
}
