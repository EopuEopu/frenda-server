package com.diary.frienda.db.test;

import java.util.List;

public interface TestDAO {
	public List<Test> getAllSentiments() throws Exception;
	public List<Test> getResultsBySentiment(String value) throws Exception;
	public void insertAll(Test test) throws Exception;
	public int getAllCount() throws Exception;
	public int getCountBySentiment(String value) throws Exception;
}
