package com.diary.frienda.db.huntedMonsterLog;

public interface HuntedMonsterLogMapper {
	public boolean getFavorIncreasedValue(String user_id) throws Exception;
	public void insertMonsterLog(HuntedMonsterLog hunted_monster_log) throws Exception;
	public void updateFavorIncreased(String user_id) throws Exception;
}
