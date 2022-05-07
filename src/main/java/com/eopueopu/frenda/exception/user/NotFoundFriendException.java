package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class NotFoundFriendException extends FrendaException{
	private static final long serialVersionUID = 2198043140298224010L;

	public NotFoundFriendException() {
		super(NotFoundFriendException.class.getSimpleName());
	}
}
