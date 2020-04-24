package com.tivanov.offermanager.domain.model.exception;

public abstract class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = -7814050935839582581L;

	public BaseException() {
		super();
	}

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public abstract String getErrorCode();
	
}
