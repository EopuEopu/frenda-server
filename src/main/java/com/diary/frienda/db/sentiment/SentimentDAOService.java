package com.diary.frienda.db.sentiment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SentimentDAOService implements SentimentDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getApprSentimentByDetailedSentiment(String detailed_sentiment) throws Exception {
		SentimentMapper mapper = sqlSession.getMapper(SentimentMapper.class);
		return mapper.getApprSentimentByDetailedSentiment(detailed_sentiment);
	}
}
