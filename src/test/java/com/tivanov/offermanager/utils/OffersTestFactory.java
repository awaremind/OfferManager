package com.tivanov.offermanager.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.tivanov.offermanager.domain.model.offer.Offer;

public class OffersTestFactory {
	
	public static Offer getSampleOffer1( ) {
		Offer offer1 = new Offer();
		offer1.setId(1);
		offer1.setCustomer("Ivan");
		offer1.setDescription("Black socks");
		offer1.setPrice(new BigDecimal(10.55));
		offer1.setValidityDays(10);
		offer1.setCreatedAt(LocalDate.now().minusDays(7));
		return offer1;
	}
	public static Offer getSampleOffer2( ) {
		Offer offer1 = new Offer();
		offer1.setId(1);
		offer1.setCustomer("Peter");
		offer1.setDescription("White socks");
		offer1.setPrice(new BigDecimal(12.55));
		offer1.setValidityDays(5);
		offer1.setCreatedAt(LocalDate.now().minusDays(3));
		return offer1;
	}
	public static Offer getSampleOffer3( ) {
		Offer offer1 = new Offer();
		offer1.setId(1);
		offer1.setCustomer("John");
		offer1.setDescription("Black socks");
		offer1.setPrice(new BigDecimal(25.55));
		offer1.setValidityDays(3);
		offer1.setCreatedAt(LocalDate.now().minusDays(4));
		return offer1;
	}

}
