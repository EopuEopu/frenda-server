package com.diary.frienda.handler;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.diary.frienda.clova.Confidence;

enum Sentiment {
	Positive("good"),
	Negative("bad");
	
	private final String emotion;

	Sentiment(String emotion) {
		this.emotion = emotion;
	}

	public String getEmotion() {
		return emotion;
	}
	
	private static final Map<String, Sentiment> emotions = Collections.unmodifiableMap(Stream.of(values())
			.collect(Collectors.toMap(Sentiment::getEmotion, Function.identity())));
	
	public static Sentiment find(String emotion) {
        if (emotions.containsKey(emotion))
            return emotions.get(emotion);
        
        // TODO : Handling the exception
        throw new IllegalArgumentException("not valid sentiment");
    }
	
}

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
	
	public static boolean isNegativeSentiment(String emotion) {
		if(Sentiment.find(emotion) == Sentiment.Negative)
			return true;
		
		return false;
	}
}
