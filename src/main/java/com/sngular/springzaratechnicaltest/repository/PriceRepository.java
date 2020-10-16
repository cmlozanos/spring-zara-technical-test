package com.sngular.springzaratechnicaltest.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sngular.springzaratechnicaltest.domain.Price;

public interface PriceRepository extends JpaRepository<Price, UUID> {

	@Query("select p from Price p where p.productId = :productId and p.brandId = :brandId and p.startDateTime <= :aplicationDateTime and p.endDateTime >= :aplicationDateTime")
	public List<Price> findAllByProductIdAndBrandIdAndGreaterOrEqualThanStartDateAndLowerOrEqualThanEndDate(
			@Param("productId") Integer productId, @Param("brandId") Integer brandId,
			@Param("aplicationDateTime") LocalDateTime aplicationDateTime);

}
