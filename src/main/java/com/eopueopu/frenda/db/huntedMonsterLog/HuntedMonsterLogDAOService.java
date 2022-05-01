package com.eopueopu.frenda.db.huntedMonsterLog;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eopueopu.frenda.exception.monster.InvalidIncreaseFavorConditionException;

@Repository
public class HuntedMonsterLogDAOService implements HuntedMonsterLogDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertMonsterLog(HuntedMonsterLog hunted_monster_log) throws Exception {
		HuntedMonsterLogMapper mapper = sqlSession.getMapper(HuntedMonsterLogMapper.class);
		mapper.insertMonsterLog(hunted_monster_log);
		
	}

	@Override
	public void updateFavorIncreased(String user_id) throws Exception {
		HuntedMonsterLogMapper mapper = sqlSession.getMapper(HuntedMonsterLogMapper.class);
		mapper.updateFavorIncreased(user_id);
		
	}

	@Override
	public Boolean getFavorIncreasedValue(String user_id) throws Exception {
		HuntedMonsterLogMapper mapper = sqlSession.getMapper(HuntedMonsterLogMapper.class);
		Boolean result = mapper.getFavorIncreasedValue(user_id);
			
		return result == null ? false : result;
	}
	
	
}
