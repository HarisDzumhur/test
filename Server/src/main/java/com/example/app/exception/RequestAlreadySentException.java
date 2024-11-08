package com.example.app.exception;

public class RequestAlreadySentException extends RuntimeException{
	
	public RequestAlreadySentException(String message)
	{
		super(message);
	}

}
