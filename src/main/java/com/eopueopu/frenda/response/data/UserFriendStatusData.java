package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.response.Data;

public class UserFriendStatusData implements Data {
	private String friend_name;
	private int favor_value;
	
	public UserFriendStatusData(UserFriendStatus friend) {
		this.friend_name = friend.getFriend_name();
		this.favor_value = friend.getFavor_value();
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	
	public int getFavor_value() {
		return favor_value;
	}

}
