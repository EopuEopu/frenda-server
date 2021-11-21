package com.diary.frienda.db.huntedMonsterLog;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HuntedMonsterLogDAOService implements HuntedMonsterLogDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertMonsterLog(HuntedMonsterLog hunted_monster_log) throws Exception {
		HuntedMonsterLogMapper mapper = sqlSession.getMapper(HuntedMonsterLogMapper.class);
		mapper.insertMonsterLog(hunted_monster_log);
		
	}
	
	
}
