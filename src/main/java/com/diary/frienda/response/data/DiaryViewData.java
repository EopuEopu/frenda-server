package com.diary.frienda.response.data;

import com.diary.frienda.response.Data;

public class DiaryViewData implements Data {
	private String content;
	private String committed_date;
	private DiaryAllSentiments diary_sentiments;
	
	
	public DiaryViewData(String content, String committed_date, DiaryAllSentiments diary_sentiments) {
		this.content = content;
		this.committed_date = committed_date;
		this.diary_sentiments = diary_sentiments;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommitted_date() {
		return committed_date;
	}
	public void setCommitted_date(String committed_date) {
		this.committed_date = committed_date;
	}
	public DiaryAllSentiments getDiary_sentiments() {
		return diary_sentiments;
	}
	public void setDiary_sentiments(DiaryAllSentiments diary_sentiments) {
		this.diary_sentiments = diary_sentiments;
	}
}
