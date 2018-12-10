package com.ngsky.tools.exception;

public class ParamErrorException extends NoStackTraceException{

	public ParamErrorException(){
	}
	
	public ParamErrorException(String message){
		super(message);
	}
	
	public ParamErrorException(String message, Object... params){
		super(message, params);
	}
}
