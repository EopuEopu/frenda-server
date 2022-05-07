package com.eopueopu.frenda.handler.util;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.eopueopu.frenda.exception.FrendaException;
import com.eopueopu.frenda.exception.FrendaExceptionType;
import com.eopueopu.frenda.response.Response;
import com.eopueopu.frenda.response.data.error.ErrorData;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private ResponseHandler response;
	
	@ExceptionHandler(FrendaException.class)
	public Response handleFrendaException(FrendaException e) {
		return response.getForm(new ErrorData(e));
	}
	
	@ExceptionHandler(SQLException.class)
	public Response handleSQLException(SQLException e) {
		e.printStackTrace();
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("SQLException")));
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Response handleNotFoundException() {
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("NotExistServiceException")));
	}
	
	@ExceptionHandler(Exception.class)
	public Response handleOtherException(Exception e) {
		e.printStackTrace();
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("RuntimeException")));
	}
	
}
