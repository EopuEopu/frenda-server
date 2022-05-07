package com.eopueopu.frenda.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLog;
import com.eopueopu.frenda.db.huntedMonsterLog.HuntedMonsterLogDAO;
import com.eopueopu.frenda.exception.monster.InvalidHuntConditionException;
import com.eopueopu.frenda.exception.monster.InvalidIncreaseFavorConditionException;
import com.eopueopu.frenda.response.data.AfterFavorUpData;
import com.eopueopu.frenda.response.data.AfterMonsterData;

@Service
public class MonsterHandler {
	@Autowired
	HuntedMonsterLogDAO monsterDAO;
	
	@Autowired
	UserHandler user;
	
	@Autowired
	DiaryHandler diary;
	
	public AfterMonsterData processHunting(String user_id) throws Exception {
		
		user.clearNegativeCount(user_id);
		
		monsterDAO.insertMonsterLog(new HuntedMonsterLog(user_id));
		
		return new AfterMonsterData(diary.getErasedNegativeFeelings(user_id));
	}
	
	public AfterFavorUpData increaseFavor(String user_id) throws Exception {
		
		user.updateFriendFavor(user_id, 3);
		
		monsterDAO.updateFavorIncreased(user_id);
		
		return user.getFavorValue(user_id);
	}
	
	public void isFavorIncreased(String user_id) throws Exception {
		
		if(!monsterDAO.getFavorIncreasedValue(user_id))
			throw new InvalidIncreaseFavorConditionException();
	}
	
	public void canAccessHuntingZone(String user_id) throws Exception {
		if(!user.getPortalOpen(user_id))
			throw new InvalidHuntConditionException();
	}
}
