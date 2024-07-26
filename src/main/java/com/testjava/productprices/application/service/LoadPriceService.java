package com.testjava.productprices.application.service;

import javax.transaction.Transactional;

import com.testjava.productprices.application.port.in.InParams;
import com.testjava.productprices.application.port.in.SearchPricePort;
import com.testjava.productprices.application.port.out.LoadPricePort;
import com.testjava.productprices.common.UseCase;
import com.testjava.productprices.domain.Price;

/**
 * The Class LoadPriceService.
 */
@UseCase
public class LoadPriceService implements SearchPricePort {

	/** The load price port. */
	private final LoadPricePort loadPricePort;

	/**
	 * Instantiates a new load price service.
	 *
	 * @param loadPricePort the load price port
	 */
	public LoadPriceService(LoadPricePort loadPricePort) {
		this.loadPricePort = loadPricePort;
	}

	/**
	 * Search.
	 *
	 * @param params the params
	 * @return the price
	 */
	@Transactional
	@Override
	public Price search(InParams params) {
		return loadPricePort.load(params.getDate(), params.getProductId(), params.getBrandId());
	}
}
