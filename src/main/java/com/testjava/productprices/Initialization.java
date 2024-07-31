package com.testjava.productprices;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
	
	@Value("classpath:sample-data.csv")
	private Resource resource;

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
		List<String> lines = IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8);

		for (String fields : lines) {
			List<String> field = Arrays.asList(fields.split(","));
			prices.add(new PriceEntity(Long.valueOf(field.get(0)), Timestamp.valueOf(field.get(7)),
					Timestamp.valueOf(field.get(2)), Integer.valueOf(field.get(4)),
					Long.valueOf(field.get(6)), Integer.valueOf(field.get(5)),
					BigDecimal.valueOf(Float.valueOf(field.get(3))), field.get(1)));
		}

		this.init.saveAll(prices);

	}
}
