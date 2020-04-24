package com.tivanov.offermanager.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tivanov.offermanager.domain.model.offer.Offer;


@Service
public class OfferServiceRestFacade {
	
	@Autowired
	private OfferingService offerService;
	
	@Autowired
	private ObjectMapper mapper;
	
	public Offer saveOffer(String offerString) throws JsonMappingException, JsonProcessingException {
		Offer offer = mapper.readValue(offerString, Offer.class);
		return offerService.saveOffer(offer);
	}
	
	public void deleteOffer(long offerId) {
		offerService.deleteOffer(offerId);
	}
	
	public List<Offer> findOfferByCustomer(String customer) {
		return offerService.findOfferByCustomer(customer);
	}
	
	public List<Offer> findAllOffers() {
		return offerService.findAllOffers();
	}
	
	public Offer findOfferById(long id) {
		return offerService.findById(id);
	}
	
	
	
//	public Offer findLatestBySymbol(String symbol) {
//		Offer offer;
//		Optional<Offer> dbPrice = offerService.;
//		if (dbPrice.isPresent()) {
//			 offer = dbPrice.get();
//		}
//		return offer;
//	}
	 
//	public List<PricesDTO> priceListPerInterval(String symbol, Optional<Integer> hours) {
//		List<PricesDTO> pricesDTO = new ArrayList<PricesDTO>();
//		pricesDTO = priceService.findPriceBySymbolPerInterval(symbol, hours);
//		return pricesDTO;
//	}

}
