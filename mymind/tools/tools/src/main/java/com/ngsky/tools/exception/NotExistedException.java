package com.ngsky.tools.exception;

public class NotExistedException extends NoStackTraceException{

	public NotExistedException(){
	}
	
	public NotExistedException(String message){
		super(message);
	}

	public NotExistedException(String message, Object... params){
		super(message, params);
	}
}
