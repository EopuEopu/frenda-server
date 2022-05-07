package com.eopueopu.frenda.response.data;

import com.eopueopu.frenda.db.diary.Diary;
import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.DiaryAllSentiments;

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
	
	public String getCommitted_date() {
		return committed_date;
	}
	
	public DiaryAllSentiments getDiary_sentiments() {
		return diary_sentiments;
	}
}
