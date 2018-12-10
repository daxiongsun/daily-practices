package com.ngsky.tools.exception;

public class ForbiddenException extends NoStackTraceException{
	
	public ForbiddenException(){
	}
	
	public ForbiddenException(String message){
		super(message);
	}
	
	public ForbiddenException(String message, Object... params){
		super(message, params);
	}

}
