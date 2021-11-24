package com.diary.frienda.db.diary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.diary.frienda.response.data.MonthlyDiaries;

public interface DiaryDAO {
	public List<Diary> getAllDiariesByUserId(String user_id) throws Exception;
	public Diary getDiaryByUserIdAndDiaryId(@Param("user_id") String user_id, @Param("diary_id") String diary_id) throws Exception;
	public int getDiaryIdByUserId(String user_id) throws Exception;
	public String getLatestDiaryDateByUserId(String user_id) throws Exception;
	public List<MonthlyDiaries> getMonthlyDiariesByUserIdAndDate(@Param("user_id") String user_id, @Param("year_month") String year_month) throws Exception;
	public void insertDiary(Diary diary) throws Exception;
	public void updateDiaryContent(Diary diary) throws Exception;
}
