package com.diary.frienda.request;

import java.util.List;

public class AdditionDiary {
	private String content;
	private List<Stickers> stickers;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Stickers> getStickers() {
		return stickers;
	}
	public void setStickers(List<Stickers> stickers) {
		this.stickers = stickers;
	}
	
	
}
