package com.example.app.exception;

public class AccountBlockedException extends RuntimeException {
	
	public AccountBlockedException(String message)
	{
		super(message);
	}

}