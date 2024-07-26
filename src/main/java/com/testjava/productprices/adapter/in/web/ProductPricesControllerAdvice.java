package com.testjava.productprices.adapter.in.web;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.testjava.productprices.domain.Price;

/**
 * The Class ProductPricesControllerAdvice.
 */
@ControllerAdvice
public class ProductPricesControllerAdvice {

	/**
	 * Price exception handle.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity priceExceptionHandle(Exception ex) {
		if (ex.getCause() instanceof ConversionFailedException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Price.INCORRECT_DATE_FORMAT);
		} else if (ex.getCause() instanceof NumberFormatException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Price.INCORRECT_NUMBER_FORMAT);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
