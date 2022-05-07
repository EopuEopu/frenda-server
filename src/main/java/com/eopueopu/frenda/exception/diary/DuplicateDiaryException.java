package com.eopueopu.frenda.exception.diary;

import com.eopueopu.frenda.exception.FrendaException;

public class DuplicateDiaryException extends FrendaException {
	private static final long serialVersionUID = -9045072024023786973L;
	
	public DuplicateDiaryException() {
		super(DuplicateDiaryException.class.getSimpleName());
	}
}
