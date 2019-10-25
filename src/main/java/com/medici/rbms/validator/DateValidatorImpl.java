package com.medici.rbms.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

/**
 * Class DateValidatorImpl- Date Validation covering date is not null and date
 * is future date
 * 
 * @author ankadlak
 *
 */
@Component
public class DateValidatorImpl implements ConstraintValidator<DateValidator, LocalDate> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
		return date != null && (date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now()));
	}
}