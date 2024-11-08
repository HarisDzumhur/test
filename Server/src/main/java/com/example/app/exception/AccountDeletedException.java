package com.example.app.exception;

public class AccountDeletedException extends RuntimeException {
	
	public AccountDeletedException(String message)
	{
		super(message);
	}

}
