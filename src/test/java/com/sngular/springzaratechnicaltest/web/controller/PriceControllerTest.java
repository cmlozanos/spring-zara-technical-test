package com.sngular.springzaratechnicaltest.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

	@Autowired
	PriceController controller;

	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@BeforeEach
	public void setup() {
		this.mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		this.mappingJackson2HttpMessageConverter.setObjectMapper(new ObjectMapper()
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new JavaTimeModule()));
	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase1WhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(10, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
				.body("brandId", CoreMatchers.equalTo(1))
				.body("startDateTime",
						CoreMatchers.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("endDateTime",
						CoreMatchers.equalTo(
								LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59))
										.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("priceList", CoreMatchers.equalTo(1)).body("productId", CoreMatchers.equalTo(35455))
				.body("amount", CoreMatchers.equalTo(35.5f)).body("currency", CoreMatchers.equalTo("EUR"));

	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase2WhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(16, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
				.body("brandId", CoreMatchers.equalTo(1))
				.body("startDateTime",
						CoreMatchers.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(15, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("endDateTime",
						CoreMatchers
								.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(18, 30, 00))
										.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("priceList", CoreMatchers.equalTo(2)).body("productId", CoreMatchers.equalTo(35455))
				.body("amount", CoreMatchers.equalTo(25.45f)).body("currency", CoreMatchers.equalTo("EUR"));

	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase3WhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(21, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
				.body("brandId", CoreMatchers.equalTo(1))
				.body("startDateTime",
						CoreMatchers.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("endDateTime",
						CoreMatchers.equalTo(
								LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59))
										.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("priceList", CoreMatchers.equalTo(1)).body("productId", CoreMatchers.equalTo(35455))
				.body("amount", CoreMatchers.equalTo(35.5f)).body("currency", CoreMatchers.equalTo("EUR"));

	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase4WhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(10, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
				.body("brandId", CoreMatchers.equalTo(1))
				.body("startDateTime",
						CoreMatchers.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("endDateTime",
						CoreMatchers
								.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(11, 00, 00))
										.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("priceList", CoreMatchers.equalTo(3)).body("productId", CoreMatchers.equalTo(35455))
				.body("amount", CoreMatchers.equalTo(30.5f)).body("currency", CoreMatchers.equalTo("EUR"));

	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase5WhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 16), LocalTime.of(21, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
				.body("brandId", CoreMatchers.equalTo(1))
				.body("startDateTime",
						CoreMatchers.equalTo(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(16, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("endDateTime",
						CoreMatchers.equalTo(
								LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59))
										.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.body("priceList", CoreMatchers.equalTo(4)).body("productId", CoreMatchers.equalTo(35455))
				.body("amount", CoreMatchers.equalTo(38.95f)).body("currency", CoreMatchers.equalTo("EUR"));

	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityCase6WhenGetResourcesThenShouldReturnNotFound()
			throws Exception {
		// given
		RestAssuredMockMvc.given()
				.standaloneSetup(MockMvcBuilders.standaloneSetup(this.controller)
						.setMessageConverters(this.mappingJackson2HttpMessageConverter))
				.param("productId", 35455).param("brandId", 1)
				.param("aplicationDateTime",
						LocalDateTime.of(LocalDate.of(2020, Month.JANUARY, 01), LocalTime.of(00, 00, 00))
								.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))

				// when
				.when().get("/api/v1/prices")

				// then
				.then().log().all().statusCode(HttpStatus.NOT_FOUND.value());
	}

}
