package com.eopueopu.frenda.clova;

import com.eopueopu.frenda.db.clovaTemp.ClovaTemp;

public class Confidence {
	private double negative;
	private double positive;
	private double neutral;
	
	// default constructor for serializable
	public Confidence() { }
	
	public Confidence(ClovaTemp clovaTemp) {
		this.negative = clovaTemp.getNegative_value();
		this.positive = clovaTemp.getPositive_value();
		this.neutral = clovaTemp.getNeutral_value();
	}
	
	public double getNegative() {
		return negative;
	}
	public double getPositive() {
		return positive;
	}
	public double getNeutral() {
		return neutral;
	}
	
	void setPositive(double positive) {
		this.positive = positive;
	}
	void setNegative(double negative) {
		this.negative = negative;
	}
	void setNeutral(double neutral) {
		this.neutral = neutral;
	}
}
