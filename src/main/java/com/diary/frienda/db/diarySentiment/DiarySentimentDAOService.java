package com.diary.frienda.db.diarySentiment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.frienda.response.data.NegativeSentimentCount;

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
	public List<NegativeSentimentCount> getNegativeSentimentCount(String user_id) throws Exception {
		DiarySentimentMapper mapper = sqlSession.getMapper(DiarySentimentMapper.class);
		return mapper.getNegativeSentimentCount(user_id);
	}

	@Override
	public void insertDiarySentiment(DiarySentiment diarySentiment) throws Exception {
		DiarySentimentMapper mapper = sqlSession.getMapper(DiarySentimentMapper.class);
		mapper.insertDiarySentiment(diarySentiment);
	}

	@Override
	public void insertDiaryUserSentiment(DiarySentiment diarySentiment) throws Exception {
		DiarySentimentMapper mapper = sqlSession.getMapper(DiarySentimentMapper.class);
		mapper.insertDiaryUserSentiment(diarySentiment);
	}
	
	
}
