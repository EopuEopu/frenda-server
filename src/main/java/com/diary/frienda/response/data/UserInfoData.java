package com.diary.frienda.response.data;

import com.diary.frienda.db.diary.Diary;
import com.diary.frienda.db.userFriendStatus.UserFriendStatus;
import com.diary.frienda.response.Data;
import com.diary.frienda.response.data.sub.FavorData;

// TODO : extend from new Class Type with DiaryInsertionData
public class UserInfoData implements Data{
	private String friend_name;
	private FavorData favor;
	private int diary_id;
	private String latest_committed_date;
	private boolean portal_open;
	
	public UserInfoData(Diary diary, UserFriendStatus friend, boolean portal_open) {
		this.diary_id = diary.getDiary_id();
		this.latest_committed_date = diary.getCommitted_date();
		this.portal_open = portal_open;
		favor = new FavorData(friend.getFavor_value(), 0);
		this.friend_name = friend.getFriend_name();
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
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

	public FavorData getFavor() {
		return favor;
	}

	public void setFavor(FavorData favor) {
		this.favor = favor;
	}

	public String getLatest_committed_date() {
		return latest_committed_date;
	}

	public void setLatest_committed_date(String latest_committed_date) {
		this.latest_committed_date = latest_committed_date;
	}
	
	
}
