package com.eopueopu.frenda.db.clovaTemp;

public interface ClovaTempDAO {
	public ClovaTemp getTodayTempDataByUserId(String user_id) throws Exception;
	public void insertTodayTempData(ClovaTemp clovaTemp) throws Exception;
	public void deleteTodayTempData(String user_id) throws Exception;
}
