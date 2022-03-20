package com.eopueopu.frenda.exception.user;

public class InvalidUserException extends Exception {
	private static final long serialVersionUID = 5351757135230038756L;

	public InvalidUserException() {
		super(InvalidUserException.class.getSimpleName());
	}
}
