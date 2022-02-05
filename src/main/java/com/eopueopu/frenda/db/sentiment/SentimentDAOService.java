package com.eopueopu.frenda.db.sentiment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eopueopu.frenda.response.data.sub.SentimentString;

@Repository
public class SentimentDAOService implements SentimentDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getApprSentimentByDetailedSentiment(String detailed_sentiment) throws Exception {
		SentimentMapper mapper = sqlSession.getMapper(SentimentMapper.class);
		return mapper.getApprSentimentByDetailedSentiment(detailed_sentiment);
	}

	@Override
	public List<SentimentString> getDetailedSentimentByApprSentiment(String appr_sentiment) throws Exception {
		SentimentMapper mapper = sqlSession.getMapper(SentimentMapper.class);
		return mapper.getDetailedSentimentByApprSentiment(appr_sentiment);
	}
}
