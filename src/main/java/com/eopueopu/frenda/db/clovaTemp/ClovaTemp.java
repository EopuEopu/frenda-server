package com.eopueopu.frenda.db.clovaTemp;

import com.eopueopu.frenda.clova.Document;

public class ClovaTemp {
	private String user_id;
	private String content;
	private String sentiment;
	private double positive_value;
	private double negative_value;
	private double neutral_value;
	private String analyzed_date;
	
	public ClovaTemp(String user_id, String content, String sentiment, double positive_value, double negative_value,
			double neutral_value, String analyzed_date) {
		this.user_id = user_id;
		this.content = content;
		this.sentiment = sentiment;
		this.positive_value = positive_value;
		this.negative_value = negative_value;
		this.neutral_value = neutral_value;
		this.analyzed_date = analyzed_date;
	}

	public ClovaTemp(Document document, String user_id, String content ) {
		this.sentiment = document.getSentiment();
		this.positive_value = document.getConfidence().getPositive();
		this.neutral_value = document.getConfidence().getNeutral();
		this.negative_value = document.getConfidence().getNegative();
		
		this.user_id = user_id;
		this.content = content;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public String getContent() {
		return content;
	}
	public String getSentiment() {
		return sentiment;
	}
	public double getPositive_value() {
		return positive_value;
	}
	public double getNegative_value() {
		return negative_value;
	}
	public double getNeutral_value() {
		return neutral_value;
	}
	public String getAnalyzied_date() {
		return analyzed_date;
	}
	
	public Document convertClovaTempToDoc() {
		return new Document(this);
	}
}
