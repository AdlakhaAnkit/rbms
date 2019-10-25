package com.medici.rbms.exception;

import org.springframework.http.HttpStatus;

/**
 * The Class RoomsCustomException.
 */
public class RoomsCustomException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4223098469161602787L;
	
	/** The error message. */
	private final String errorMessage;
	
	/** The http status. */
	private final HttpStatus httpStatus;

	/**
	 * Instantiates a new rooms custom exception.
	 *
	 * @param httpStatus the http status
	 * @param errorMessage the error message
	 */
	public RoomsCustomException(HttpStatus httpStatus, String errorMessage) {
		super(errorMessage);
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String toString() {
		return "RoomsCustomException [errorMessage=" + errorMessage + ", httpStatus=" + httpStatus + "]";
	}

}
