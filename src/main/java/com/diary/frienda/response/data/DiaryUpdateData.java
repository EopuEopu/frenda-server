package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class DiaryUpdateData implements Data{
	private int diary_id;
	
	public DiaryUpdateData(int diary_id) {
		this.diary_id = diary_id;
	}

	public int getDiary_id() {
		return diary_id;
	}

	public void setDiary_id(int diary_id) {
		this.diary_id = diary_id;
	}
	
	
}
