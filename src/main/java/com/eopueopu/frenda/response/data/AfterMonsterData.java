package com.eopueopu.frenda.response.data;

import java.util.List;

import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.NegativeSentimentCount;

public class AfterMonsterData implements Data{
	private List<NegativeSentimentCount> sentiments; 

	public AfterMonsterData(List<NegativeSentimentCount> sentiments) {
		this.sentiments = sentiments;
	}

	public List<NegativeSentimentCount> getSentiments() {
		return sentiments;
	}

	public void setSentiments(List<NegativeSentimentCount> sentiments) {
		this.sentiments = sentiments;
	}
	
	

}
