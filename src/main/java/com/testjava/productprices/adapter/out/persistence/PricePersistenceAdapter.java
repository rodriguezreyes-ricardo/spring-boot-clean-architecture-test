package com.testjava.productprices.adapter.out.persistence;

import java.sql.Timestamp;

import com.testjava.productprices.application.port.out.LoadPricePort;
import com.testjava.productprices.common.PersistenceAdapter;
import com.testjava.productprices.domain.Price;

/**
 * The Class PricePersistenceAdapter.
 */
@PersistenceAdapter
public class PricePersistenceAdapter implements LoadPricePort {

	/** The price repository. */
	private final SpringPriceRepository priceRepository;

	/**
	 * Instantiates a new price persistence adapter.
	 *
	 * @param priceRepository the price repository
	 */
	public PricePersistenceAdapter(SpringPriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	/**
	 * Load.
	 *
	 * @param date      the date
	 * @param productId the product id
	 * @param brandId   the brand id
	 * @return the price
	 */
	@Override
	public Price load(Timestamp date, Long productId, Long brandId) {
		Price price = null;
		try {
			PriceEntity priceEntity = priceRepository.findByDateAndIdAndBrandId(date, productId, brandId);
			price = priceEntity != null?PriceMapper.entityToDomain(priceEntity):new Price();
		} catch (RuntimeException e) {
			throw new RuntimeException(Price.INTERNAL_SERVER_ERROR);
		}

		return price;
	}
}
