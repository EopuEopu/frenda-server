package com.diary.frienda.response.data;

import java.util.List;

import com.diary.frienda.response.Data;

public class AfterMonsterData implements Data{
	private FavorData favor;
	private List<NegativeSentimentCount> sentiments; 

	public AfterMonsterData(FavorData favor, List<NegativeSentimentCount> sentiments) {
		this.favor = favor;
		this.sentiments = sentiments;
	}

	public FavorData getFavor() {
		return favor;
	}

	public void setFavor(FavorData favor) {
		this.favor = favor;
	}

	public List<NegativeSentimentCount> getSentiments() {
		return sentiments;
	}

	public void setSentiments(List<NegativeSentimentCount> sentiments) {
		this.sentiments = sentiments;
	}
	
	

}
