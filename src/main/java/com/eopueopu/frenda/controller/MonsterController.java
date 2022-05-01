package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.MonsterHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;

//@Api(tags = "몬스터 사냥 관련 API")
@RestController
@RequestMapping("/monster")
public class MonsterController {
	@Autowired
	private MonsterHandler monster;
	
	@Autowired
	private UserHandler user;
	
	@Autowired
	private ResponseHandler response;
	
	@GetMapping("/log")
	public Response huntMonster(String userId) throws Exception {
		
		user.isNotPresentUser(userId, false);

		return response.getForm(monster.processHunting(userId));
	}
	
	@GetMapping("/favor-value")
	public Response addFavorAfterHuntMonster(String userId) throws Exception {
		
		user.isNotPresentUser(userId, false);
		
		monster.isFavorIncreased(userId);
		
		return response.getForm(monster.increaseFavor(userId));
	}
	
}
