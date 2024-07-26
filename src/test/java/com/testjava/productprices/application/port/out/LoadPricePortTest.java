package com.testjava.productprices.application.port.out;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testjava.productprices.Dataset;
import com.testjava.productprices.domain.Price;

/**
 * The Class LoadPricePortTest.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Sql("/test-h2.sql")
class LoadPricePortTest {

	/** The load price port. */
	@Autowired
	LoadPricePort loadPricePort;

	/**
	 * Test load.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testLoad() throws Exception {
		Price price = loadPricePort.load(Timestamp.valueOf(Dataset.DATE_1), Long.valueOf(Dataset.PRODUCTID_1),
				Long.valueOf(Dataset.BRANDID_1));

		Resource resource = new ClassPathResource("/responses/response1.json");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readValue(resource.getFile(), JsonNode.class);

		String json = mapper.writeValueAsString(price);
		JsonNode jsonNode2 = mapper.readTree(json);

		assertEquals(jsonNode.get("brandId"), jsonNode2.get("brandId"));
		assertEquals(Timestamp.valueOf(jsonNode.get("startDate").asText()).getTime(),
				jsonNode2.get("startDate").asLong());
		assertEquals(Timestamp.valueOf(jsonNode.get("endDate").asText()).getTime(), jsonNode2.get("endDate").asLong());
		assertEquals(jsonNode.get("priceList"), jsonNode2.get("priceList"));
		assertEquals(jsonNode.get("priority"), jsonNode2.get("priority"));
		assertEquals(jsonNode.get("curr"), jsonNode2.get("curr"));
	}

}
