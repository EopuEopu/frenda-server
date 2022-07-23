package com.eopueopu.frenda.handler.util;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(FrendaException.class)
	public Response handleFrendaException(FrendaException e) {
		logger.error(e.getMessage());
		return response.getForm(new ErrorData(e));
	}
	
	@ExceptionHandler(SQLException.class)
	public Response handleSQLException(SQLException e) {
		logger.error(e.getMessage());
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("SQLException")));
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Response handleNotFoundException() {
		logger.error("NotExistServiceException");
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("NotExistServiceException")));
	}
	
	@ExceptionHandler(Exception.class)
	public Response handleOtherException(Exception e) {
		logger.error("ERROR CAUSED BY {}", e.getClass().getName());
		return response.getForm(new ErrorData(FrendaExceptionType.valueOf("RuntimeException")));
	}
	
}
