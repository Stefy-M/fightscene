package com.fightscene.backend.exception;

public class InvalidCredentialsException extends RuntimeException{
	
	public InvalidCredentialsException() {
		super("Invalid credentials");
	}

}
