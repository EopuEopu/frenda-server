package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class AlreadyExistUser extends FrendaException{
	private static final long serialVersionUID = -9596122429506710L;

	public AlreadyExistUser() {
		super(AlreadyExistUser.class.getSimpleName());
	}
}
