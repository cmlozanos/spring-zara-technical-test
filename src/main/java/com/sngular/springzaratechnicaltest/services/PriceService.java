package com.sngular.springzaratechnicaltest.services;

import java.time.LocalDateTime;

import com.sngular.springzaratechnicaltest.domain.Price;

public interface PriceService {

	Price findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
			Integer productId, Integer brandId, LocalDateTime aplicationDateTime);
}
