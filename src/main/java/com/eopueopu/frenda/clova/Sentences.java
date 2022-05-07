package com.eopueopu.frenda.clova;

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
	public int getOffset() {
		return offset;
	}
	public int getLength() {
		return length;
	}
	public String getSentiment() {
		return sentiment;
	}
	public Confidence getConfidence() {
		return confidence;
	}
	public ArrayList<Highlights> getHighlights() {
		return highlights;
	}	
}
