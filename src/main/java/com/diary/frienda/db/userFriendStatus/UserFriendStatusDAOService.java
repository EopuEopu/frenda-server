package com.diary.frienda.db.userFriendStatus;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diary.frienda.db.request.AddFavorValueRequest;

@Repository
public class UserFriendStatusDAOService implements UserFriendStatusDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserFriendStatus getUserFriendStatusByUserId(String user_id) throws Exception {
		UserFriendStatusMapper mapper = sqlSession.getMapper(UserFriendStatusMapper.class);
		return mapper.getUserFriendStatusByUserId(user_id);
	}
	
	@Override
	public int getFavorValueByUserId(String user_id) throws Exception {
		UserFriendStatusMapper mapper = sqlSession.getMapper(UserFriendStatusMapper.class);
		return mapper.getFavorValueByUserId(user_id);
	}

	@Override
	public int checkUserFriendByUserId(String user_id) throws Exception {
		UserFriendStatusMapper mapper = sqlSession.getMapper(UserFriendStatusMapper.class);
		return mapper.checkUserFriendByUserId(user_id);
	}
	
	@Override
	public void addFavorValue(AddFavorValueRequest request) throws Exception {
		UserFriendStatusMapper mapper = sqlSession.getMapper(UserFriendStatusMapper.class);
		mapper.addFavorValue(request);
	}
	
	@Override
	public void insertNewFriend(String user_id, String friend_name) throws Exception {
		UserFriendStatusMapper mapper = sqlSession.getMapper(UserFriendStatusMapper.class);
		mapper.insertNewFriend(user_id, friend_name);
	}

}
