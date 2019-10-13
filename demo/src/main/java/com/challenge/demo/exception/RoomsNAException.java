package com.challenge.demo.exception;

import org.springframework.http.HttpStatus;

public class RoomsNAException extends RuntimeException {

	private static final long serialVersionUID = -4223098469161602787L;
	private final String errorMessage;
	private final HttpStatus httpStatus;
	private final Class<?> className;

	public RoomsNAException(HttpStatus httpStatus, String errorMessage, Class<?> className) {
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.className = className;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Class<?> getClassName() {
		return className;
	}

	@Override
	public String toString() {
		return "RoomsNAException [errorMessage=" + errorMessage + ", httpStatus=" + httpStatus + ", className="
				+ className + "]";
	}

}
