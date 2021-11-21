package com.diary.frienda.db.huntedMonsterLog;

public class HuntedMonsterLog {
	private int log_id;
	private String user_id;
	private int monster_id;
	
	public HuntedMonsterLog(String user_id, int monster_id) {
		this.user_id = user_id;
		this.monster_id = monster_id;
	}
	
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getMonster_id() {
		return monster_id;
	}
	public void setMonster_id(int monster_id) {
		this.monster_id = monster_id;
	}
	
	
}
