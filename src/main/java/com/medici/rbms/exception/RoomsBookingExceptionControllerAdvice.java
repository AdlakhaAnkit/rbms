package com.medici.rbms.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medici.rbms.constants.RBMSConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomsBookingExceptionControllerAdvice.
 */
@ControllerAdvice
public class RoomsBookingExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	/** The log. */
	private Logger log = LoggerFactory.getLogger(RoomsBookingExceptionControllerAdvice.class);

	/**
	 * Handle custom exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(RoomsCustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(RoomsCustomException e) {
		log.error("Exception Occurrend", e);
		return new ResponseEntity<>(new ErrorResponse(e.getMessage()), e.getHttpStatus());
	}

	/**
	 * Handle exception.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("Exception Occurrend", e);
		return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put(RBMSConstants.ERRORS, RBMSConstants.DATE_FORMAT_MESSAGE);
		return new ResponseEntity<>(body, headers, status);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put(RBMSConstants.ERRORS, errors);

		return new ResponseEntity<>(body, headers, status);
	}

}
