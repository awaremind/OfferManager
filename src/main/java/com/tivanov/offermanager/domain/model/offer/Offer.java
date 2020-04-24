package com.tivanov.offermanager.domain.model.offer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.tivanov.offermanager.domain.model.enumerated.OfferStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "offer")
@NoArgsConstructor
public class Offer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3598569377908511754L;

	public Offer(String customer, String description, BigDecimal price, LocalDate createdAt, int validityDays) {
		super();
		this.customer = customer;
		this.description = description;
		this.price = price;
		this.validityDays = validityDays;
		this.createdAt = LocalDate.now();
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private long id;

	@Column
	@Getter @Setter
	private String customer;
	
	@Column
	@Getter @Setter
	private String description;

	@Column
	@Getter @Setter
	private BigDecimal price;
	
	@Column(columnDefinition = "DATE")
	@Getter @Setter
	private LocalDate createdAt = LocalDate.now();
	
	@Column
	@Getter @Setter
	private int validityDays;
	
	@Column
	@Transient
	@Getter @Setter
	private OfferStatus status;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(customer)
				.append(description)
				.append(price)
				.append(createdAt)
				.append(validityDays)
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else { 
			Offer other = (Offer) obj;
			return new EqualsBuilder()
					.append(this.id, other.id)
					.append(this.customer, other.customer)
					.append(this.description, other.description)
					.append(this.price, other.price)
					.append(this.createdAt, other.createdAt)
					.append(this.validityDays, other.validityDays)
					.isEquals();
		}
	}

}
