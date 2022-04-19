package com.eopueopu.frenda.exception.user;

import com.eopueopu.frenda.exception.FrendaException;

public class NotPresentUserException extends FrendaException{
	private static final long serialVersionUID = -9100495892967293659L;

	public NotPresentUserException() {
		super(NotPresentUserException.class.getSimpleName());
	}
}
