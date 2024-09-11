package com.mj.nttdata.assignment.manikjain.exception.handler;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
	
	private String message;
	private String status;

}
