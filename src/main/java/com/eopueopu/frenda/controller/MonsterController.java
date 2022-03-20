package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.db.diarySentiment.DiarySentimentDAOService;
import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLog;
import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLogDAOService;
import com.eopueopu.frenda.db.user.UserDAOService;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatusDAOService;
import com.eopueopu.frenda.handler.ResponseHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.AfterFavorUpData;
import com.eopueopu.frenda.response.data.AfterMonsterData;
import com.eopueopu.frenda.response.data.sub.FavorData;

@RestController
public class MonsterController {
	@Autowired
	private UserHandler userH;
	
	@Autowired
	private ResponseHandler responseH;
	
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
		try {
			userH.isNotPresentUser(user_id);
			huntedMonsterLogDAO.insertMonsterLog(new HuntedMonsterLog(user_id));
			userDAO.updateNegativeDiaryCountToZero(user_id);
			
			return responseH.successResponse(new AfterMonsterData(diarySentimentDAO.getNegativeSentimentCount(user_id)));
		} catch(Exception e) {
			return responseH.failResponse(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/favor-value", method = RequestMethod.GET)
	public Response addFavorAfterHuntMonster(@RequestParam("userId") String user_id) throws Exception {
		try {
			userH.isNotPresentUser(user_id);
			if(huntedMonsterLogDAO.getFavorIncreasedValue(user_id))
				return responseH.failResponse("CannotIncreaseFavor");
			
			userH.updateFriendFavor(user_id, 3);
			huntedMonsterLogDAO.updateFavorIncreased(user_id);
			
			return responseH.successResponse(new AfterFavorUpData(new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 3)));
			
		}catch(Exception e) {
			return responseH.failResponse(e.getMessage());
		}
		
		
		
	}
	
}
