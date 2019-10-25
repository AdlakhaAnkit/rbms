package com.medici.rbms.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The Interface DateValidator- Custom Validator for dates
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Constraint(validatedBy = DateValidatorImpl.class)
public @interface DateValidator {
	String message() default "{date}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}