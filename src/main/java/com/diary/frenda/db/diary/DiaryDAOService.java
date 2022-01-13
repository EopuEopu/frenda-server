package com.diary.frenda.db.diary;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.frenda.response.data.sub.MonthlyDiaries;

@Repository
public class DiaryDAOService implements DiaryDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Diary> getAllDiariesByUserId(String user_id) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		return mapper.getAllDiariesByUserId(user_id);
	}

	@Override
	public Diary getDiaryByUserIdAndDiaryId(String user_id, String diary_id) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		return mapper.getDiaryByUserIdAndDiaryId(user_id, diary_id);
	}
	
	@Override
	public int getDiaryIdByUserId(String user_id) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		return mapper.getDiaryIdByUserId(user_id);
	}
	
	@Override
	public List<MonthlyDiaries> getMonthlyDiariesByUserIdAndDate(String user_id, String year_month) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		return mapper.getMonthlyDiariesByUserIdAndDate(user_id, year_month);
	}

	@Override
	public void insertDiary(String user_id, String content) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		mapper.insertDiary(user_id, content);
		
	}

	@Override
	public void updateDiaryContent(Diary diary) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		mapper.updateDiaryContent(diary);
	}

	@Override
	public String getLatestDiaryDateByUserId(String user_id) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		return mapper.getLatestDiaryDateByUserId(user_id);
	}

}
