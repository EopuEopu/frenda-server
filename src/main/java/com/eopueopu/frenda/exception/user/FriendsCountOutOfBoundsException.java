package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class FriendsCountOutOfBoundsException extends FrendaException {
	private static final long serialVersionUID = 5432928224668630611L;
	
	public FriendsCountOutOfBoundsException() {
		super(FriendsCountOutOfBoundsException.class.getSimpleName());
	}
}
