package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class AfterMonsterData implements Data{
	private int favor_value;

	public AfterMonsterData(int favor_value) {
		this.favor_value = favor_value;
	}
	
	public int getFavor_value() {
		return favor_value;
	}

	public void setFavor_value(int favor_value) {
		this.favor_value = favor_value;
	}
	
	
}
