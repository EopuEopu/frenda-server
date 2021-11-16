package com.diary.frienda;

import java.util.List;

import com.diary.frienda.db.user.User;
import com.diary.frienda.db.user.UserDAOService;

public class UserHandler {
	UserDAOService userDAO = null;
	
	public UserHandler(UserDAOService userDAO) {
		this.userDAO = userDAO;
	}
	
	public List<User> getAllIds() throws Exception{
		return userDAO.getIds();
	}
}
