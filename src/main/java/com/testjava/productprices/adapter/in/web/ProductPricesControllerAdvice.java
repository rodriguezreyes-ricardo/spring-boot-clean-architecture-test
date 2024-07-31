package com.testjava.productprices.adapter.in.web;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.testjava.productprices.domain.ExceptionPrice;
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
	public ResponseEntity priceBadRequestExceptionHandle(Exception ex) {
		if (ex.getCause() instanceof ConversionFailedException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Price.INCORRECT_DATE_FORMAT);
		} else if (ex.getCause() instanceof NumberFormatException) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Price.INCORRECT_NUMBER_FORMAT);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Price not found exception handle.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ResponseEntity priceNotFoundExceptionHandle(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Price.NOT_FOUND);
	}

	/**
	 * Price internal server exception handle.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({ ExceptionPrice.class })
	public ResponseEntity priceInternalServerExceptionHandle(ExceptionPrice ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Price.INTERNAL_SERVER_ERROR);
	}

}
