package com.testjava.productprices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/** The properties. */
	private SwaggerProperties properties;

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public SwaggerProperties getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the new properties
	 */
	public void setProperties(SwaggerProperties properties) {
		this.properties = properties;
	}

	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.testjava.productprices.adapter.in.web"))
				.paths(Predicates.not(PathSelectors.regex("/error"))).build().pathMapping("/")
				.enableUrlTemplating(true);

	}

	/**
	 * Api info.
	 *
	 * @return the api info
	 */
	private ApiInfo apiInfo() {
		properties = new SwaggerProperties();
		properties.setTitle("ProductPrice Swagger Api");
		properties.setDescription("Swagger Api");
		properties.setVersion("1.1.0");
		properties.setTermsOfServiceUrl("termsOfServiceUrl");
		SwaggerProperties.License license = new SwaggerProperties.License();
		license.setName("name");
		license.setUrl("url");
		properties.setLicense(license);
		return new ApiInfoBuilder().title(properties.getTitle()).description(properties.getDescription())
				.version(properties.getVersion()).termsOfServiceUrl(properties.getTermsOfServiceUrl())
				.license(properties.getLicense().getName()).licenseUrl(properties.getLicense().getUrl()).build();

	}

}
