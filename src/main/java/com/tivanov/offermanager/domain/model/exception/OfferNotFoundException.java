package com.tivanov.offermanager.domain.model.exception;

public class OfferNotFoundException extends BaseException {
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
		return "OFFER_NOT_FOUND";
	}

	public OfferNotFoundException() {
		super();
	}

	public OfferNotFoundException(String message) {
		super(message);
	}

	public OfferNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
