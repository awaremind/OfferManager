package com.tivanov.offermanager.domain.model.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.tivanov.offermanager.domain.model.enumerated.OfferStatus;
import com.tivanov.offermanager.utils.OffersTestFactory;

public class OfferTest {
	
	@Test
	public void testHashcode_shouldReturnSameHashCodeForSameOffers() {
		Offer expected = new Offer(); 
		Offer actual = new Offer();
		expected = OffersTestFactory.getSampleOffer1();
		actual = OffersTestFactory.getSampleOffer1();
		assertEquals(expected.hashCode(), actual.hashCode());
	}
	
	@Test
	public void testHashcode_shouldReturnDiffHashCodeForSameOffers() {
		Offer expected = new Offer(); 
		Offer actual = new Offer();
		expected = OffersTestFactory.getSampleOffer1();
		actual = OffersTestFactory.getSampleOffer2();
		assertNotEquals(expected.hashCode(), actual.hashCode());
	}
	
	@Test
	public void testEquals_shouldReturnTrueForSameOffers() {
		Offer actualOffer = new Offer(); 
		Offer givenOffer = new Offer();
		actualOffer = OffersTestFactory.getSampleOffer1();
		givenOffer = OffersTestFactory.getSampleOffer1();
		assertTrue(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnTrueForOffersWithSameProp() {
		Offer actualOffer = new Offer(); 
		actualOffer.setId(1);
		actualOffer.setCustomer("Ivan");
		actualOffer.setDescription("Black socks");
		actualOffer.setPrice(new BigDecimal(10.55));
		actualOffer.setValidityDays(10);
		actualOffer.setCreatedAt(LocalDate.now().minusDays(3));
		actualOffer.setStatus(OfferStatus.VALID);;
		
		Offer givenOffer = new Offer();
		givenOffer.setId(actualOffer.getId());
		givenOffer.setCustomer(actualOffer.getCustomer());
		givenOffer.setDescription(actualOffer.getDescription());
		givenOffer.setPrice(actualOffer.getPrice());
		givenOffer.setValidityDays(actualOffer.getValidityDays());
		givenOffer.setCreatedAt(actualOffer.getCreatedAt());
		givenOffer.setStatus(actualOffer.getStatus());
		
		assertTrue(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnFalseForOffersWithDiffPropeprties() {
		Offer actualOffer = new Offer(); 
		actualOffer.setId(1);
		actualOffer.setCustomer("Ivan");
		actualOffer.setDescription("Black socks");
		actualOffer.setPrice(new BigDecimal(10.55));
		actualOffer.setValidityDays(10);
		actualOffer.setCreatedAt(LocalDate.now());
		actualOffer.setStatus(OfferStatus.VALID);;
		
		Offer givenOffer = new Offer("Peter", "Red socks", new BigDecimal(13.55), LocalDate.now(), 11);
		givenOffer.setId(actualOffer.getId());
		
		assertFalse(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnFalseForOffersWithSamePropeprties() {
		Offer givenOffer = new Offer("Peter", "Red socks", new BigDecimal(13.55), LocalDate.now(), 11);
		givenOffer.setId(1);
		
		Offer actualOffer = OffersTestFactory.getSampleOffer3();
		
		assertFalse(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnTrueForRefferencedOffers() {
		Offer actualOffer = OffersTestFactory.getSampleOffer1(); 
		Offer givenOffer = actualOffer;
		assertTrue(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnTrueForInstanciatedOffers() {
		Offer givenOffer = OffersTestFactory.getSampleOffer1(); 
		Offer actualOffer = givenOffer;
		assertTrue(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnFalseForGivenNullOffer() {
		Offer actualOffer = OffersTestFactory.getSampleOffer1();
		Offer givenOffer = null;
		assertFalse(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnFalseForDifferentOffers() {
		Offer actualOffer = OffersTestFactory.getSampleOffer1();
		Offer givenOffer = OffersTestFactory.getSampleOffer3();
		assertFalse(actualOffer.equals(givenOffer));
	}
	
	@Test
	public void testEquals_shouldReturnFalseForOffersWithDiffProp() {
		Offer actualOffer = new Offer(); 
		actualOffer.setId(1);
		actualOffer.setCustomer("Ivan");
		actualOffer.setDescription("Black socks");
		actualOffer.setPrice(new BigDecimal(10.55));
		actualOffer.setValidityDays(10);
		actualOffer.setCreatedAt(LocalDate.now().minusDays(3));
		
		Offer givenOffer = new Offer();
		givenOffer.setId(2);
		givenOffer.setCustomer("Peter");
		givenOffer.setDescription("White socks");
		givenOffer.setPrice(new BigDecimal(12.35));
		givenOffer.setValidityDays(5);
		givenOffer.setCreatedAt(LocalDate.now().minusDays(3));
		
		assertFalse(actualOffer.equals(givenOffer));
	}

}
