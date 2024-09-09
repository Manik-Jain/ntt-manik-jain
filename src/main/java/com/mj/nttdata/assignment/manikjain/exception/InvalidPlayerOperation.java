package com.mj.nttdata.assignment.manikjain.exception;

/**
 * 
 * Rumtime Exception that can be thrown in case an attempt made to add multiple ranking records for a player with same match type
 * 
 * @author manikjain
 *
 */
public class InvalidPlayerOperation extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidPlayerOperation() {
		super();
	}
	
	public InvalidPlayerOperation(String message) {
		super(message);
	}
}
