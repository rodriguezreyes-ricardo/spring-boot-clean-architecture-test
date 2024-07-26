package com.testjava.productprices.adapter.in.web;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.testjava.productprices.application.port.in.InParams;
import com.testjava.productprices.application.port.in.SearchPricePort;
import com.testjava.productprices.common.WebAdapter;
import com.testjava.productprices.domain.Price;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class ProductPricesController.
 */
@WebAdapter
@RestController
@Api(description = "Api Rest", produces = "application/json", tags = { "Product Prices" })
public class ProductPricesController {

	/** The search price port. */
	private final SearchPricePort searchPricePort;

	/**
	 * Instantiates a new product prices controller.
	 *
	 * @param searchPricePort the search price port
	 */
	public ProductPricesController(SearchPricePort searchPricePort) {
		this.searchPricePort = searchPricePort;
	}


	/**
	 * Search.
	 *
	 * @param date the date
	 * @param productId the product id
	 * @param brandId the brand id
	 * @return the response entity
	 */
	@ApiOperation("Rate appied to the final price depending on the date.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Price.class),
			@ApiResponse(code = 00, message = "Internal server error") })
	@GetMapping(path = "/price/{date}/{productId}/{brandId}")
	@Nullable
	public ResponseEntity<Price> search(@PathVariable("date") Timestamp date,
			@PathVariable("productId") Long productId, @PathVariable("brandId") Long brandId) {
		Price price = null;
		InParams params = new InParams(brandId, date, productId);
		try {
			price = searchPricePort.search(params);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(price.getId()!=null?price:null, HttpStatus.OK);
	}

}
