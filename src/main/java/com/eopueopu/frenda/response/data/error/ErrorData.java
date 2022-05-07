package com.eopueopu.frenda.response.data.error;

import com.eopueopu.frenda.exception.FrendaException;
import com.eopueopu.frenda.exception.FrendaExceptionType;
import com.eopueopu.frenda.response.Data;

public class ErrorData implements Data {
	private String error_name;
	private int error_code;
	private String error_msg;

	public ErrorData(FrendaException e) {
		this.error_name = e.getError_type().getError_name();
		this.error_code = e.getError_type().getError_code();
		this.error_msg = e.getError_type().getError_msg();
	}
	
	public ErrorData(FrendaExceptionType type) {
		this.error_name = type.getError_name();
		this.error_code = type.getError_code();
		this.error_msg = type.getError_msg();
	}
	
	public String getError_name() {
		return error_name;
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public String getError_msg() {
		return error_msg;
	}

}
