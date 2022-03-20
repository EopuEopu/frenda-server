package com.eopueopu.frenda.exception.diary;

public class DuplicateDiaryException extends Exception {
	private static final long serialVersionUID = -9045072024023786973L;

	public DuplicateDiaryException() {
		super(DuplicateDiaryException.class.getSimpleName());
	}
}
