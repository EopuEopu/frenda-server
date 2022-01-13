package com.diary.frenda.response.data.sub;

public class FavorData {
	private int favor_value;
	private int incremented_value;
	
	public FavorData(int favor_value, int incremented_value) {
		this.favor_value = favor_value;
		this.incremented_value = incremented_value;
	}
	
	public int getFavor_value() {
		return favor_value;
	}

	public void setFavor_value(int favor_value) {
		this.favor_value = favor_value;
	}

	public int getIncremented_value() {
		return incremented_value;
	}

	public void setIncremented_value(int incremented_value) {
		this.incremented_value = incremented_value;
	}
	
	
}
