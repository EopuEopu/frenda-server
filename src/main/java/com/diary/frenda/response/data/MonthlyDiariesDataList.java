package com.diary.frenda.response.data;

import java.util.List;

import com.diary.frenda.response.Data;
import com.diary.frenda.response.data.sub.MonthlyDiaries;

public class MonthlyDiariesDataList implements Data {
	private List<MonthlyDiaries> monthlyDiaries;

	public MonthlyDiariesDataList(List<MonthlyDiaries> monthlyDiaries) {
		this.monthlyDiaries = monthlyDiaries;
	}
	
	public List<MonthlyDiaries> getMonthlyDiaries() {
		return monthlyDiaries;
	}

	public void setMonthlyDiaries(List<MonthlyDiaries> monthlyDiaries) {
		this.monthlyDiaries = monthlyDiaries;
	}
	
	
}
