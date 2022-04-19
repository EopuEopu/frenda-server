package com.eopueopu.frenda.exception;

public class FrendaException extends RuntimeException {
	private static final long serialVersionUID = 9031348967762542628L;
	
	protected final FrendaExceptionType error_type;
	
	protected FrendaException(String className) {
		super(FrendaExceptionType.valueOf(className).toString());
		this.error_type = FrendaExceptionType.valueOf(className);
	}
	
	public FrendaExceptionType getError_type() {
		return error_type;
	}
	
}
