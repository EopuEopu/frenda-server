package com.eopueopu.frenda.exception.user;

public class NotPresentUserException extends Exception{
	private static final long serialVersionUID = -9100495892967293659L;

	public NotPresentUserException() {
		super(NotPresentUserException.class.getSimpleName());
	}
}
