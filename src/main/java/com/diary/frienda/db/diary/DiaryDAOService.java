package com.diary.frienda.db.diary;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.frienda.response.data.MonthlyDiaries;

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
	public void insertDiary(Diary diary) throws Exception {
		DiaryMapper mapper = sqlSession.getMapper(DiaryMapper.class);
		mapper.insertDiary(diary);
		
	}

}
