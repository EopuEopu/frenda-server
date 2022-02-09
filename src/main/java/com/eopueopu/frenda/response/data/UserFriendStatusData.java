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
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public int getFavor_value() {
		return favor_value;
	}
	public void setFavor_value(int favor_value) {
		this.favor_value = favor_value;
	}

}
