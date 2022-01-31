package com.diary.frenda.response.data;

import com.diary.frenda.response.Data;
import com.diary.frenda.response.data.sub.FavorData;

public class AfterFavorUpData implements Data {
	private FavorData favor;
	
	public AfterFavorUpData(FavorData favor) {
		this.favor = favor;
	}
	
	public FavorData getFavor() {
		return favor;
	}

	public void setFavor(FavorData favor) {
		this.favor = favor;
	}
}
