package com.testjava.productprices.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The Class Price.
 */
public class Price {

	/** The Constant INTERNAL_SERVER_ERROR. */
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";

	/** The Constant INCORRECT_DATE_FORMAT. */
	public static final String INCORRECT_DATE_FORMAT = "Should pass a correct date format";

	/** The Constant INCORRECT_NUMBER_FORMAT. */
	public static final String INCORRECT_NUMBER_FORMAT = "Should pass a correct number format";

	/** The id. */
	private Long id;

	/** The brand id. */
	private Long brandId;

	/** The start date. */
	private Timestamp startDate;

	/** The end date. */
	private Timestamp endDate;

	/** The price list. */
	private int priceList;

	/** The product id. */
	private Long productId;

	/** The priority. */
	private int priority;

	/** The price. */
	private BigDecimal price;

	/** The curr. */
	private String curr;

	/**
	 * Instantiates a new price.
	 *
	 * @param brandId   the brand id
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @param priceList the price list
	 * @param productId the product id
	 * @param priority  the priority
	 * @param price     the price
	 * @param curr      the curr
	 */
	public Price(Long brandId, Timestamp startDate, Timestamp endDate, int priceList, Long productId, int priority,
			BigDecimal price, String curr) {
		super();
		this.brandId = brandId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priceList = priceList;
		this.productId = productId;
		this.priority = priority;
		this.price = price;
		this.curr = curr;
	}

	/**
	 * Instantiates a new price entity.
	 */
	public Price() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

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
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the price list.
	 *
	 * @return the price list
	 */
	public int getPriceList() {
		return priceList;
	}

	/**
	 * Sets the price list.
	 *
	 * @param priceList the new price list
	 */
	public void setPriceList(int priceList) {
		this.priceList = priceList;
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

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the curr.
	 *
	 * @return the curr
	 */
	public String getCurr() {
		return curr;
	}

	/**
	 * Sets the curr.
	 *
	 * @param curr the new curr
	 */
	public void setCurr(String curr) {
		this.curr = curr;
	}

}
