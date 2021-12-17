package com.diary.frienda.handler;

import com.diary.frienda.clova.Confidence;


public class DiaryHandler {
	
	
	public static double doRound(double value) {
		return Math.round(value * 100) / 100.0;
	}

	public static Confidence roundValues(Confidence conf) {
		conf.setNegative(doRound(conf.getNegative()));
		conf.setPositive(doRound(conf.getPositive()));
		conf.setNeutral(doRound(conf.getNeutral()));
		return conf;
	}
	
}
