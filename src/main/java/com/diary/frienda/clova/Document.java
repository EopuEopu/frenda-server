package com.diary.frienda.clova;

import java.util.ArrayList;

public class Document {
	private String sentiment;
	private Confidence confidence;
	private ArrayList<Sentences> sentences;
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public Confidence getConfidence() {
		return confidence;
	}
	public void setConfidence(Confidence confidence) {
		this.confidence = confidence;
	}
	public ArrayList<Sentences> getSentences() {
		return sentences;
	}
	public void setSentences(ArrayList<Sentences> sentences) {
		this.sentences = sentences;
	}
	
	
}
