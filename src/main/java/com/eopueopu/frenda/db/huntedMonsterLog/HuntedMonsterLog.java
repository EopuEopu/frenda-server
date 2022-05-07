package com.eopueopu.frenda.db.huntedMonsterLog;

public class HuntedMonsterLog {
	private int log_id;
	private String user_id;
	private int monster_id;
	private boolean favor_increased;
	private String hunted_time;
	
	public HuntedMonsterLog(String user_id) {
		this.user_id = user_id;
		this.monster_id = 1;
	}
	
	public int getLog_id() {
		return log_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public int getMonster_id() {
		return monster_id;
	}
	
	public String getHunted_time() {
		return hunted_time;
	}

	public boolean getIsFavor_increased() {
		return favor_increased;
	}
}
