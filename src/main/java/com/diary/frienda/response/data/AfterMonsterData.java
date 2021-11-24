package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class AfterMonsterData implements Data{
	private FavorData favor;

	public AfterMonsterData(FavorData favor) {
		this.favor = favor;
	}

	public FavorData getFavor() {
		return favor;
	}

	public void setFavor(FavorData favor) {
		this.favor = favor;
	}

}
