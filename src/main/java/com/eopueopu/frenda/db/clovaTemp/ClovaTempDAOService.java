package com.eopueopu.frenda.db.clovaTemp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClovaTempDAOService implements ClovaTempDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ClovaTemp getTodayTempDataByUserId(String user_id) throws Exception {
		ClovaTempMapper mapper = sqlSession.getMapper(ClovaTempMapper.class);
		return mapper.getTodayTempDataByUserId(user_id);
	}

	@Override
	public void insertTodayTempData(ClovaTemp clovaTemp) throws Exception {
		ClovaTempMapper mapper = sqlSession.getMapper(ClovaTempMapper.class);
		mapper.insertTodayTempData(clovaTemp);
		
	}

	@Override
	public void deleteTodayTempData(String user_id) throws Exception {
		ClovaTempMapper mapper = sqlSession.getMapper(ClovaTempMapper.class);
		mapper.deleteTodayTempData(user_id);
	}

	

}
