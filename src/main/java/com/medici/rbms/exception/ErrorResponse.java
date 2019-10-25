package com.medici.rbms.exception;

/**
 * The Class ErrorResponse.
 *
 * @author ankadlak
 */
public class ErrorResponse {

	/** The errors. */
	private String errors;

	/**
	 * Instantiates a new error response.
	 *
	 * @param errors the errors
	 */
	public ErrorResponse(String errors) {
		this.errors = errors;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public String getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(String errors) {
		this.errors = errors;
	}

}
