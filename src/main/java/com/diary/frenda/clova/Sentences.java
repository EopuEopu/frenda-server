package com.diary.frenda.clova;

import java.util.ArrayList;

public class Sentences {
	private String content;
	private int offset;
	private int length;
	private String sentiment;
	private Confidence confidence;
	private ArrayList<Highlights> highlights;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
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
	public ArrayList<Highlights> getHighlights() {
		return highlights;
	}
	public void setHighlights(ArrayList<Highlights> highlights) {
		this.highlights = highlights;
	}	
}
