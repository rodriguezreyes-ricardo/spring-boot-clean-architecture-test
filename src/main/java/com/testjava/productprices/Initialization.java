package com.testjava.productprices;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.testjava.productprices.adapter.out.persistence.PriceEntity;
import com.testjava.productprices.adapter.out.persistence.SpringPriceRepository;

/**
 * The Class Initialization.
 */
@Component
public class Initialization implements InitializingBean {

	/** The init. */
	private final SpringPriceRepository init;

	/**
	 * Instantiates a new initialization.
	 *
	 * @param init the init
	 */
	public Initialization(SpringPriceRepository init) {
		this.init = init;
	}

	/**
	 * After properties set.
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<PriceEntity> prices = new ArrayList<>();

		prices.add(new PriceEntity(1L, Timestamp.valueOf("2020-06-14 00:00:00"),
				Timestamp.valueOf("2020-12-31 23:59:59"), 1, 35455L, 0, BigDecimal.valueOf(35.50), "EUR"));
		prices.add(new PriceEntity(1L, Timestamp.valueOf("2020-06-14 15:00:00"),
				Timestamp.valueOf("2020-06-14 18:30:00"), 2, 35455L, 1, BigDecimal.valueOf(25.45), "EUR"));
		prices.add(new PriceEntity(1L, Timestamp.valueOf("2020-06-15 00:00:00"),
				Timestamp.valueOf("2020-06-15 11:00:00"), 3, 35455L, 1, BigDecimal.valueOf(30.50), "EUR"));
		prices.add(new PriceEntity(1L, Timestamp.valueOf("2020-06-15 16:00:00"),
				Timestamp.valueOf("2020-12-31 23:59:59"), 4, 35455L, 1, BigDecimal.valueOf(38.95), "EUR"));
		this.init.saveAll(prices);

	}
}
