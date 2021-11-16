package com.diary.frienda.db.test;

public class Test {
	private int id;
	private String content;
	private String sentiment;
	private double positive_value;
	private double negative_value;
	private double neutral_value;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public double getPositive_value() {
		return positive_value;
	}

	public void setPositive_value(double positive_value) {
		this.positive_value = positive_value;
	}

	public double getNegative_value() {
		return negative_value;
	}

	public void setNegative_value(double negative_value) {
		this.negative_value = negative_value;
	}

	public double getNeutral_value() {
		return neutral_value;
	}

	public void setNeutral_value(double neutral_value) {
		this.neutral_value = neutral_value;
	}
	
}
