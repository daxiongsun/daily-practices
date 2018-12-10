package com.ngsky.tools.exception;

/**
 * <dl>
 * <dt>BaseException</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/20/2018 6:44 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class NoStackTraceException extends BaseException{
	
	private String message;
	private Object[] params;

	public NoStackTraceException(){
		this(null, null, false, true);
	}
	
	public NoStackTraceException(String message) {
        this(message, null, false, true);
        this.message = message;
    }
	
	public NoStackTraceException(String message, Object... params) {
        this(message, null, false, true);
        this.message = message;
        this.params = params;
    }
	
	public NoStackTraceException(String message, Throwable cause) {
		this(message, cause, false, true);
    }
	
	public NoStackTraceException(Throwable cause) {
        this(null, cause, false, true);
    }
	
	protected NoStackTraceException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public String getLessStackTrace(){
		StackTraceElement[] stackTraceArray = getStackTrace();
		StringBuilder sb = new StringBuilder();
		for(StackTraceElement st : stackTraceArray){
			String stLog = st.toString();
			if(stLog.contains("com.jielingtianxia.yuefu") && !stLog.contains("$"))
				sb.append("at ").append(stLog).append("\r\n");
		}
		return sb.toString();
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
