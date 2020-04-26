package com.tivanov.offermanager.domain.model.exception;

public abstract class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = -7814050935839582581L;

	public abstract String getErrorCode();
	
}
