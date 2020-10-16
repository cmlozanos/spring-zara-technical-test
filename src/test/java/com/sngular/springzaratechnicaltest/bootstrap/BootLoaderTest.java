package com.sngular.springzaratechnicaltest.bootstrap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sngular.springzaratechnicaltest.repository.PriceRepository;

@SpringBootTest
/**
 * To this tests we assume spring boot context loaded with bootloader launched
 * before
 *
 * @author Carlos Lozano
 *
 */
class BootLoaderTest {

	@Autowired
	public BootLoader loader;

	@Autowired
	public PriceRepository repository;

	@Test
	void givenDatabaseLoaderWhenStartedRepositoryShouldHave4Items() {
		// given
		// when
		// then
		Assertions.assertThat(this.repository.count()).isEqualTo(4L);
	}

	@Test
	void givenDatabaseLoadedWhenStartedRepositoryShouldHave4Items() {
		// given

		// when
		this.loader.loadObjects(this.repository);

		// then
		Assertions.assertThat(this.repository.count()).isEqualTo(4L);
	}
}
