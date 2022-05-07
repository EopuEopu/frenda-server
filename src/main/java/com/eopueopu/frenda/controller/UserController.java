package com.eopueopu.frenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eopueopu.frenda.handler.UserHandler;
import com.eopueopu.frenda.handler.util.ResponseHandler;
import com.eopueopu.frenda.response.Response;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserHandler user;

	@Autowired
	ResponseHandler response;

	@GetMapping("/new-user")
	@ApiOperation(value = "회원 가입", notes = "소셜 로그인 후 일기 DB에 회원 ID 저장")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원 가입 완료"),
		@ApiResponse(code = 4201, message = "이미 존재하는 ID로 가입 시도"),
	})
	public Response makeNewUser(String userId) throws Exception {

		user.isAlreadyExistUser(userId);

		user.insertNewUserInfo(userId);

		return response.getForm(user.getUserKeyData(userId));
	}

	@GetMapping("/new-friend")
	@ApiOperation(value = "상상 친구 생성", notes = "해당 유저의 이미 등록된 친구 수를 파악하여 친구 생성이 가능한 경우 friendName 이름을 가진 친구 생성")
    @ApiImplicitParam(name = "friendName", value = "새로 등록할 친구 이름", required = true, dataType = "string", paramType = "query", defaultValue = "두두")
	@ApiResponses({
		@ApiResponse(code = 200, message = "상상 친구 추가 완료"),
		@ApiResponse(code = 4200, message = "최대 친구 수를 채운 상태에서 친구 추가 시도 시"),
	})
	public Response makeNewFriend(String userId, String friendName) throws Exception {

		user.isNotPresentUser(userId);
		user.hasFullFriends(userId);

		user.insertNewUserFriend(userId, friendName);

		return response.getForm(user.getFriendStatus(userId).convertToData());
	}

	@GetMapping("/key")
	@ApiOperation(value = "사용자의 키를 불러오기", notes = "일기를 작성할 때 필요한 사용자의 키를 가져오는 API(will be DEPRECATED)")
	public Response getUserKey(String userId) throws Exception {
		user.isNotPresentUser(userId);
		
		return response.getForm(user.getUserKeyData(userId));
	}

	@GetMapping("/info")
	@ApiOperation(value = "사용자의 정보 불러오기", notes = "어플 진입 시 사용자의 정보를 불러옴")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그인 및 사용자 정보 불러오기 완료"),
		@ApiResponse(code = 4100, message = "상상 친구를 등록하지 않은 상태에서 로그인 시도"),
	})
	public Response getUserStatus(String userId) throws Exception {
		user.isNotPresentUser(userId);
		
		return response.getForm(response.logInData(userId));

	}
}
