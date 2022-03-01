package com.eopueopu.frenda.db.huntedMonsterLog;

public interface HuntedMonsterLogMapper {
	public Boolean getFavorIncreasedValue(String user_id) throws Exception;
	public void insertMonsterLog(HuntedMonsterLog hunted_monster_log) throws Exception;
	public void updateFavorIncreased(String user_id) throws Exception;
}
