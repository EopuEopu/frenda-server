package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.response.Data;

public class DiaryUpdateData implements Data{
	private int diary_id;
	
	public DiaryUpdateData(int diary_id) {
		this.diary_id = diary_id;
	}

	public int getDiary_id() {
		return diary_id;
	}

}
