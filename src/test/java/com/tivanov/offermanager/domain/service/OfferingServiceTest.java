package com.tivanov.offermanager.domain.service;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tivanov.offermanager.config.OfferingConfig;
import com.tivanov.offermanager.domain.model.enumerated.OfferStatus;
import com.tivanov.offermanager.domain.model.exception.CustomerNotFoundException;
import com.tivanov.offermanager.domain.model.exception.OfferNotFoundException;
import com.tivanov.offermanager.domain.model.offer.Offer;
import com.tivanov.offermanager.infrastructure.persistence.OfferJpaRepository;
import com.tivanov.offermanager.utils.OffersTestFactory;

@SpringBootTest
class OfferingServiceTest {
	
	private Offer expectedOffer, actualOffer;
	
	@MockBean
	private OfferJpaRepository repo;
	
	@Autowired
	private OfferingService offerService;
	
	@Autowired
	private OfferingConfig config;

	@BeforeEach
	void setUp() throws Exception {
		expectedOffer = OffersTestFactory.getSampleOffer1();
	}
	
	@Test
	void testSaveOfferWithPositiveValidity_shouldReturnOffer() {
		Mockito.when(repo.saveAndFlush(expectedOffer)).thenReturn(expectedOffer);
		actualOffer = offerService.saveOffer(expectedOffer);
		
		assertNotNull("The returned offer, with positive validity days, from save  is null ", actualOffer);
		assertThat("The offer is not returning the default value when given validity days is not positive",
				actualOffer.getValidityDays(), is(expectedOffer.getValidityDays()));
	}

	@Test
	void testSaveOfferWithNegativeValidity_shouldReturnOfferWithDefaultValidity() {
		expectedOffer.setValidityDays(-1);
		Mockito.when(repo.saveAndFlush(expectedOffer)).thenReturn(expectedOffer);
		actualOffer = offerService.saveOffer(expectedOffer);
		
		assertNotNull("The returned offer, with negative validity days, from save is null ", actualOffer);
		assertThat("The offer is not returning the default value when given validity days is not positive", 
				actualOffer.getValidityDays(), is(config.getDefaultValidityDays()));
	}
	
	
	@Test
	void testDeleteOffer_shouldDeleteOffer() {
		offerService.deleteOffer(expectedOffer.getId());
		Mockito.verify(repo, atLeast(1)).deleteById(expectedOffer.getId());
		Mockito.verify(repo, atLeast(1)).flush();
	}
	
	@Test
	void testFindById_shouldReturnOfferByIdValid() {
		Mockito.when(repo.findById(expectedOffer.getId())).thenReturn(expectedOffer);
		actualOffer = offerService.findById(expectedOffer.getId());
		
		assertNotNull(actualOffer);
		assertTrue(actualOffer.equals(expectedOffer));
		assertTrue(actualOffer.getStatus().equals(OfferStatus.VALID));
	}
	
	@Test
	void testFindById_shouldReturnOfferByIdExpired() {
		expectedOffer = OffersTestFactory.getSampleOffer3();
		Mockito.when(repo.findById(expectedOffer.getId())).thenReturn(expectedOffer);
		actualOffer = offerService.findById(expectedOffer.getId());
		
		assertNotNull(actualOffer);
		assertTrue(actualOffer.equals(expectedOffer));
		assertTrue(actualOffer.getStatus().equals(OfferStatus.EXPIRED));
	}
	
	@Test
	void testFindByInvalidId_shouldThrowException() {
		Mockito.when(repo.findById(expectedOffer.getId())).thenReturn(actualOffer);

		OfferNotFoundException ex = assertThrows(OfferNotFoundException.class, 
				() -> offerService.findById(expectedOffer.getId()));
		
		assertEquals(ex.getErrorCode(), "OFFER_NOT_FOUND");
	}
	
	@Test
	void testFindByCustomer_shouldReturnOffer() {
		Offer secondExpectedOffer = OffersTestFactory.getSampleOffer3();
		secondExpectedOffer.setCustomer(expectedOffer.getCustomer());
		List<Offer> expectedOfferList = Arrays.asList(expectedOffer, secondExpectedOffer);
		Mockito.when(repo.findByCustomer(expectedOffer.getCustomer()))
			.thenReturn(expectedOfferList);
		List<Offer> actualOfferList = offerService.findOfferByCustomer(expectedOffer.getCustomer());
		
		assertFalse(actualOfferList.isEmpty());
		assertTrue(actualOfferList.contains(expectedOffer));
		assertThat(actualOfferList, hasItem(anyOf(
		        hasProperty("status", is(OfferStatus.VALID)),
		        hasProperty("status", is(OfferStatus.EXPIRED))
		    )));
	}
	
	@Test
	void testFindByInvalidCustomer_shouldThrowException() {
		List<Offer> expectedOfferList = new ArrayList<>();
		Mockito.when(repo.findByCustomer(expectedOffer.getCustomer()))
			.thenReturn(expectedOfferList);
		CustomerNotFoundException ex = assertThrows(CustomerNotFoundException.class, () -> 
				offerService.findOfferByCustomer(expectedOffer.getCustomer()));
		
		assertEquals(ex.getErrorCode(), "CUSTOMER_NOT_FOUND");
	}
	
	@Test
	void testFindAllValid_shouldReturnOffer() {
		Offer secondOffer = OffersTestFactory.getSampleOffer2();
		Offer thirdOffer = OffersTestFactory.getSampleOffer1();
		List<Offer> expectedOfferList = Arrays.asList(expectedOffer, secondOffer, thirdOffer);
		Mockito.when(repo.findAllValid())
			.thenReturn(expectedOfferList);
		List<Offer> actualOfferList = offerService.findAllOffers();
		
		assertFalse(actualOfferList.isEmpty());
		actualOfferList.forEach(o -> 
			assertTrue(o.getStatus().equals(OfferStatus.VALID)));
	}
	
}
