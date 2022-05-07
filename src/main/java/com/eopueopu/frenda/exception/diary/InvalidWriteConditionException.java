package com.eopueopu.frenda.exception.diary;

import com.eopueopu.frenda.exception.FrendaException;

public class InvalidWriteConditionException extends FrendaException {
	private static final long serialVersionUID = -4141084385732959667L;

	public InvalidWriteConditionException() {
		super(InvalidWriteConditionException.class.getSimpleName());
	}
}
