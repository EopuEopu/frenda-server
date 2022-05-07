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

	public String getFood_conversation() {
		return food_conversation;
	}
	
}
