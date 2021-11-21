package com.diary.frienda.db.food;

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

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getFood_sentiment() {
		return food_sentiment;
	}

	public void setFood_sentiment(String food_sentiment) {
		this.food_sentiment = food_sentiment;
	}

	public String getFood_conversation() {
		return food_conversation;
	}

	public void setFood_conversation(String food_conversation) {
		this.food_conversation = food_conversation;
	}
	
}
