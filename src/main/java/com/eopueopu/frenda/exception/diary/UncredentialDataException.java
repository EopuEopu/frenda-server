package com.eopueopu.frenda.exception.diary;

import com.eopueopu.frenda.exception.FrendaException;

public class UncredentialDataException extends FrendaException {
	private static final long serialVersionUID = 6268276562504667955L;

	public UncredentialDataException() {
		super(UncredentialDataException.class.getSimpleName());
	}
}
