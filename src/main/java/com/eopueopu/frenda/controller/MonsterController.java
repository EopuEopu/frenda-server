package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.MonsterHandler;
import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
    @ApiOperation(value = "몬스터 사냥 후 로그 저장", notes = "몬스터 사냥 여부(portal open)를 체크하여 몬스터 사냥 후 log 저장")
	@ApiResponses({
		@ApiResponse(code = 200, message = "몬스터 사냥 완료"),
		@ApiResponse(code = 5100, message = "portal이 열리지 않았는데 사냥 시도"),
	})
	public Response huntMonster(String userId) throws Exception {
		
		user.isNotPresentUser(userId);
		
		monster.canAccessHuntingZone(userId);
		
		return response.getForm(monster.processHunting(userId));
	}
	
	@GetMapping("/favor-value")
    @ApiOperation(value = "몬스터 사냥 이후 호감도 상승", notes = "몬스터 사냥 후 홈으로 돌아와 호감도를 올리는 애니메이션 수행 후 실제 호감도 증가")
	@ApiResponses({
		@ApiResponse(code = 200, message = "호감도 상승 완료"),
		@ApiResponse(code = 5101, message = "호감도를 이미 올렸거나 올릴 수 없는 상태에서 호감도 증가 시도"),
	})
	public Response addFavorAfterHuntMonster(String userId) throws Exception {
		
		user.isNotPresentUser(userId);
		
		monster.isFavorIncreased(userId);
		
		return response.getForm(monster.increaseFavor(userId));
	}
	
}
