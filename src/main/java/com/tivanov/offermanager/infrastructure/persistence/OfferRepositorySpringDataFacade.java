package com.tivanov.offermanager.infrastructure.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tivanov.offermanager.domain.model.offer.Offer;

@Repository
public class OfferRepositorySpringDataFacade {
	
	@Autowired
	private OfferJpaRepository offerJpaRepository;
	
	public Offer save(Offer offer) {
		return offerJpaRepository.saveAndFlush(offer);
	}
	
	public void deleteOffer(long offerId) {
		offerJpaRepository.deleteById(offerId);
		offerJpaRepository.flush();
	}
	
	public Offer findById(long id) {
		return offerJpaRepository.findById(id);
	}
	
	public List<Offer> findAllByCustomer(String customer) {
		return offerJpaRepository.findByCustomer(customer);
	}
	
	public List<Offer> findAllValid() {
		return offerJpaRepository.findAllValid();
	}

}
