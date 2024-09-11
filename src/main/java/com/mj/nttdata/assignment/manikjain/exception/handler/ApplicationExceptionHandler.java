package com.mj.nttdata.assignment.manikjain.exception.handler;

import java.util.Optional;
import java.util.function.BiFunction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.mj.nttdata.assignment.manikjain.exception.InvalidPlayerOperation;

@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(value = {InvalidPlayerOperation.class})
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		return ResponseEntity.of(Optional.of(prepareExceptionResponse.apply(ex.getMessage(), HttpStatus.BAD_REQUEST.name())));
	}
	
	private BiFunction<String, String, ExceptionResponse> prepareExceptionResponse = (message, status) -> {
		return ExceptionResponse.builder().message(message).status(status).build();
	};
}
