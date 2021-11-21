package com.diary.frienda.db.food;

public interface FoodDAO {
	public Food getFoodBySentiment(String food_sentiment) throws Exception;
}
