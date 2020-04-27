package com.tivanov.offermanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tivanov.offermanager.domain.model.exception.CustomerNotFoundException;
import com.tivanov.offermanager.domain.model.exception.OfferNotFoundException;
import com.tivanov.offermanager.domain.model.offer.Offer;
import com.tivanov.offermanager.domain.service.OfferingService;
import com.tivanov.offermanager.utils.OffersTestFactory;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class OfferControllerTest {
	
	Offer expectedOffer;

	@MockBean
	private OfferingService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
    void testGetAll_shouldGetValidOffers() throws Exception {
        mockMvc.perform(get("/offers/all"))
        	.andExpect(status().isOk());
    }
	
	@Test
	void testGetOfferById_shouldReturnOffer() throws Exception {
		mockMvc.perform(get("/offer/id/1"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testGetMissingOfferById_shouldThrowException() throws Exception {
		Mockito.when(service.findById(1)).thenThrow(OfferNotFoundException.class);
		mockMvc.perform(delete("/offer/id/1"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void testDeleteOfferById_shouldDeleteOffer() throws Exception {
		mockMvc.perform(delete("/offer/cancel/1"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testGetMissingCustomerOfferById_shouldThrowException() throws Exception {
		Mockito.when(service.findOfferByCustomer("nobody")).thenThrow(CustomerNotFoundException.class);
		mockMvc.perform(delete("/offer/nobody"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void testGetOfferByCustomer_shouldReturnOffersForCustomer() throws Exception {
		mockMvc.perform(get("/offer/ivan"))
			.andExpect(status().isOk());
	}
	
	@Test
	void testCreateOfferPost_shouldCreateNewOffer() throws Exception {
		Offer expectedOffer = OffersTestFactory.getSampleOffer1();
		String expectedOfferJson = mapper.writeValueAsString(expectedOffer);
		mockMvc.perform(post("/offer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(expectedOfferJson)
						.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated()
			);
	}
	
	@Test
	void testCreateOfferPostMalformedJson_shouldReturn412() throws Exception {
		String expectedOfferJson = "{blabla\"\"foo}";
		mockMvc.perform(post("/offer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(expectedOfferJson)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isPreconditionFailed()
				);
	}

}
