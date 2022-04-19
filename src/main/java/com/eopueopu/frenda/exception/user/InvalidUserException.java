package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class InvalidUserException extends FrendaException {
	private static final long serialVersionUID = 5351757135230038756L;

	public InvalidUserException() {
		super(InvalidUserException.class.getSimpleName());
	}
}
