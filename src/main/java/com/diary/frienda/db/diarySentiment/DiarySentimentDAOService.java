package com.diary.frienda.db.diarySentiment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiarySentimentDAOService implements DiarySentimentDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public DiarySentiment getDiarySentimentByDiaryId(int diary_id) throws Exception {
		DiarySentimentMapper mapper = sqlSession.getMapper(DiarySentimentMapper.class);
		return mapper.getDiarySentimentByDiaryId(diary_id);
	}

	@Override
	public void insertDiarySentiment(DiarySentiment diarySentiment) throws Exception {
		DiarySentimentMapper mapper = sqlSession.getMapper(DiarySentimentMapper.class);
		mapper.insertDiarySentiment(diarySentiment);
	}
}
