package com.diary.frenda.response.data;

import com.diary.frenda.db.diary.Diary;
import com.diary.frenda.response.Data;
import com.diary.frenda.response.data.sub.DiaryAllSentiments;

public class DiaryViewData implements Data {
	private String content;
	private String committed_date;
	private DiaryAllSentiments diary_sentiments;
	
	
	public DiaryViewData(Diary diary, DiaryAllSentiments diary_sentiments) {
		this.content = diary.getContent();
		this.committed_date = diary.getCommitted_date();
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
