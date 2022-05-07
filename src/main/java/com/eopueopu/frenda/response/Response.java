package com.eopueopu.frenda.response;

import io.swagger.annotations.ApiModelProperty;

public class Response {
	
	@ApiModelProperty(example = "200", required = true, value="Response 반환 완료")
	private int status_code;
	
	@ApiModelProperty(example = "Success / Fail", required = true, value="예외 상황 없이 Response 생성 / 예외 상황에서 Request 시")
	private String message;
	
	@ApiModelProperty(required = true, value="API에 맞는 데이터 or 에러 메시지")
	private Data data;
	
	public Response(int status_code, String message, Data data) {
		this.status_code = status_code;
		this.message = message;
		this.data = data;
	}

	public int getStatus_code() {
		return status_code;
	}

	public String getMessage() {
		return message;
	}
	
	public Data getData() {
		return data;
	}
	
}
