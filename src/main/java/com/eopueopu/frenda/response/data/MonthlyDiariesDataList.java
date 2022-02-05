package com.eopueopu.frenda.response.data;

import java.util.List;

import com.eopueopu.frenda.response.Data;
import com.eopueopu.frenda.response.data.sub.MonthlyDiaries;

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
