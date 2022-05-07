package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.db.diary.Diary;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.FavorData;
import com.eopueopu.frenda.response.data.sub.FoodData;

//TODO : extend from new Class Type with UserInfoData
public class DiaryInsertionData implements Data{
	private int diary_id;
	private String latest_committed_date;
	private boolean portal_open;
	private FavorData favor;
	private FoodData food;
	
	public DiaryInsertionData() {
		
	}
	
	public DiaryInsertionData(Diary diary, UserFriendStatus friend, boolean portal_open, FoodData food) {
		this.diary_id = diary.getDiary_id();
		this.latest_committed_date = diary.getCommitted_date();
		this.portal_open = portal_open;
		this.favor =  new FavorData(friend.getFavor_value(), 1);
		this.food = food;
	}
	
	public int getDiary_id() {
		return diary_id;
	}

	public boolean isPortal_open() {
		return portal_open;
	}

	public FoodData getFood() {
		return food;
	}

	public FavorData getFavor() {
		return favor;
	}

	public String getLatest_committed_date() {
		return latest_committed_date;
	}
}
