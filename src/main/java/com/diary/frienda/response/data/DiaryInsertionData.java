package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class DiaryInsertionData implements Data{
	private int diary_id;
	private int favor_value;
	private boolean portal_open;
	
	public DiaryInsertionData() {
		
	}
	
	public DiaryInsertionData(int diary_id, int favor_value, boolean portal_open) {
		this.diary_id = diary_id;
		this.favor_value = favor_value;
		this.portal_open = portal_open;
	}
	
	public int getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
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
	
	
	
}
