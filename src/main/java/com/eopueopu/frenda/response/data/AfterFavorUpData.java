package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.FavorData;

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
