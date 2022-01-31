package com.diary.frenda.response.data;

import com.diary.frenda.db.diary.Diary;
import com.diary.frenda.db.userFriendStatus.UserFriendStatus;
import com.diary.frenda.response.Data;
import com.diary.frenda.response.data.sub.FavorData;

// TODO : extend from new Class Type with DiaryInsertionData
public class UserInfoData implements Data{
	private String friend_name;
	private FavorData favor;
	private int diary_id;
	private String latest_committed_date;
	private boolean portal_open;
	private boolean favor_increased;
	
	public UserInfoData(Diary diary, UserFriendStatus friend, boolean portal_open, boolean favor_increased) {
		this.diary_id = diary.getDiary_id();
		this.latest_committed_date = diary.getCommitted_date();
		this.portal_open = portal_open;
		favor = new FavorData(friend.getFavor_value(), 0);
		this.friend_name = friend.getFriend_name();
		this.favor_increased = favor_increased;
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

	public boolean isFavor_increased() {
		return favor_increased;
	}

	public void setFavor_increased(boolean favor_increased) {
		this.favor_increased = favor_increased;
	}
	
}
