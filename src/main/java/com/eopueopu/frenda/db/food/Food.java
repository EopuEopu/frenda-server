package com.eopueopu.frenda.db.food;

import com.eopueopu.frenda.response.data.sub.FoodData;

public class Food {
	private int food_id;
	private String food_name;
	private String food_sentiment;
	private String food_conversation;
	
	public Food(String food_name, String food_conversation) {
		this.food_name = food_name;
		this.food_conversation = food_conversation;
	}

	public int getFood_id() {
		return food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public String getFood_sentiment() {
		return food_sentiment;
	}

	public String getFood_conversation() {
		return food_conversation;
	}
	
	public FoodData convertToData() {
		return new FoodData(this);
	}
	
}
