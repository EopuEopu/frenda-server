package com.eopueopu.frenda.exception.monster;

import com.eopueopu.frenda.exception.FrendaException;

public class InvalidHuntConditionException extends FrendaException {
	private static final long serialVersionUID = 6261902261340799800L;

	public InvalidHuntConditionException() {
		super(InvalidHuntConditionException.class.getSimpleName());
	}
}
