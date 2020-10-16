package com.sngular.springzaratechnicaltest.bootstrap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sngular.springzaratechnicaltest.domain.Price;
import com.sngular.springzaratechnicaltest.repository.PriceRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BootLoader implements CommandLineRunner {

	@Autowired
	private PriceRepository repository;

	@Override
	public void run(final String... args) throws Exception {
		this.loadObjects(this.repository);
	}

	/**
	 * Protected only for tests purposes
	 */
	protected void loadObjects(final PriceRepository repository) {
		if (repository.count() == 0) {
			final Price entity1 = Price.builder().brandId(1)
					.startDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(00, 00, 00)))
					.endDateTime(LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59)))
					.priceList(1).productId(35455).priority(0).amount(35.5D).currency("EUR").build();

			repository.save(entity1);

			final Price entity2 = Price.builder().brandId(1)
					.startDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(15, 00, 00)))
					.endDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 14), LocalTime.of(18, 30, 00)))
					.priceList(2).productId(35455).priority(1).amount(25.45D).currency("EUR").build();

			repository.save(entity2);

			final Price entity3 = Price.builder().brandId(1)
					.startDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(00, 00, 00)))
					.endDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(11, 00, 00)))
					.priceList(3).productId(35455).priority(1).amount(30.5D).currency("EUR").build();
			repository.save(entity3);

			final Price entity4 = Price.builder().brandId(1)
					.startDateTime(LocalDateTime.of(LocalDate.of(2020, Month.JUNE, 15), LocalTime.of(16, 00, 00)))
					.endDateTime(LocalDateTime.of(LocalDate.of(2020, Month.DECEMBER, 31), LocalTime.of(23, 59, 59)))
					.priceList(4).productId(35455).priority(1).amount(38.95D).currency("EUR").build();
			repository.save(entity4);
		}

		BootLoader.log.debug("Loaded Prices: " + repository.count());
	}

}
