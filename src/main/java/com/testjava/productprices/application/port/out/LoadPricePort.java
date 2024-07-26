package com.testjava.productprices.application.port.out;

import java.sql.Timestamp;

import com.testjava.productprices.domain.Price;

/**
 * The Interface LoadPricePort.
 */
public interface LoadPricePort {
	
	/**
	 * Load.
	 *
	 * @param date the date
	 * @param productId the product id
	 * @param brandId the brand id
	 * @return the price
	 */
	Price load(Timestamp date, Long productId, Long brandId);
}
