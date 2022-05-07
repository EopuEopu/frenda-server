package com.eopueopu.frenda.exception.diary;

import com.eopueopu.frenda.exception.FrendaException;

public class InexistSentimentException extends FrendaException {
	private static final long serialVersionUID = 5663087336783667119L;

	public InexistSentimentException() {
		super(InexistSentimentException.class.getSimpleName());
	}
}
