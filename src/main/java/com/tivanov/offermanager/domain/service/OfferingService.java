package com.tivanov.offermanager.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tivanov.offermanager.config.OfferingConfig;
import com.tivanov.offermanager.domain.model.enumerated.OfferStatus;
import com.tivanov.offermanager.domain.model.exception.CustomerNotFoundException;
import com.tivanov.offermanager.domain.model.exception.OfferNotFoundException;
import com.tivanov.offermanager.domain.model.offer.Offer;
import com.tivanov.offermanager.infrastructure.persistence.OfferRepositorySpringDataFacade;

@Service
public class OfferingService {
	
	@Autowired
	private OfferingConfig config;
	
	@Autowired
	private OfferRepositorySpringDataFacade offerRepo;
	
	private boolean checkIfValid(Offer offer) {
		return offer.getCreatedAt().plusDays(offer.getValidityDays()).isAfter(LocalDate.now());
	}
	
	private List<Offer> setOffersStatus(List<Offer> offersList) {
		offersList.forEach(o -> {
			o.setStatus(checkIfValid(o) ? OfferStatus.VALID : OfferStatus.EXPIRED);
			});
		
		return offersList;
	}

	public Offer saveOffer(Offer offer) {
		if (offer.getValidityDays() <= 0) {
			offer.setValidityDays(config.getDefaultValidityDays());
		}
		offer.setStatus(OfferStatus.VALID);
		return offerRepo.save(offer);
	}
	
	public void deleteOffer(long offerId) {
		offerRepo.deleteOffer(offerId);
	}
	
	public List<Offer> findOfferByCustomer (String customer) {
		List<Offer> offersList = offerRepo.findAllByCustomer(customer);
		if (offersList.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		return setOffersStatus(offersList);
	}
	
	public List<Offer> findAllOffers() {
		return setOffersStatus(offerRepo.findAllValid());
	}
	
	public Offer findById(long id) {
		Offer offer = offerRepo.findById(id);
		if (offer == null) {
			throw new OfferNotFoundException();
		}
		offer.setStatus(checkIfValid(offer) ? OfferStatus.VALID : OfferStatus.EXPIRED);
		return offer;
	}
	
}
