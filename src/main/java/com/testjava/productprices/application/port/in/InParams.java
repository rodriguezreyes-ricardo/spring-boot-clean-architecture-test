package com.testjava.productprices.application.port.in;

import java.sql.Timestamp;


/**
 * The Class InParams.
 */
public class InParams {

	/**
	 * Instantiates a new in params.
	 *
	 * @param brandId   the brand id
	 * @param date      the date
	 * @param productId the product id
	 */
	public InParams(Long brandId, Timestamp date, Long productId) {
		super();
		this.brandId = brandId;
		this.date = date;
		this.productId = productId;
	}

	/** The brand id. */
	private Long brandId;

	/** The date. */
	private Timestamp date;

	/** The product id. */
	private Long productId;

	/**
	 * Gets the brand id.
	 *
	 * @return the brand id
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * Sets the brand id.
	 *
	 * @param brandId the new brand id
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
