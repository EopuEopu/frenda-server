package com.diary.frienda.db.user;

import java.util.List;

public interface UserDAO {
	public List<User> getUserInfo() throws Exception;
	public String getUserKey(String user_id) throws Exception;
	public int getUserValidation(String user_id, String user_key) throws Exception;
}
