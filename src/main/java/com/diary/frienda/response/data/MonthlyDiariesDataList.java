package com.diary.frienda.response.data;

import java.util.List;

import com.diary.frienda.response.Data;

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
