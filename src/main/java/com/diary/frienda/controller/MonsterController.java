package com.diary.frienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diary.frienda.db.diarySentiment.DiarySentimentDAOService;
import com.diary.frienda.db.huntedMonsterLog.HuntedMonsterLog;
import com.diary.frienda.db.huntedMonsterLog.HuntedMonsterLogDAOService;
import com.diary.frienda.db.request.AddFavorValueRequest;
import com.diary.frienda.db.user.UserDAOService;
import com.diary.frienda.db.userFriendStatus.UserFriendStatusDAOService;
import com.diary.frienda.response.Response;
import com.diary.frienda.response.data.AfterMonsterData;
import com.diary.frienda.response.data.FavorData;

@RestController
public class MonsterController {
	@Autowired
	UserDAOService userDAO;
	
	@Autowired
	UserFriendStatusDAOService userFriendStatusDAO;
	
	@Autowired
	DiarySentimentDAOService diarySentimentDAO;
	
	@Autowired
	HuntedMonsterLogDAOService huntedMonsterLogDAO;
	
	Response res = null;
	
	@RequestMapping(value = "/monster-log", method = RequestMethod.GET)
	public Response huntMonster(@RequestParam("userId") String user_id) throws Exception {
		if(userDAO.checkUserId(user_id) < 1)
			return new Response(500, "존재하지 않는 사용자입니다.", null);
		
		huntedMonsterLogDAO.insertMonsterLog(new HuntedMonsterLog(user_id, 1));
		userFriendStatusDAO.addFavorValue(new AddFavorValueRequest(user_id, 3));
		userDAO.updateNegativeDiaryCountToZero(user_id);
		
		res = new Response(200, "성공적으로 몬스터 로그를 저장했습니다.", 
							new AfterMonsterData(new FavorData(userFriendStatusDAO.getFavorValueByUserId(user_id), 3)
									,diarySentimentDAO.getNegativeSentimentCount(user_id)));
		
		return res;
	}
}
