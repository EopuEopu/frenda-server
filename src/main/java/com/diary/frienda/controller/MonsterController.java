package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.db.diarySentiment.DiarySentimentDAOService;
import com.diary.frienda.db.huntedMonsterLog.HuntedMonsterLog;
import com.diary.frienda.db.huntedMonsterLog.HuntedMonsterLogDAOService;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.handler.ResponseHandler;
import com.diary.frienda.handler.UserHandler;
import com.diary.frienda.response.Response;
import com.diary.frienda.response.data.AfterFavorUpData;
import com.diary.frienda.response.data.AfterMonsterData;
import com.diary.frienda.response.data.sub.FavorData;

@RestController
public class MonsterController {
	@Autowired
	private UserHandler userH;
	
	@Autowired
	UserDAOService userDAO;
	
	@Autowired
	UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	HuntedMonsterLogDAOService huntedMonsterLogDAO;
	
	@RequestMapping(value = "/monster-log", method = RequestMethod.GET)
	public Response huntMonster(@RequestParam("userId") String user_id) throws Exception {
		if(userH.isNotPresentUser(user_id))
			return ResponseHandler.failResponse();
		
		huntedMonsterLogDAO.insertMonsterLog(new HuntedMonsterLog(user_id));
		userDAO.updateNegativeDiaryCountToZero(user_id);
		
		return ResponseHandler.successResponse(new AfterMonsterData(diarySentimentDAO.getNegativeSentimentCount(user_id)));
	}
	
	@RequestMapping(value = "/favor-value", method = RequestMethod.GET)
	public Response addFavorAfterHuntMonster(@RequestParam("userId") String user_id) throws Exception {
		if(userH.isNotPresentUser(user_id) || huntedMonsterLogDAO.getFavorIncreasedValue(user_id))
			return ResponseHandler.failResponse();
		
		userH.updateFriendFavor(user_id, 3);
		huntedMonsterLogDAO.updateFavorIncreased(user_id);
		
		return ResponseHandler.successResponse(new AfterFavorUpData(new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 3)));
	}
	
}
