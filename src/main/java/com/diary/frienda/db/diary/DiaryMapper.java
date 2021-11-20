package com.diary.frienda.db.diary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DiaryMapper {
	public List<Diary> getAllDiariesByUserId(String user_id) throws Exception;
	public Diary getDiaryByUserIdAndDiaryId(@Param("user_id") String user_id, @Param("diary_id") String diary_id) throws Exception;
	public int getDiaryIdByUserId(String user_id) throws Exception;
	public void insertDiary(Diary diary) throws Exception;
}
