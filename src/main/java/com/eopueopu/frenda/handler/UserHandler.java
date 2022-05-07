package com.eopueopu.frenda.handler;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLogDAO;
import com.eopueopu.frenda.db.request.AddFavorValueRequest;
import com.eopueopu.frenda.db.user.User;
import com.eopueopu.frenda.db.user.UserDAO;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatus;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatusDAO;
import com.eopueopu.frenda.exception.user.AlreadyExistUserException;
import com.eopueopu.frenda.exception.user.FriendsCountOutOfBoundsException;
import com.eopueopu.frenda.exception.user.InvalidUserException;
import com.eopueopu.frenda.exception.user.NotPresentUserException;
import com.eopueopu.frenda.handler.util.EncryptHandler;
import com.eopueopu.frenda.response.data.AfterFavorUpData;
import com.eopueopu.frenda.response.data.UserKeyData;
import com.eopueopu.frenda.response.data.sub.FavorData;

@Service
public class UserHandler {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserFriendStatusDAO userFriendStatusDAO;
	
	@Autowired
	private HuntedMonsterLogDAO huntedMonsterLogDAO;
	
	@Autowired
	private EncryptHandler encryptH;
	
	static final private int MAX_FRIEND_COUNT = 1;
	
	/**
	 * check existence of user information
	 * @param user_id
	 * @throws Exception
	 */
	public boolean isNotPresentUser(String user_id) throws Exception {
		int userIdCount = userDAO.checkUserId(user_id);
		
		if(userIdCount < 1)
			throw new NotPresentUserException();
		
		return true;
	}
	
	public void isAlreadyExistUser(String user_id) throws Exception {
		if(isNotPresentUser(user_id))
			throw new AlreadyExistUserException();
	}
	
	public void isInvalidUser(String user_id, String encrypted_key) throws Exception {
		String user_key = encryptH.decryptContent(user_id, encrypted_key);
		if(userDAO.getUserValidation(user_id, user_key) < 1)
			throw new InvalidUserException();
	}
	
	public void hasFullFriends(String user_id) throws Exception {
		if(userFriendStatusDAO.checkUserFriendByUserId(user_id) == MAX_FRIEND_COUNT)
			throw new FriendsCountOutOfBoundsException();
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
	
	public AfterFavorUpData getFavorValue(String user_id) throws Exception {
		return new AfterFavorUpData(new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 3));
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
	
	public void increaseNegativeDiarys(String user_id) throws Exception {
		userDAO.addNegativeDiaryCount(user_id);
	}
	
	public void clearNegativeCount(String user_id) throws Exception {
		userDAO.updateNegativeDiaryCountToZero(user_id);
	}
	
	/**
	 * Credential Methods --> to be DEPRECATED
	 */
	private String makeUserKey() {
		return RandomStringUtils.randomAlphanumeric(16);
	}
	
	private String getEncryptedUserKey(String user_id) throws Exception {
		return encryptH.encryptContent(user_id, userDAO.getUserKey(user_id));
	}
	
}
