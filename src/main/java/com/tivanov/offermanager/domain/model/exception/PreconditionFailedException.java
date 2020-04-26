package com.tivanov.offermanager.domain.model.exception;

public class PreconditionFailedException extends BaseException {
	
	/** 
	 * Exception thrown when the entry data is not formatted according to contract.
	 * 
	 */
	private static final long serialVersionUID = 5563179157330120601L;

	@Override
	public String getErrorCode() {
		return "DATA_REQUEST_NOT_CORRECT";
	}

}

