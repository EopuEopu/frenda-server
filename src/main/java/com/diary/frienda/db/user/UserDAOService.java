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
	public List<User> getIds() throws Exception {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		return mapper.getIds();
	}

}
