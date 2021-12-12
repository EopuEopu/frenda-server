package com.diary.frienda.db.userFriendStatus;

import org.apache.ibatis.annotations.Param;

import com.diary.frienda.db.request.AddFavorValueRequest;

public interface UserFriendStatusDAO {
	public UserFriendStatus getUserFriendStatusByUserId(String user_id) throws Exception;
	public int getFavorValueByUserId(String user_id) throws Exception;
	public int checkUserFriendByUserId(String user_id) throws Exception;
	public void addFavorValue(AddFavorValueRequest request) throws Exception;
	public void insertNewFriend(@Param("user_id") String user_id, @Param("friend_name") String friend_name) throws Exception;
}
