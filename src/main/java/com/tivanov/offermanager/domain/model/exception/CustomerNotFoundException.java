package com.tivanov.offermanager.domain.model.exception;

public class CustomerNotFoundException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2111187104109189661L;

	/** 
	 * Exception thrown when the entry data is not formatted according to contract.
	 * 
	 */

	@Override
	public String getErrorCode() {
		return "CUSTOMER_NOT_FOUND";
	}

}

