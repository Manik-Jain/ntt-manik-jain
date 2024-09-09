package com.mj.nttdata.assignment.manikjain.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mj.nttdata.assignment.manikjain.exception.InvalidPlayerOperation;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(value = {InvalidPlayerOperation.class})
	public Object handle() {
		return null;
	}

}
