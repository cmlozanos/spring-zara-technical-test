package com.sngular.springzaratechnicaltest.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.sngular.springzaratechnicaltest.domain.Price;
import com.sngular.springzaratechnicaltest.repository.PriceRepository;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository repository;

	@Override
	public Price findPriceByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDateAndPriority(
			final Integer productId, final Integer brandId, final LocalDateTime aplicationDateTime) {

		final List<Price> entities = this.repository
				.findAllByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDate(productId,
						brandId, aplicationDateTime);

		if (CollectionUtils.isEmpty(entities)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return entities.stream().max(Comparator.comparing(Price::getPriority)).orElseThrow(NoSuchElementException::new);
	}

}
