package com.diary.frienda.db.diary;

import java.util.List;

public interface DiaryDAO {
	public List<Diary> getAllDiariesByUserId(String user_id) throws Exception;
	public Diary getDiaryByUserIdAndDiaryId(String user_id, String diary_id) throws Exception;
	public int getDiaryIdByUserId(String user_id) throws Exception;
	public void insertDiary(Diary diary) throws Exception;
}
