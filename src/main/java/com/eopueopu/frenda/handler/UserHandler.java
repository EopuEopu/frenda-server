package com.eopueopu.frenda.handler;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLogDAOService;
import com.eopueopu.frenda.db.request.AddFavorValueRequest;
import com.eopueopu.frenda.db.user.User;
import com.eopueopu.frenda.db.user.UserDAOService;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatusDAOService;
import com.eopueopu.frenda.response.data.UserKeyData;

@Service
public class UserHandler {
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	HuntedMonsterLogDAOService huntedMonsterLogDAO;
	
	@Autowired
	private EncryptHandler encryptH;
	
	/**
	 * check existence of user information
	 * @param user_id
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isNotPresentUser(String user_id) throws Exception {
		return (userDAO.checkUserId(user_id) < 1);
	}
	
	public boolean isInvalidUser(String user_id, String encrypted_key) throws Exception {
		String user_key = encryptH.decryptContent(user_id, encrypted_key);
		return (userDAO.getUserValidation(user_id, user_key) < 1);
	}
	
	public boolean isPresentFriend(String user_id) throws Exception {
		return (userFriendStatusDAO.checkUserFriendByUserId(user_id) > 0);
	}
	
	
	/**
	 * get various user information
	 * @param user_id
	 */
	public boolean getPortalOpen(String user_id) throws Exception {		
		if(userDAO.getNegativeDiaryCountByUserId(user_id) < 3)
			return false;
		
		return true;
	}
	
	public boolean isFavorIncreased(String user_id) throws Exception {
		return huntedMonsterLogDAO.getFavorIncreasedValue(user_id);
	}
	
	public UserFriendStatus getFriendStatus(String user_id) throws Exception {
		return userFriendStatusDAO.getUserFriendStatusByUserId(user_id);
	}
	
	public UserKeyData getUserKeyData(String user_id) throws Exception {
		return new UserKeyData(getEncryptedUserKey(user_id));
	}
	
	/**
	 * insert/update user data to Database
	 */
	public void insertNewUserInfo(String user_id) throws Exception {
		userDAO.insertNewUser(new User(user_id, makeUserKey()));
	}
	
	public void insertNewUserFriend(String user_id, String friend_name) throws Exception {
		userFriendStatusDAO.insertNewFriend(user_id, friend_name);
	}
	
	public void updateFriendFavor(String user_id, int value) throws Exception {
		userFriendStatusDAO.addFavorValue(new AddFavorValueRequest(user_id, value));
	}
	
	
	private String makeUserKey() {
		return RandomStringUtils.randomAlphanumeric(16);
	}
	
	private String getEncryptedUserKey(String user_id) throws Exception {
		return encryptH.encryptContent(user_id, userDAO.getUserKey(user_id));
	}
	
}
