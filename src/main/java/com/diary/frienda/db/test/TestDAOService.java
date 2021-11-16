package com.diary.frienda.db.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAOService implements TestDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Test> getAllSentiments() throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.getAllSentiments();
	}
	
	@Override
	public List<Test> getResultsBySentiment(String value) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.getResultsBySentiment(value);
	}

	@Override
	public void insertAll(Test test) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);		
		mapper.insertAll(test);
	}

	@Override
	public int getAllCount() throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.getAllCount();
	}

	@Override
	public int getCountBySentiment(String value) throws Exception {
		TestMapper mapper = sqlSession.getMapper(TestMapper.class);
		return mapper.getCountBySentiment(value);
	}
}
