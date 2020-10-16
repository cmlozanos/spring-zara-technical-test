package com.sngular.springzaratechnicaltest.web.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sngular.springzaratechnicaltest.domain.Price;
import com.sngular.springzaratechnicaltest.services.PriceService;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

	private final PriceService service;

	public PriceController(final PriceService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Price> findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
			@RequestParam("productId") final Integer productId, @RequestParam("brandId") final Integer brandId,
			@RequestParam("aplicationDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime aplicationDateTime) {
		return ResponseEntity.ok(this.service
				.findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
						productId, brandId, aplicationDateTime));
	}
}
