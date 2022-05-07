package com.eopueopu.frenda.exception;

public enum FrendaExceptionType {
	NotExistServiceException(404, "NotExistServiceException", "해당하는 서비스가 존재하지 않습니다."),
	
	NotPresentUserException(2100, "NotPresentUserException", "해당하는 사용자가 없습니다."),
	InvalidUserException(2101, "InvalidUserException", "사용자 인증 정보가 일치하지 않습니다."),
	UncredentialDataException(2200, "UncredentialDataException", "Data의 암호화가 필요합니다."),
	
	DuplicateDiaryException(3100, "DuplicateDiaryException", "이미 오늘의 일기를 작성하셨습니다."),
	InvalidWriteConditionException(3101, "InvalidWriteConditionException", "일기를 작성할 수 없는 조건이 있습니다."),
	InexistSentimentException(3102, "InexistSentimentException", "선택할 수 없는 감정입니다."),
	InexistDiaryIdException(3200, "InexistDiaryIdException", "존재하지 않는 다이어리 ID 입니다."),
	
	NotFoundFriendException(4100, "NotFoundFriendException", "Frenda가 존재하지 않습니다."),
	FriendsCountOutOfBoundsException(4200, "FriendsCountOutOfBoundsException", "생성 가능한 최대의 Frenda를 생성했습니다."),
	AlreadyExistUser(4201, "AlreadyExistUserException", "이미 존재하는 사용자입니다."),
	
	InvalidHuntConditionException(5100, "InvalidHuntConditionException", "몬스터를 사냥할 수 없습니다."),
	InvalidIncreaseFavorConditionException(5101, "InvalidIncreaseFavorConditionException", "호감도를 증가할 수 없습니다."),
	
	SQLException(8100, "SQLException", "DataBase 연결 오류"),
	
	RuntimeException(9100, "RuntimeException", "Server 오류");
	
	
	private final int error_code;
	private final String error_name;
	private final String error_msg;
	
	private FrendaExceptionType(int error_code, String error_name, String error_msg) {
		this.error_code = error_code;
		this.error_name = error_name;
		this.error_msg = error_msg;
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public String getError_name() {
		return error_name;
	}
	
	public String getError_msg() {
		return error_msg;
	}
	
	@Override
	public String toString() {
		return error_name + "(" + error_code + ") : " + error_msg;
	}
	
}
