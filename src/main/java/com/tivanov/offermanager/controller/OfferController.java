package com.tivanov.offermanager.controller;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tivanov.offermanager.domain.model.offer.Offer;
import com.tivanov.offermanager.domain.service.OfferServiceRestFacade;

@RestController
public class OfferController {
	
	@Autowired
	private OfferServiceRestFacade offeringServiceFacade;

	@GetMapping
	@RequestMapping("/offers/all")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getAllOffers() {
		List<Offer> offersList = offeringServiceFacade.findAllOffers();
		return new ResponseEntity<>(offersList, HttpStatus.OK);
	}
	
	@DeleteMapping
	@RequestMapping("/offer/cancel/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> cancellOfferById(@PathVariable(name="id") long id) {
		offeringServiceFacade.deleteOffer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping("/offer/id/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getOfferById(@PathVariable(name="id") long id) {
		Offer offer = offeringServiceFacade.findOfferById(id);
		return new ResponseEntity<>(offer, HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping("/offer/{customer}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> getOfferByCustomer(@PathVariable(name="customer") @NotNull String customer) {
		List<Offer> offersList = offeringServiceFacade.findOfferByCustomer(customer);
		return new ResponseEntity<>(offersList, HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping("/offer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> sendMessage(@RequestBody @NotNull String offerString) 
			throws JsonMappingException, JsonProcessingException {
		Offer offer = offeringServiceFacade.saveOffer(offerString);
		return new ResponseEntity<>(offer, HttpStatus.CREATED);
	}
	
}
