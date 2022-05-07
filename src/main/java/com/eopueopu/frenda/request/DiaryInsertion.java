package com.eopueopu.frenda.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DiaryInsertion {
	@ApiModelProperty(example = "LWWrVCEK7/ZJmA0/VlIYpw==", required = true, value="사용자 key")
	private String user_key;
	
	@ApiModelProperty(example = "T7QWvOhPrdRXpRVsMwtWu56+mtFgxiJ0K5GiG9vTNqdijBBCcEgIgN6mNxGkXEx0", required = true, value="일기 내용")
	private String content;
	
	private List<Stickers> stickers;
	
	@ApiModelProperty(example = "sad", required = true, value="사용자가 선택한 감정")
	private String user_selected_sentiment;
	
	public String getUser_key() {
		return user_key;
	}
	
	public String getContent() {
		return content;
	}
	
	public List<Stickers> getStickers() {
		return stickers;
	}
	
	public String getUser_selected_sentiment() {
		return user_selected_sentiment;
	}
	
}
