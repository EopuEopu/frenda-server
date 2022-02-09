package com.eopueopu.frenda.db.huntedMonsterLog;

public interface HuntedMonsterLogDAO {
	public boolean getFavorIncreasedValue(String user_id) throws Exception;
	public void insertMonsterLog(HuntedMonsterLog hunted_monster_log) throws Exception;
	public void updateFavorIncreased(String user_id) throws Exception;
}
