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
	public String getHunted_time() {
		return hunted_time;
	}
	public void setHunted_time(String hunted_time) {
		this.hunted_time = hunted_time;
	}

	public boolean isFavor_increased() {
		return favor_increased;
	}

	public void setFavor_increased(boolean favor_increased) {
		this.favor_increased = favor_increased;
	}
	
}
