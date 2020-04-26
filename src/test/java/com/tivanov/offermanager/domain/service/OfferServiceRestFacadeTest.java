package com.tivanov.offermanager.domain.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tivanov.offermanager.domain.model.offer.Offer;
import com.tivanov.offermanager.utils.OffersTestFactory;

@SpringBootTest
class OfferServiceRestFacadeTest {

	private Offer expectedOffer, actualOffer;
	private String expectedOfferStr;
	
	@MockBean
	private OfferingService service;
	
	@Autowired
	private OfferServiceRestFacade serviceFacade;
	
	@Autowired
	private ObjectMapper mapper;
	
//	@BeforeAll
//	void setupAll() throws Exception {
//	}

	@BeforeEach
	void setUp() throws Exception {
		expectedOfferStr = mapper.writeValueAsString(OffersTestFactory.getSampleOffer1());
		expectedOffer = OffersTestFactory.getSampleOffer1();
	}
	
	@Test
	void testSaveOfferJsonString_shouldReturnOffer() throws JsonMappingException, JsonProcessingException {
		Mockito.when(service.saveOffer(expectedOffer)).thenReturn(expectedOffer);
		actualOffer = serviceFacade.saveOffer(expectedOfferStr);
		
		assertNotNull("The returned offer from save is null ", actualOffer);
		assertTrue(actualOffer.equals(actualOffer));
	}

	
	@Test
	void testDeleteOffer_shouldDeleteOffer() {
		serviceFacade.deleteOffer(expectedOffer.getId());
		Mockito.verify(service, atLeast(1)).deleteOffer(expectedOffer.getId());
	}
	
	@Test
	void testFindById_shouldReturnOfferByIdValid() {
		Mockito.when(service.findById(expectedOffer.getId())).thenReturn(expectedOffer);
		actualOffer = serviceFacade.findOfferById(expectedOffer.getId());
		
		assertNotNull(actualOffer);
		assertTrue(actualOffer.equals(expectedOffer));
	}
	
	@Test
	void testFindByCustomer_shouldReturnOffer() {
		List<Offer> expectedOfferList = Collections.singletonList(expectedOffer);
		Mockito.when(service.findOfferByCustomer(expectedOffer.getCustomer()))
			.thenReturn(expectedOfferList);
		List<Offer> actualOfferList = serviceFacade.findOfferByCustomer(expectedOffer.getCustomer());
		
		assertFalse(actualOfferList.isEmpty());
		assertTrue(actualOfferList.contains(expectedOffer));
	}
	
	@Test
	void testFindAllValid_shouldReturnOfferList() {
		Offer secondOffer = OffersTestFactory.getSampleOffer2();
		Offer thirdOffer = OffersTestFactory.getSampleOffer1();
		List<Offer> expectedOfferList = Arrays.asList(expectedOffer, secondOffer, thirdOffer);
		Mockito.when(service.findAllOffers())
			.thenReturn(expectedOfferList);
		List<Offer> actualOfferList = serviceFacade.findAllOffers();
		
		assertFalse(actualOfferList.isEmpty());
		assertTrue("The list does not contain secondOffer", actualOfferList.contains(secondOffer));
		assertTrue("The list does not contain expectedOffer", actualOfferList.contains(expectedOffer));
	}
}
