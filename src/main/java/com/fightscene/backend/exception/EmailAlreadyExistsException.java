package com.fightscene.backend.exception;

public class EmailAlreadyExistsException extends RuntimeException {
	
	public EmailAlreadyExistsException() {
		super("Email already exist");
	}

}
