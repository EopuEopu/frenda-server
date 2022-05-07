package com.eopueopu.frenda.request;

import io.swagger.annotations.ApiModelProperty;

public class SentimentAnalysis {
	
	@ApiModelProperty(example = "LWWrVCEK7/ZJmA0/VlIYpw==", required = true, value="사용자 key")
	private String user_key;
	
	@ApiModelProperty(example = "T7QWvOhPrdRXpRVsMwtWu56+mtFgxiJ0K5GiG9vTNqdijBBCcEgIgN6mNxGkXEx0", required = true, value="일기 내용")
	private String content;
	
	public String getUser_key() {
		return user_key;
	}
	
	public String getContent() {
		return content;
	}
}
