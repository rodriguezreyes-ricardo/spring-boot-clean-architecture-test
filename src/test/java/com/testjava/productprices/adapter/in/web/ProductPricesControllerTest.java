package com.testjava.productprices.adapter.in.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.testjava.productprices.Dataset;
import com.testjava.productprices.adapter.out.persistence.SpringPriceRepository;

/**
 * The Class ProductPricesControllerTest.
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Sql("/test-h2.sql")
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
public class ProductPricesControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The objectmapper. */
	@Autowired
	ObjectMapper objectmapper;

	/** The spring price repository. */
	@Autowired
	SpringPriceRepository springPriceRepository;

	/**
	 * The Class TestConfigurationApp.
	 */
	@TestConfiguration
	static class TestConfigurationApp {

		/**
		 * Object mapper pretty printing.
		 *
		 * @return the object mapper
		 */
		@Bean
		ObjectMapper objectMapperPrettyPrinting() {
			return JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).addModule(new JavaTimeModule())
					.build();
		}
	}

	/**
	 * Test product prices controller.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testProductPricesController() throws Exception {
		// Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1
		// (ZARA)
		String date = Dataset.DATE_1;
		String productId = Dataset.PRODUCTID_1;
		String brandId = Dataset.BRANDID_1;
		String numTest = "1";

		testDataset(date, productId, brandId, numTest);

		// Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1
		// (ZARA)
		date = Dataset.DATE_2;
		productId = Dataset.PRODUCTID_2;
		brandId = Dataset.BRANDID_2;
		numTest = "2";
		testDataset(date, productId, brandId, numTest);

		// Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1
		// (ZARA)
		date = Dataset.DATE_3;
		productId = Dataset.PRODUCTID_3;
		brandId = Dataset.BRANDID_3;
		numTest = "3";
		testDataset(date, productId, brandId, numTest);

		// Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1
		// (ZARA)
		date = Dataset.DATE_4;
		productId = Dataset.PRODUCTID_4;
		brandId = Dataset.BRANDID_4;
		numTest = "4";
		testDataset(date, productId, brandId, numTest);

		// Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1
		// (ZARA)
		date = Dataset.DATE_5;
		productId = Dataset.PRODUCTID_5;
		brandId = Dataset.BRANDID_5;
		numTest = "5";
		testDataset(date, productId, brandId, numTest);

	}

	void testDataset(String date, String productId, String brandId, String numTest) throws Exception {
		Resource resource = new ClassPathResource("/responses/response".concat(numTest).concat(".json"));
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readValue(resource.getFile(), JsonNode.class);

		mockMvc.perform(get("/price/{date}/{productId}/{brandId}", date, productId, brandId))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				// .andExpect(MockMvcResultMatchers.jsonPath("$.brandId",
				// is(jsonNode.get("brandId").asLong())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate",
						is(Timestamp.valueOf(jsonNode.get("startDate").asText()).getTime())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate",
						is(Timestamp.valueOf(jsonNode.get("endDate").asText()).getTime())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList", is(jsonNode.get("priceList").asInt())))
				// .andExpect(MockMvcResultMatchers.jsonPath("$.productId",
				// is(jsonNode.get("productId").asLong())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priority", is(jsonNode.get("priority").asInt())))
				// .andExpect(MockMvcResultMatchers.jsonPath("$.price",
				// is(BigDecimal.valueOf(jsonNode.get("price").asDouble()))))
				.andExpect(MockMvcResultMatchers.jsonPath("$.curr", is(jsonNode.get("curr").asText()))).andReturn()
				.getResponse().getContentAsString();

	}
}
