package com.diary.frienda.db.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOService implements UserDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<User> getUserInfo() throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.getUserInfo();
	}

	@Override
	public String getUserKey(String user_id) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.getUserKey(user_id);
	}
	
	@Override
	public int checkUserId(String user_id) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.checkUserId(user_id);
	}

	@Override
	public int getUserValidation(String user_id, String user_key) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.getUserValidation(user_id, user_key);
	}
	
	@Override
	public int getNegativeDiaryCountByUserId(String user_id) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.getNegativeDiaryCountByUserId(user_id);
	}

	@Override
	public void addNegativeDiaryCount(String user_id) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.addNegativeDiaryCount(user_id);
		
	}

	@Override
	public void updateNegativeDiaryCountToZero(String user_id) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.updateNegativeDiaryCountToZero(user_id);
	}

	@Override
	public void insertNewUser(User user) throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		mapper.insertNewUser(user);
	}
	
}
