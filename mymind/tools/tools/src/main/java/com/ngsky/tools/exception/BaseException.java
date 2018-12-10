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
public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
