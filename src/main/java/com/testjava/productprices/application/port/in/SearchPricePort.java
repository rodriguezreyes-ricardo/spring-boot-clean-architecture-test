package com.testjava.productprices.application.port.in;

import com.testjava.productprices.domain.Price;

/**
 * The Interface SearchPricePort.
 */
public interface SearchPricePort {

	/**
	 * Send.
	 *
	 * @param params the params
	 * @return true, if successful
	 */
	public Price search(InParams params);
}
