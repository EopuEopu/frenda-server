package com.eopueopu.frenda.db.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	public List<User> getUserInfo() throws Exception;
	public String getUserKey(String user_id) throws Exception;
	public int checkUserId(String user_id) throws Exception;
	public int getUserValidation(@Param("user_id") String user_id, @Param("user_key") String user_key) throws Exception;
	public int getNegativeDiaryCountByUserId(String user_id) throws Exception;
	public void addNegativeDiaryCount(String user_id) throws Exception;
	public void updateNegativeDiaryCountToZero(String user_id) throws Exception;
	public void insertNewUser(User user) throws Exception;
}
