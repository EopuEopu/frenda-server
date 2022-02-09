package com.eopueopu.frenda.response.data.sub;

import com.eopueopu.frenda.db.food.Food;

public class FoodData {
	private String food_name;
	private String food_conversation;
	
	public FoodData(Food food) {
		this.food_name = food.getFood_name();
		this.food_conversation = food.getFood_conversation();
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_conversation() {
		return food_conversation;
	}

	public void setFood_conversation(String food_conversation) {
		this.food_conversation = food_conversation;
	}
	
	
}
