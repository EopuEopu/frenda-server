package com.diary.frienda.db.userFriendStatus;

import com.diary.frienda.db.request.AddFavorValueRequest;

public interface UserFriendStatusDAO {
	public UserFriendStatus getUserFriendStatusByUserId(String user_id) throws Exception;
	public int getFavorValueByUserId(String user_id) throws Exception;
	public void addFavorValue(AddFavorValueRequest request) throws Exception;
}
