package com.ngsky.tools.exception;

public class UnauthorizedException extends NoStackTraceException{

	public UnauthorizedException(){
	}
	
	public UnauthorizedException(String message){
		super(message);
	}
	public UnauthorizedException(String message, Object... params){
		super(message, params);
	}
}
