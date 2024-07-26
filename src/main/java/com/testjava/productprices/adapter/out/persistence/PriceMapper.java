package com.testjava.productprices.adapter.out.persistence;

import com.testjava.productprices.domain.Price;

/**
 * The Class PriceMapper.
 */
public class PriceMapper {

	/**
	 * Entity to domain.
	 *
	 * @param priceEntity the price entity
	 * @return the price
	 */
	public static Price entityToDomain(PriceEntity priceEntity) {
		Price price = new Price();
		price.setId(priceEntity.getId());
		price.setBrandId(priceEntity.getBrandId());
		price.setStartDate(priceEntity.getStartDate());
		price.setEndDate(priceEntity.getEndDate());
		price.setPriceList(priceEntity.getPriceList());
		price.setProductId(priceEntity.getProductId());
		price.setPriority(priceEntity.getPriority());
		price.setPrice(priceEntity.getPrice());
		price.setCurr(priceEntity.getCurr());

		return price;
	}

	/**
	 * Domain to entity.
	 *
	 * @param price the price
	 * @return the price entity
	 */
	public static PriceEntity domainToEntity(Price price) {
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setId(price.getId());
		priceEntity.setBrandId(price.getBrandId());
		priceEntity.setStartDate(price.getStartDate());
		priceEntity.setEndDate(price.getEndDate());
		priceEntity.setPriceList(price.getPriceList());
		priceEntity.setProductId(price.getProductId());
		priceEntity.setPriority(price.getPriority());
		priceEntity.setPrice(price.getPrice());
		priceEntity.setCurr(price.getCurr());

		return priceEntity;
	}
}
