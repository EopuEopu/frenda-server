package com.eopueopu.frenda.handler.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingHandler implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingHandler.class);
	public static final String TRACING_ID = "trace.id";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		MDC.put(TRACING_ID, UUID.randomUUID().toString());
		
		logger.info("REQUEST URI = {} - {}", request.getRequestURI(), request.getMethod());
		logger.info("REQUEST userId = {}", request.getParameter("userId"));
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info("RESPONSE status = {}", response.getStatus());
	}
	
}
