package com.example.app.exception;

public class UserAlreadyInGroupException extends RuntimeException{
	
	public UserAlreadyInGroupException(String message)
	{
		super(message);
	}

}
