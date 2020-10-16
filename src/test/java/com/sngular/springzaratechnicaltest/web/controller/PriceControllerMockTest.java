package com.sngular.springzaratechnicaltest.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sngular.springzaratechnicaltest.domain.Price;
import com.sngular.springzaratechnicaltest.services.PriceService;

@WebMvcTest(PriceController.class)
class PriceControllerMockTest {

	@MockBean
	PriceService service;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	Price validEntity;
	String validEntityJson;

	@BeforeEach
	public void setup() throws JsonProcessingException {
		this.validEntity = Price.builder().brandId(1)
				.startDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(00, 00, 00)))
				.endDateTime(LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59)))
				.priceList(1).productId(35455).priority(0).amount(35.5D).currency("EUR").build();
		this.validEntityJson = this.mapper.writeValueAsString(this.validEntity);
	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesAndPriorityWhenGetResourcesThenShouldReturnAPrice()
			throws Exception {
		// given
		final Integer productId = 1;
		final Integer brandId = 1;
		final LocalDateTime aplicationDateTime = LocalDateTime.of(LocalDate.of(2020, Month.OCTOBER, 15),
				LocalTime.of(00, 00, 00));
		final String aplicationDateTimeString = aplicationDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		BDDMockito.given(this.service
				.findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
						productId, brandId, aplicationDateTime))
				.willReturn(this.validEntity);
		final String url = "/api/v1/prices";

		// when
		final ResultActions perform = this.mockMvc.perform(MockMvcRequestBuilders.get(url).param("productId", "1")
				.param("brandId", "1").param("aplicationDateTime", aplicationDateTimeString));

		// then
		perform.andExpect(MockMvcResultMatchers.status().isOk());
		perform.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.brandId", CoreMatchers.is(this.validEntity.getBrandId())));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.startDateTime",
				CoreMatchers.is(this.validEntity.getStartDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.endDateTime",
				CoreMatchers.is(this.validEntity.getEndDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))));
		perform.andExpect(
				MockMvcResultMatchers.jsonPath("$.priceList", CoreMatchers.is(this.validEntity.getPriceList())));
		perform.andExpect(
				MockMvcResultMatchers.jsonPath("$.productId", CoreMatchers.is(this.validEntity.getProductId())));
		perform.andExpect(MockMvcResultMatchers.jsonPath("$.amount", CoreMatchers.is(this.validEntity.getAmount())));
		perform.andExpect(
				MockMvcResultMatchers.jsonPath("$.currency", CoreMatchers.is(this.validEntity.getCurrency())));
		perform.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void givenGetRequestToFindPriceByProductIdAndBrandIdAndDatesInvalidWhenGetResourcesThenShouldReturnNotFound()
			throws Exception {
		// given
		final Integer productId = 1;
		final Integer brandId = 1;
		final LocalDateTime aplicationDateTime = LocalDateTime.of(LocalDate.of(2020, Month.OCTOBER, 15),
				LocalTime.of(00, 00, 00));
		final String aplicationDateTimeString = aplicationDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		BDDMockito.given(this.service
				.findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
						productId, brandId, aplicationDateTime))
				.willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
		final String url = "/api/v1/prices";

		// when
		final ResultActions perform = this.mockMvc.perform(MockMvcRequestBuilders.get(url).param("productId", "1")
				.param("brandId", "1").param("aplicationDateTime", aplicationDateTimeString));

		// then
		perform.andExpect(MockMvcResultMatchers.status().isNotFound());
		perform.andDo(MockMvcResultHandlers.print());
	}
}
