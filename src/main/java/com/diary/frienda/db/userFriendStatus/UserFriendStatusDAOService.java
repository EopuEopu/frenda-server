package com.diary.frienda.db.userFriendStatus;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserFriendStatusDAOService implements UserFriendStatusDAO{
	@Autowired
	private SqlSession sqlSession;
	


}
