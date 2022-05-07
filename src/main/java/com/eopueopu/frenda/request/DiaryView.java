package com.eopueopu.frenda.request;

import io.swagger.annotations.ApiModelProperty;

public class DiaryView {
	
	@ApiModelProperty(example = "LWWrVCEK7/ZJmA0/VlIYpw==", required = true, value="사용자 key")
	private String user_key;

	public String getUser_key() {
		return user_key;
	}
}
