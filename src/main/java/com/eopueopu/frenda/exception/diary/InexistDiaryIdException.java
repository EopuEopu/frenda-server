package com.eopueopu.frenda.exception.diary;

import com.eopueopu.frenda.exception.FrendaException;

public class InexistDiaryIdException extends FrendaException {
	private static final long serialVersionUID = -6629140648108888550L;

	public InexistDiaryIdException() {
		super(InexistDiaryIdException.class.getSimpleName());
	}
}
