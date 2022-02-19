package com.eopueopu.frenda.clova;

import java.util.ArrayList;

import com.eopueopu.frenda.db.clovaTemp.ClovaTemp;

public class Document {
	private String sentiment;
	private Confidence confidence;
	private ArrayList<Sentences> sentences;
	
	// default constructor for serializable
	public Document() {	}
	
	public Document(ClovaTemp temp) {
		this.sentiment = temp.getSentiment();
		this.confidence = new Confidence(temp);
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
	public ArrayList<Sentences> getSentences() {
		return sentences;
	}
	public void setSentences(ArrayList<Sentences> sentences) {
		this.sentences = sentences;
	}
	
	private double doRound(double value) {
		return Math.round(value * 100) / 100.0;
	}

	public Document roundValues() {
		confidence.setNegative(doRound(confidence.getNegative()));
		confidence.setPositive(doRound(confidence.getPositive()));
		confidence.setNeutral(doRound(confidence.getNeutral()));
		return this;
	}
	
	public ClovaTemp convertDocToClovaTemp(String user_id, String content) {
		return new ClovaTemp(this, user_id, content);
	}
}
