package com.sngular.springzaratechnicaltest.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class Price {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36, columnDefinition = "varchar", updatable= false, nullable=false)
	@JsonIgnore
	private UUID id;

	private Integer brandId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer priceList;
    private Integer productId;
    
    @JsonIgnore
    private Integer priority;
    private Double amount;
    private String currency;
}
