package com.eopueopu.frenda.exception.user;

public class NotFoundFriendException extends Exception{
	private static final long serialVersionUID = 2198043140298224010L;

	public NotFoundFriendException() {
		super(NotFoundFriendException.class.getSimpleName());
	}
}
