package com.diary.frienda.db.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public List<User> getUserInfo() throws Exception;
	public String getUserKey(String user_id) throws Exception;
	public int getUserValidation(@Param("user_id") String user_id, @Param("user_key") String user_key) throws Exception;
}
