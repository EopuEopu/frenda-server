package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class DiaryInsertionData implements Data{
	private int diary_id;
	private String latest_committed_date;
	private boolean portal_open;
	private FavorData favor;
	private FoodData food;
	
	public DiaryInsertionData() {
		
	}
	
	public DiaryInsertionData(int diary_id, String latest_committed_date, boolean portal_open, FavorData favor, FoodData food) {
		this.diary_id = diary_id;
		this.latest_committed_date = latest_committed_date;
		this.portal_open = portal_open;
		this.favor = favor;
		this.food = food;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}

	public boolean isPortal_open() {
		return portal_open;
	}

	public void setPortal_open(boolean portal_open) {
		this.portal_open = portal_open;
	}

	public FoodData getFood() {
		return food;
	}

	public void setFood(FoodData food) {
		this.food = food;
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
