package com.mj.nttdata.assignment.manikjain.common;

public enum ErrorMessages {
	
	INVALID_PLAYER_ID("Invalid Player Id"),
	INVALID_MATCH_TYPE("Invalid Match type"),
	INVALID_COUNTRY("Invalid country"),
	DUPLICATE_PLAYER("Attempting to add a duplicate player record")
	
	;
	
	private final String message;
	
	ErrorMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
