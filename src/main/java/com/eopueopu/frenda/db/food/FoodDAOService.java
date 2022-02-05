package com.eopueopu.frenda.db.food;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodDAOService implements FoodDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public Food getFoodBySentiment(String food_sentiment) throws Exception {
		FoodMapper mapper = sqlSession.getMapper(FoodMapper.class);
		return mapper.getFoodBySentiment(food_sentiment);
	}
}
