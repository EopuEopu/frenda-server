package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.db.diarySentiment.DiarySentimentDAOService;
import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLog;
import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLogDAOService;
import com.eopueopu.frenda.db.user.UserDAOService;
import com.eopueopu.frenda.db.userFriendStatus.UserFriendStatusDAOService;
import com.eopueopu.frenda.exception.FrendaExceptionType;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.AfterFavorUpData;
import com.eopueopu.frenda.response.data.AfterMonsterData;
import com.eopueopu.frenda.response.data.error.ErrorData;
import com.eopueopu.frenda.response.data.sub.FavorData;

//@Api(tags = "몬스터 사냥 관련 API")
@RestController
@RequestMapping("/monster")
public class MonsterController {
	@Autowired
	private UserHandler userH;
	
	@Autowired
	private ResponseHandler responseH;
	
	@Autowired
	private UserDAOService userDAO;
	
	@Autowired
	private UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	private DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	private HuntedMonsterLogDAOService huntedMonsterLogDAO;
	
	@GetMapping("/log")
	public Response huntMonster(@RequestParam("userId") String user_id) throws Exception {
		userH.isNotPresentUser(user_id, false);
		huntedMonsterLogDAO.insertMonsterLog(new HuntedMonsterLog(user_id));
		userDAO.updateNegativeDiaryCountToZero(user_id);
		
		return responseH.getForm(new AfterMonsterData(diarySentimentDAO.getNegativeSentimentCount(user_id)));
	}
	
	@GetMapping("/favor-value")
	public Response addFavorAfterHuntMonster(@RequestParam("userId") String user_id) throws Exception {
		userH.isNotPresentUser(user_id, false);
		
		if(huntedMonsterLogDAO.getFavorIncreasedValue(user_id))
			return responseH.getForm(new ErrorData(FrendaExceptionType.InvalidIncreaseFavorConditionException));
		
		userH.updateFriendFavor(user_id, 3);
		huntedMonsterLogDAO.updateFavorIncreased(user_id);
		
		return responseH.getForm(new AfterFavorUpData(new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 3)));
		
		
		
	}
	
}
