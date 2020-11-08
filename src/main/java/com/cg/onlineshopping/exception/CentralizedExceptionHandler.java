package com.cg.onlineshopping.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CentralizedExceptionHandler.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String addressAlreadyExists(Exception e) {
		LOGGER.error("Some exception occured, errorlog :", e);
		return e.getMessage();
	}

}
