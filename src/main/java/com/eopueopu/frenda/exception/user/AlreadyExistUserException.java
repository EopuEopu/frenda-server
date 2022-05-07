package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class AlreadyExistUserException extends FrendaException{
	private static final long serialVersionUID = -9596122429506710L;

	public AlreadyExistUserException() {
		super(AlreadyExistUserException.class.getSimpleName());
	}
}
