package com.diary.frienda;

import java.util.List;

import com.diary.frienda.db.test.Test;
import com.diary.frienda.db.test.TestDAOService;

public class TestHandler {
	TestDAOService testDAO = null;
	
	public TestHandler(TestDAOService testDAO) {
		this.testDAO = testDAO;
	}
	
	public List<Test> getAllSentiments() throws Exception{
		return testDAO.getAllSentiments();
	}
	
	public List<Test> getResultsBySentiment(String value) throws Exception{
		return testDAO.getResultsBySentiment(value);
	}
	
	public void insertAll(Test test) throws Exception{
		testDAO.insertAll(test);
	}
	
	public int getAllCount() throws Exception{
		return testDAO.getAllCount();
	}
	public int getCountBySentiment(String value) throws Exception{
		return testDAO.getCountBySentiment(value);
	}
	
}
