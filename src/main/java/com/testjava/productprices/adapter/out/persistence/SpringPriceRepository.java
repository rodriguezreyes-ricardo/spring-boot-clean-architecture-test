package com.testjava.productprices.adapter.out.persistence;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The Interface SpringPriceRepository.
 */
public interface SpringPriceRepository extends JpaRepository<PriceEntity, Long> {

	/**
	 * Find by date and id and brand id.
	 *
	 * @param date the date
	 * @param productId the product id
	 * @param brandId the brand id
	 * @return the price entity
	 */
	@Query(value="SELECT *  FROM prices u WHERE  ?1 BETWEEN u.start_date AND u.end_date AND product_id = ?2 AND brand_id = ?3 ORDER BY priority desc limit 1", nativeQuery = true)
	PriceEntity findByDateAndIdAndBrandId(Timestamp date, Long productId, Long brandId);
}
